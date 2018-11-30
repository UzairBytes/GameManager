package Sliding;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.CoreClasses.CustomAdapter;
import fall2018.csc2017.CoreClasses.GameActivity;
import fall2018.csc2017.CoreClasses.R;
import fall2018.csc2017.CoreClasses.StartingActivity;
import fall2018.csc2017.CoreClasses.AccountManager;
import fall2018.csc2017.CoreClasses.Savable;

import static fall2018.csc2017.CoreClasses.SettingsActivity.TEMP_SAVE_FILENAME;

/**
 * The game activity.
 */
public class SlidingTilesGameActivity extends GameActivity implements Observer {


    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    /**
     * Undo botton restores game to previous state.
     */
    private void addUndoButtonListener() {
        Button undoButton = findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.undo();
                AccountManager.activeAccount.setActiveGameFile(boardManager.getGameFile());
                AccountManager.activeAccount.addGameFile(boardManager.getGameFile());
            }
        });
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    void createTileButtons(Context context) {
        SlidingBoard board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getNumRows(); row++) {
            for (int col = 0; col != board.getNumCols(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slidingboardManager = (SlidingBoardManager) Savable.loadFromFile(TEMP_SAVE_FILENAME);
        createTileButtons(this);
        setContentView(R.layout.activity_sliding_tiles_game);

        // Add View to activity
        gridView = findViewById(R.id.slidingGrid);
        gridView.setNumColumns(slidingboardManager.getBoard().getNumCols());
        gridView.setBoardManager(slidingboardManager);
        slidingboardManager.addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / slidingboardManager.getBoard().getNumCols();
                        columnHeight = displayHeight / slidingboardManager.getBoard().getNumCols();

                        display();
                    }
                });
        addUndoButtonListener();
    }

    /**
     * Updates display based on behaviour of observables like Sliding Board Manager.
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        SlidingBoard board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / board.getNumCols();
            int col = nextPos % board.getNumCols();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    //TODO: What's this for?
    @Override
    protected void onPause() {
        super.onPause();
        Savable.saveToFile(StartingActivity.TEMP_SAVE_FILENAME, slidingboardManager); //TODO Use Savable class.
    }
}
