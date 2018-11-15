package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.os.Bundle;

public class SlidingSettingsActivity extends SettingsActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void switchToGame(){
        Intent start = new Intent(this, GameActivity.class);
        saveToFile(SettingsActivity.TEMP_SAVE_FILENAME);
        startActivity(start);
    }
}
