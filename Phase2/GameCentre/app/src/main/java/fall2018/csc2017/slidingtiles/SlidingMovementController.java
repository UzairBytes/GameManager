package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;
import phase1.AccountManager;
import phase1.GameScore;
import phase1.LeaderBoard;

/**
 * Controls the movement of tiles, and notifies the user of the results.
 */
public class SlidingMovementController extends MovementController {

    private SlidingBoardManager slidingBoardManager = null;

    public SlidingMovementController() {
    }

    public void setSlidingBoardManager(SlidingBoardManager slidingBoardManager) {

        this.slidingBoardManager = slidingBoardManager;
    }

    public void processTapMovement(Context context, int position) {
        if (slidingBoardManager.isValidTap(position)) {

            slidingBoardManager.touchMove(position);
            if (slidingBoardManager.puzzleSolved()) {
                LeaderBoard.updateScores(context, new GameScore(
                        "SlidingTiles", slidingBoardManager.getGameFile().getName(),
                        AccountManager.activeAccount.getUsername(), slidingBoardManager.score()));
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
