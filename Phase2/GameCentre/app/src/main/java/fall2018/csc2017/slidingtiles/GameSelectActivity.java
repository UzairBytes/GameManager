package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The activity for select the game the user wishes to play.
 */
public class GameSelectActivity extends AppCompatActivity {

    /**
     * Shows the activity_game_select.xml file and starts the listener for the games corresponding button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);

        slidingTilesButtonListener();
    }

    /**
     * Switches to StartingActivity, which is the SlidingTiles main screen.
     */
    private void switchToStartingActivity(){
        Intent starting = new Intent(this, StartingActivity.class);
        startActivity(starting);
    }

    /**
     * Activate Slidingtiles button.
     */
    private void slidingTilesButtonListener(){
        Button slidingTitles = findViewById(R.id.button5);
        slidingTitles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToStartingActivity();
            }
        });
    }
    /**
     * Override the functionality of the back button
     */
    @Override
    public void onBackPressed(){
        Intent logInActivity = new Intent(this, LogInActivity.class);
        startActivity(logInActivity);
    }
}
