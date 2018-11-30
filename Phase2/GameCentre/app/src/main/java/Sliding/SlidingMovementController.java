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
     * Sliding board manager for movement controller.
     */
    private SlidingBoardManager slidingBoardManager = null;

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

        this.slidingBoardManager = slidingBoardManager;
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
        if (slidingBoardManager.isValidTap(position)) {

            slidingBoardManager.touchMove(position);
            AccountManager.activeAccount.setActiveGameFile(slidingBoardManager.getGameFile());
            AccountManager.activeAccount.addGameFile(slidingBoardManager.getGameFile());
            if (slidingBoardManager.puzzleSolved()) {
                LeaderBoard.updateScores(new GameScore(
                        "SlidingTiles", slidingBoardManager.getGameFile().getName(),
                        AccountManager.activeAccount.getUsername(), slidingBoardManager.score()));
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
