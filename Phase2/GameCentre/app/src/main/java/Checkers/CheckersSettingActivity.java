package Checkers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.R;
import fall2018.csc2017.CoreClasses.SettingsActivity;
import phase1.Savable;

public class CheckersSettingActivity extends SettingsActivity {

    /**
     * The board manager.
     */
    protected CheckersBoardManager checkersBoardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Savable.saveToFile(TEMP_SAVE_FILENAME, checkersBoardManager); //TODO Might be redundant - see savable below.
        addStartButtonListener();
        addEasyButtonListener();
        addHardButtonListener();
        addHumanButtonListener();
    }

    void switchToGame(){
        Intent start = new Intent(this, CheckersGameActivity.class);
        Savable.saveToFile(SettingsActivity.TEMP_SAVE_FILENAME, checkersBoardManager);
        startActivity(start);
    }

    /**
     * Activate Start button.
     */
    void addStartButtonListener(){
        final Button start = findViewById(R.id.CheckersStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSize();
                setUndos();
                if (size > 7 && size < 13) {
                    switchToGame();
                }
                else {
                    makeToastSize();
                }
            }
        });
    }

    /**
     * Activate Human button.
     */
    void addHumanButtonListener(){
        final Button start = findViewById(R.id.CheckersHuman);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkersBoardManager.setOpponentType("human");
            }
        });
    }

    /**
     * Activate Easy button.
     */
    void addEasyButtonListener(){
        final Button start = findViewById(R.id.CheckersEasy);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkersBoardManager.setOpponentType("easy");
            }
        });
    }

    /**
     * Activate Hard button.
     */
    void addHardButtonListener(){
        final Button start = findViewById(R.id.CheckersHard);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkersBoardManager.setOpponentType("hard");
            }
        });
    }

    /**
     * Displays that a size is not supported.
     */
    private void makeToastSize(){
        Toast.makeText(this, "Please enter a size 8 to 12.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets size from textView.
     */
    private void setSize(){
        String strSize = ((EditText)findViewById(R.id.CheckersBoardSizeInput)).getText().toString();
        if(strSize.equals("")) {
            size = 8;
        }
        else {
            size = Integer.parseInt(strSize);
        }
        checkersBoardManager = new CheckersBoardManager(size, true);
    }

    /**
     * Sets undos from textView.
     */
    void setUndos(){
        int numUndos;
        String strUndos = ((EditText)findViewById(R.id.CheckersUndosInput)).getText().toString();
        if(strUndos.equals("")){
            numUndos = 1;
        }
        else {
            numUndos = Integer.parseInt(strUndos);
        }
        checkersBoardManager.setMaxUndos(numUndos);
    }
}

