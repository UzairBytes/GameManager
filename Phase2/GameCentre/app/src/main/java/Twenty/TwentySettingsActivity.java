package Twenty;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.SettingsActivity;
import fall2018.csc2017.slidingtiles.SlidingBoardManager;

public class TwentySettingsActivity extends SettingsActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        boardManager = new SlidingBoardManager(size);
    }
}
