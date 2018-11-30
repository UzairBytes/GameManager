package fall2018.csc2017.CoreClasses;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.widget.GridView;

import Sliding.SlidingBoardManager;

public class GestureDetectGridView extends GridView {
    /**
     * Controls movements of buttons and tiles in gridview.
     */
    protected MovementController mController;

    public GestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GestureDetectGridView(Context context) {
        super(context);
    }

    public GestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBoardManager(BoardManager boardManager) {
        mController.setBoardManager(boardManager);
    }
}
