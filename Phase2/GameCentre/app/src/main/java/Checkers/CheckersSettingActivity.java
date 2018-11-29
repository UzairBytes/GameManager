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

    /**
     * The oppenentType.
     */
    private String oppenentType = new String("human");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkers_settings);
        Savable.saveToFile(TEMP_SAVE_FILENAME, checkersBoardManager); //TODO Might be redundant - see savable below.
        EditText undosInput = findViewById(R.id.CheckersUndosInput);
        undosInput.setText("0");
        EditText boardSizeInput = findViewById(R.id.CheckersBoardSizeInput);
        boardSizeInput.setText("8");
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
                checkersBoardManager.setOpponentType(oppenentType);
                if (size > 3 && size < 13 && size%2 == 0) {
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
        final Button human = findViewById(R.id.CheckersHuman);
        human.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oppenentType = "human";
            }
        });
    }

    /**
     * Activate Easy button.
     */
    void addEasyButtonListener(){
        final Button easy = findViewById(R.id.CheckersEasy);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oppenentType = "easy";
            }
        });
    }

    /**
     * Activate Hard button.
     */
    void addHardButtonListener(){
        final Button hard = findViewById(R.id.CheckersHard);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oppenentType = "hard";
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
            numUndos = 0;
        }
        else {
            numUndos = Integer.parseInt(strUndos);
        }
        checkersBoardManager.setMaxUndos(numUndos);
    }
}

