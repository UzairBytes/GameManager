package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    public static String game = "sliding";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_starting_);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addLeaderBoardListener();
    }

    /**
     * Activate LeaderBoard Button
     */

    private void addLeaderBoardListener(){
        Button leaderBoardButton = findViewById(R.id.LeaderBoardButton);
        leaderBoardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switchToLeaderBoard();
            }
        });
    }

    /**
     * Switch to LeaderBoardActivity
     */
    private void switchToLeaderBoard(){
        Intent leaderBoard = new Intent(this, GlobalLeaderBoardActivity.class);
        startActivity(leaderBoard);
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSettings();

            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLoad();
            }
        });
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(SAVE_FILENAME);
                saveToFile(TEMP_SAVE_FILENAME);
                makeToastSavedText();
            }
            });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the SettingsActivity view to set parameters of the game.
     */
    private void switchToSettings(){
        if(game == "sliding") {
            Intent settings = new Intent(this, SlidingSettingsActivity.class);
            startActivity(settings);
        }
        else if(game == "checkers"){
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
        }
        else if(game == "twenty"){
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
        }
    }

    /**
     * Switch to LoadGameActiviy view to select games to load from.
     */
    private void switchToLoad(){
        Intent load = new Intent(this, LoadGameActivity.class);
        startActivity(load);
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
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Override the functionality of the back button
     */
    @Override
    public void onBackPressed(){
        Intent logIn = new Intent(this, LogInActivity.class);
        startActivity(logIn);
    }
}
