package fall2018.csc2017.CoreClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;

import Checkers.CheckersBoardManager;
import Sliding.SlidingBoardManager;
import Sliding.SlidingGestureDetectGridView;
import Twenty.TwentyBoard;
import Twenty.TwentyBoardManager;
import phase1.AccountManager;
import phase1.Game;
import phase1.Savable;

import static fall2018.csc2017.CoreClasses.SettingsActivity.TEMP_SAVE_FILENAME;


public class GameActivity extends AppCompatActivity {

    int contentId;
    int viewId;

    /**
     * The buttons to display.
     */
    protected ArrayList<Button> tileButtons; //TODO make private and add getter method

    /**
     * Gridview for sliding tiles game.
     */
    protected GestureDetectGridView gridView;

    /**
     * Calculate columnWidth and columnHeight based on device size.
     */
    protected int columnWidth, columnHeight;

    /**
     * The board manager.
     */
    protected BoardManager boardManager; //TODO make private and add getter method

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(StartingActivity.TEMP_SAVE_FILENAME); //TODO Use Savable class.
    }

    /**
     * Returns boardmanager
     *
     * @Return boardmanager
     */
    public BoardManager getBoardManager(){
        return boardManager;
    }

    /**
     * set boardmanager
     */
    public void setBoardManager(BoardManager newBoardManager){
        this.boardManager = newBoardManager;
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

    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
        gridView.setLongClickable(true);

    }

    private void createTileButtons(Context context){
        Board board = boardManager.getBoard();
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
        boardManager = (BoardManager)Savable.loadFromFile(TEMP_SAVE_FILENAME);
        createTileButtons(this);
        setContentView(viewId);
        gridView = findViewById(contentId);
        gridView.setNumColumns(boardManager.getSize());
        String activeGameName = AccountManager.activeAccount.getActiveGameName();
        gridView.setBoardManager(boardManager);

        boardManager.addObserver(this);

        gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                        this);
                int displayWidth = gridView.getMeasuredWidth();
                int displayHeight = gridView.getMeasuredHeight();

                columnWidth = displayWidth / boardManager.getSize();
                columnHeight = displayHeight / boardManager.getSize();

                display();
            }
        });

        addUndoButtonListener();
    }

    private void addUndoButtonListener(){
        Button undoButton = findViewById(R.id.undoTwentyButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.undo();
                AccountManager.activeAccount.setActiveGameFile(boardManager.getGameFile());
                AccountManager.activeAccount.addGameFile(boardManager.getGameFile());
            }
        });
    }

    public void update(Observable o, Object arg) {
        display();
    }

    private void updateTileButtons() {
        Board board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / board.getNumCols();
            int col = nextPos % board.getNumCols();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }
}
