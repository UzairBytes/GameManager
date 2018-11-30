package Twenty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.R;
import fall2018.csc2017.CoreClasses.SettingsActivity;
import phase1.AccountManager;
import phase1.Savable;

public class TwentySettingsActivity extends SettingsActivity {

    protected TwentyBoardManager twentyBoardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("first");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_settings);
        EditText undosInput = findViewById(R.id.twentyUndosInput);
        undosInput.setText("3");
        EditText boardSizeInput = findViewById(R.id.twentyBoardSizeInput);
        boardSizeInput.setText("4");
        Savable.saveToFile(TEMP_SAVE_FILENAME, twentyBoardManager);
        addStartButtonListener();
    }

    public void switchToGame() {
        Intent start = new Intent(this, TwentyGameActivity.class);
        AccountManager.activeAccount.setActiveGameFile(twentyBoardManager.getGameFile());
        AccountManager.activeAccount.addGameFile(twentyBoardManager.getGameFile());
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
                updateUndos();
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
     * Updates the number of undos based off of the value in the textView.
     * // TODO: These are public. Should they be private?
     */
    public void updateUndos(){
        // Get the value from the textView, and change the number of undos for this board manager.
        String undosFromView = ((EditText)findViewById(R.id.twentyUndosInput)).getText().toString();
        setUndos(undosFromView);
    }

    public void setUndos(String undosFromView){
        int numUndos;
        if (undosFromView.equals("")){
            numUndos = 3;
        }
        else {
            numUndos = Integer.parseInt(undosFromView);
        }
        System.out.println("Num undos:" + numUndos);
        twentyBoardManager.setMaxUndos(numUndos);
    }
}
