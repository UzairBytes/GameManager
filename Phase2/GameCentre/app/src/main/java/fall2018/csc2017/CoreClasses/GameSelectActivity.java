package fall2018.csc2017.CoreClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import phase1.AccountManager;
import phase1.Game;

/**
 * The activity for select the game the user wishes to play.
 */
public class GameSelectActivity extends AppCompatActivity {

    /**
     * game denotes the game selected by user.
     */
    public static String game = "";

    /**
     * Shows the activity_game_select.xml file and starts the listener for the games corresponding button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);

        slidingTilesButtonListener();
        checkersButtonListener();
        twentyButtonListener();
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
        AccountManager.activeAccount.setActiveGameName(Game.SLIDING_NAME);
        slidingTitles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToStartingActivity();
            }
        });
    }

    /**
     * Activate Checkers button.
     */
    private void checkersButtonListener() {
        Button slidingTitles = findViewById(R.id.button2);
        AccountManager.activeAccount.setActiveGameName(Game.CHECKERS_NAME);
        slidingTitles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToStartingActivity();
            }
        });
    }

    /**
     * Activate Checkers button.
     */
    private void twentyButtonListener() {
        Button slidingTitles = findViewById(R.id.button);
        AccountManager.activeAccount.setActiveGameName(Game.TWENTY_NAME);
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
