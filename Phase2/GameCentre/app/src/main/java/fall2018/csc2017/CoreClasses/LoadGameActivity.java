package fall2018.csc2017.CoreClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Checkers.CheckersBoardManager;
import Checkers.CheckersGameActivity;
import Checkers.CheckersGameFile;
import Sliding.SlidingBoardManager;
import Sliding.SlidingTilesGameActivity;
import Twenty.TwentyBoardManager;
import Twenty.TwentyGameActivity;
import Twenty.TwentyGameFile;
import phase1.Account;
import phase1.AccountManager;
import phase1.Game;
import phase1.GameFile;
import Sliding.SlidingGameFile;
import phase1.Savable;


public class LoadGameActivity extends AppCompatActivity {


    public final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    private BoardManager boardManager;

    private ArrayList<String> files;

    /**
     * Shows the activity_load_game.xml file, populates a list view with the user saved files, and creates a listener for the load button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String gameType = AccountManager.activeAccount.getActiveGameName();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);
        files = new ArrayList<>(AccountManager.activeAccount.getGames(gameType).keySet());
        System.out.println("files:" + files);
        ListAdapter fileAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, files);
        ListView listV = findViewById(R.id.savedFiles);
        listV.setAdapter(fileAdapter);
        listV.setChoiceMode(1);
        LoadButtonListener();
    }

    /**
     * Switches to StartingActivity to play sliding tiles.
     */
    private void switchToGame(){
        //TODO
        String gameType = AccountManager.activeAccount.getActiveGameName();
        Intent start = null;
        if(gameType.equals(Game.SLIDING_NAME)){
             start = new Intent(this, SlidingTilesGameActivity.class);
        }else if(gameType.equals(Game.TWENTY_NAME)){
            start = new Intent(this, TwentyGameActivity.class);
        }else if(gameType.equals(Game.CHECKERS_NAME)){
            start = new Intent(this, CheckersGameActivity.class);
        }
        ListView listV = findViewById(R.id.savedFiles);
        int pos = listV.getCheckedItemPosition();
        if(pos == -1){
            makeToastFile();
        }
        else {
            Account acc = AccountManager.activeAccount;
            String fileName = files.get(pos);
            acc.loadAccountGameData();
            GameFile desiredFile = AccountManager.activeAccount.getGames(gameType).get(fileName);
            if(gameType.equals(Game.SLIDING_NAME)){
                boardManager = new SlidingBoardManager((SlidingGameFile)desiredFile);
            }else if(gameType.equals(Game.TWENTY_NAME)){
                boardManager = new TwentyBoardManager((TwentyGameFile) desiredFile);
            }else{
            boardManager = new CheckersBoardManager((CheckersGameFile) desiredFile);
            }
            Savable.saveToFile(TEMP_SAVE_FILENAME, boardManager);
            startActivity(start);
        }
    }

    /**
     * Activate Start button.
     */
    private void LoadButtonListener(){
        Button start = findViewById(R.id.Load);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame();
            }
        });
    }

    /**
     * Displays that the user has not select a file..
     */
    private void makeToastFile(){
        Toast.makeText(this, "Please select a save file.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Override the functionality of the back button
     */
    @Override
    public void onBackPressed(){
        Intent startingActivity = new Intent(this, StartingActivity.class);
        startActivity(startingActivity);
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
