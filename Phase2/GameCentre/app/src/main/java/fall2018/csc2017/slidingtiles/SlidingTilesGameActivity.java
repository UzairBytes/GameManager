package fall2018.csc2017.slidingtiles;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import java.util.Observable;
import java.util.Observer;

/**
 * The game activity.
 */
public class SlidingTilesGameActivity extends GameActivity implements Observer {

    // Grid View and calculated column height and width based on device size
    private SlidingGestureDetectGridView gridView;

    private static int columnWidth, columnHeight;

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
    private void addUndoButtonListener(){
        Button undoButton = findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.undo();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile();
        createTileButtons(this);
        setContentView(R.layout.activity_main);

        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(boardManager.board.numCols);
        gridView.setBoardManager(boardManager);
        boardManager.addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / boardManager.board.numRows;
                        columnHeight = displayHeight / boardManager.board.numCols;

                        display();
                    }
                });
        addUndoButtonListener();
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }
}
