package Twenty;

import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.CoreClasses.CustomAdapter;
import fall2018.csc2017.CoreClasses.GameActivity;
import fall2018.csc2017.CoreClasses.R;
import phase1.Savable;
import static fall2018.csc2017.CoreClasses.SettingsActivity.TEMP_SAVE_FILENAME;


public class TwentyGameActivity extends GameActivity implements Observer {

    TwentyBoardManager twentyBoardManager;

    private TwentyGestureDetectGridView gridView;

    private static int columnWidth, columnHeight;

    ArrayList<Button> tileButtons;

    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    private void createTileButtons(Context context){
        TwentyBoard board = twentyBoardManager.getBoard();
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
        twentyBoardManager = (TwentyBoardManager) Savable.loadFromFile(TEMP_SAVE_FILENAME);
        createTileButtons(this);
        setContentView(R.layout.activity_twenty_game);

        gridView = findViewById(R.id.gridTwenty);
        gridView.setNumColumns(twentyBoardManager.getSize());
        gridView.setBoardManager(twentyBoardManager);
        twentyBoardManager.addObserver(this);

        gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                        this);
                int displayWidth = gridView.getMeasuredWidth();
                int displayHeight = gridView.getMeasuredHeight();

                columnWidth = displayWidth / twentyBoardManager.twentyBoard.getNumCols();
                columnHeight = displayHeight / twentyBoardManager.twentyBoard.getNumCols();

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
                twentyBoardManager.undo();
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    private void updateTileButtons() {
        TwentyBoard board = twentyBoardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / board.getNumCols();
            int col = nextPos % board.getNumCols();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }
}
