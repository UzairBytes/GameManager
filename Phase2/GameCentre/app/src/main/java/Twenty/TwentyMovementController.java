package Twenty;
import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.MovementController;
import phase1.AccountManager;
import phase1.GameScore;
import phase1.LeaderBoard;

public class TwentyMovementController extends MovementController {


    public TwentyMovementController() {
    }

    public void setTwentyBoardManager(TwentyBoardManager twentyBoardManager) {
        this.boardManager = twentyBoardManager;
    }

    public void processSlideMovement(Context context, int horizDir, int direction) {
        if (boardManager.isValidMove(horizDir)) {
            boardManager.touchMove(direction);
            super.processMovement(context,"Game Over! No more moves possible!");
        } else {
            Toast.makeText(context, "Invalid move!", Toast.LENGTH_SHORT).show();
        }
    }
}
