package Sliding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.GameActivity;
import fall2018.csc2017.CoreClasses.R;
import fall2018.csc2017.CoreClasses.SettingsActivity;
import fall2018.csc2017.CoreClasses.AccountManager;
import fall2018.csc2017.CoreClasses.Savable;

public class SlidingSettingsActivity extends SettingsActivity {

    /**
     * Sliding board manager for settings activity.
     */
    protected SlidingBoardManager slidingBoardManager;

    /**
     * Sets activity view to xml layout, saves sliding board manager to temporary file.
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_settings);
        Savable.saveToFile(TEMP_SAVE_FILENAME, slidingBoardManager);
        addStartButtonListener();
    }

    /**
     * Saves sliding board manager to temporary file and switches to starting activity.
     */
    void switchToGame() {
        Intent start = new Intent(this, GameActivity.class);
        AccountManager.activeAccount.setActiveGameFile(slidingBoardManager.getGameFile());
        AccountManager.activeAccount.addGameFile(slidingBoardManager.getGameFile());
        Savable.saveToFile(SettingsActivity.TEMP_SAVE_FILENAME, slidingBoardManager);
        startActivity(start);
    }

    /**
     * Activate Start button.
     */
    void addStartButtonListener() {
        final Button start = findViewById(R.id.SlidingStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSize();
                setUndos();
                if (size > 2 && size < 6) {
                    switchToGame();
                } else {
                    makeToastSize();
                }
            }
        });
    }

    /**
     * Displays that a size is not supported.
     */
    private void makeToastSize() {
        Toast.makeText(this, "Please enter a size 3 to 5.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets size from textView.
     */
    private void setSize() {
        String strSize = ((EditText) findViewById(R.id.SlidingBoardSizeInput)).getText().toString();
        if (strSize.equals("")) { //TODO make method
            size = 4;
        } else {
            size = Integer.parseInt(strSize);
        }
        slidingBoardManager = new SlidingBoardManager(size);

    }

    /**
     * Sets undos from textView.
     */
    void setUndos() {
        int numUndos;
        String strUndos = ((EditText) findViewById(R.id.SlidingUndosInput)).getText().toString();
        if (strUndos.equals("")) {
            numUndos = 3;
        } else {
            numUndos = Integer.parseInt(strUndos);
        }
        slidingBoardManager.setMaxUndos(numUndos);

    }
}

