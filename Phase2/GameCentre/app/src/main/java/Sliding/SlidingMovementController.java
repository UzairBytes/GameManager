package Sliding;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.MovementController;
import phase1.AccountManager;
import phase1.GameScore;
import phase1.LeaderBoard;

/**
 * Controls the movement of tiles, and notifies the user of the results.
 */
public class SlidingMovementController extends MovementController {


    /**
     * Default constructor for sliding movement controller.
     */
    public SlidingMovementController() {
    }

    /**
     * Sets movement controller sliding board manager equal to argument slidingBoardManager.
     *
     * @param slidingBoardManager
     */
    public void setSlidingBoardManager(SlidingBoardManager slidingBoardManager) {
        this.boardManager = slidingBoardManager;
    }

    /**
     * If position of movement is a valid tap, executes touchMove and checks
     * to see if the puzzle has been solved or prints invalid tap if move cannot be
     * processed.
     *
     * @param context  app context.
     * @param position int.
     */
    public void processTapMovement(Context context, int position) {
        super.processMovement(context,"Game Over! No more moves possible!", position);
    }
}
