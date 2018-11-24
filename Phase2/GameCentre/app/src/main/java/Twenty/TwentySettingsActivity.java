package Twenty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.R;
import fall2018.csc2017.CoreClasses.SettingsActivity;
import phase1.Savable;

public class TwentySettingsActivity extends SettingsActivity {

    protected TwentyBoardManager twentyBoardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_settings);
        Savable.saveToFile(TEMP_SAVE_FILENAME,twentyBoardManager);
        addStartButtonListener();
    }

    public void switchToGame() {
        Intent start = new Intent(this, TwentyGameActivity.class);
        Savable.saveToFile(SettingsActivity.TEMP_SAVE_FILENAME,twentyBoardManager);
        startActivity(start);
    }

    /**
     * Activate Start Button.
     */
    public void addStartButtonListener(){
        final Button start = findViewById(R.id.TwentyStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSize();
                setUndos();
                if ((size > 2) && (size < 6)){
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
        String strSize = ((EditText)findViewById(R.id.twentyBoardSizeInput)).getText().toString();
        if(strSize.equals("")) {
            size = 4;
        }
        else {
            size = Integer.parseInt(strSize);
        }
        twentyBoardManager = new TwentyBoardManager(size);
    }

    /**
     * Sets number of undos from textView.
     */
    public void setUndos(){
        int numUndos;
        String strUndos = ((EditText)findViewById(R.id.twentyUndosInput)).getText().toString();
        if (strUndos.equals("")){
            numUndos = 3;
        }
        else {
            numUndos = Integer.parseInt(strUndos);
        }
        twentyBoardManager.setMaxUndos(numUndos);
    }
}
