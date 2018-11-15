package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import phase1.AccountManager;
import phase1.GameScore;
import phase1.LeaderBoard;

/**
 * Controls the movement of tiles, and notifies the user of the results.
 */
public class SlidingMovementController extends MovementController {

    private BoardManager boardManager = null;

    public SlidingMovementController() {
    }

    public void setBoardManager(BoardManager boardManager) {

        this.boardManager = boardManager;
    }

    public void processTapMovement(Context context, int position) {
        if (boardManager.isValidTap(position)) {

            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                LeaderBoard.updateScores(context, new GameScore(
                        "SlidingTiles", boardManager.getGameFile().getName(),
                        AccountManager.activeAccount.getUsername(), boardManager.score()));
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
