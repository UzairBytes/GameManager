package Twenty;
import android.content.Context;
import fall2018.csc2017.CoreClasses.*;
import phase1.*;
import android.widget.Toast;

public class TwentyMovementController extends MovementController {

    private TwentyBoardManager twentyBoardManager = null;

    public TwentyMovementController() {
    }

    public void setTwentyBoardManager(TwentyBoardManager twentyBoardManager) {

        this.twentyBoardManager = twentyBoardManager;
    }

    public void processSlideMovement(Context context, boolean horizDir, Character direction) {
        if (twentyBoardManager.isValidMove(horizDir)) {

            twentyBoardManager.touchMove(direction);

            if (twentyBoardManager.gameComplete()) {
                LeaderBoard.updateScores(context, new GameScore(
                        "Twenty", twentyBoardManager.getGameFile().getName(),
                        AccountManager.activeAccount.getUsername(), twentyBoardManager.score()));
                Toast.makeText(context, "Game Over! No more moves possible!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid move!", Toast.LENGTH_SHORT).show();
        }
    }
}
