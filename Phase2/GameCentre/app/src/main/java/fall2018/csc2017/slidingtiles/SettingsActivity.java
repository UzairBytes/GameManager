package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The activity for determining settings for a new game.
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * The board manager.
     */
     BoardManager boardManager;
    
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    /**
     * Size of the board specified
     */
     int size = 4;

    /**
     * Shows activity_settings.xml file and starts the listener for the start button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saveToFile(TEMP_SAVE_FILENAME);
        setContentView(R.layout.activity_settings);
        EditText undosInput = findViewById(R.id.undosInput);
        undosInput.setText("3");
        EditText boardSizeInput = findViewById(R.id.boardSizeInput);
        boardSizeInput.setText("4");

        addStartButtonListener();
    }

    /**
     * Switches to desired game.
     */
    void switchToGame(){
    }

    /**
     * Activate Start button.
     */
    void addStartButtonListener(){
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

    /**
     * Override the functionality of the back button
     */
    @Override
    public void onBackPressed(){
        Intent startingActivity = new Intent(this, StartingActivity.class);
        startActivity(startingActivity);
    }

    /**
     * Sets undos from textView.
     */
    void setUndos(){
        int numUndos;
        String strUndos = ((EditText)findViewById(R.id.undosInput)).getText().toString();
        if(strUndos.equals("")){
            numUndos = 3;
        }
        else {
            numUndos = Integer.parseInt(strUndos);
        }
        boardManager.setMaxUndos(numUndos);
    }
}



