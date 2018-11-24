package Twenty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.R;
import fall2018.csc2017.CoreClasses.SettingsActivity;

public class TwentySettingsActivity extends SettingsActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void switchToGame() {
        Intent start = new Intent(this, TwentyGameActivity.class);
        saveToFile(SettingsActivity.TEMP_SAVE_FILENAME);
        startActivity(start);
    }
    /**
     * Activate Start Button.
     */
    public void addStartButtonListener(){
        final Button start = findViewById(R.id.start);
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
        String strSize = ((EditText)findViewById(R.id.boardSizeInput)).getText().toString();
        if(strSize.equals("")) {
            size = 4;
        }
        else {
            size = Integer.parseInt(strSize);
        }
        boardManager = new TwentyBoardManager(size);
    }

    /**
     * Sets number of undos from textView.
     */
    public void setUndos(){
        int numUndos;
        String strUndos = ((EditText)findViewById(R.id.undosInput)).getText().toString();
        if (strUndos.equals("")){
            numUndos = 3;
        }
        else {
            numUndos = Integer.parseInt(strUndos);
        }
        boardManager.setMaxUndos(numUndos);
    }
}
