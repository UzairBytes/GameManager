package Sliding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.R;
import fall2018.csc2017.CoreClasses.SettingsActivity;
import phase1.Savable;

public class SlidingSettingsActivity extends SettingsActivity {

    protected SlidingBoardManager slidingBoardManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_settings);
        Savable.saveToFile(TEMP_SAVE_FILENAME, slidingBoardManager); //TODO Might be redundant - see savable below.
        addStartButtonListener();
    }

     void switchToGame(){
        Intent start = new Intent(this, SlidingTilesGameActivity.class);
        Savable.saveToFile(SettingsActivity.TEMP_SAVE_FILENAME, slidingBoardManager);
        startActivity(start);
    }

    /**
     * Activate Start button.
     */
     void addStartButtonListener(){
        final Button start = findViewById(R.id.SlidingStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSize();
                setUndos();
                if (size > 2 && size < 6) {
                    switchToGame();
                }
                else {
                    makeToastSize();
                }
            }
        });
    }

    /**
     * Displays that a size is not supported.
     */
    private void makeToastSize(){
        Toast.makeText(this, "Please enter a size 3 to 5.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets size from textView.
     */
    private void setSize(){
        String strSize = ((EditText)findViewById(R.id.SlidingBoardSizeInput)).getText().toString();
        if(strSize.equals("")) {
            size = 4;
        }
        else {
            size = Integer.parseInt(strSize);
        }
        slidingBoardManager = new SlidingBoardManager(size);
    }

    /**
     * Sets undos from textView.
     */
    void setUndos(){
        int numUndos;
        String strUndos = ((EditText)findViewById(R.id.SlidingUndosInput)).getText().toString();
        if(strUndos.equals("")){
            numUndos = 3;
        }
        else {
            numUndos = Integer.parseInt(strUndos);
        }
        slidingBoardManager.setMaxUndos(numUndos);
    }
}
