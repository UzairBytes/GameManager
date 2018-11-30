package Checkers;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.MovementController;
import phase1.AccountManager;
import phase1.Game;
import phase1.GameScore;
import phase1.LeaderBoard;

public class CheckersMovementController extends MovementController {

    private boolean moving = false;

    public CheckersMovementController() {
        moving = false;
    }

    public void setCheckersBoardManager(CheckersBoardManager checkersBoardManager) {
        this.boardManager = checkersBoardManager;
    }

    public void processTapMovement(Context context, int position) {
        CheckersBoardManager checkersBoardManager = ((CheckersBoardManager)boardManager);
        if (moving && checkersBoardManager.isValidMove(position)) {
            checkersBoardManager.touchMove(position);
            super.processMovement(context, checkersBoardManager.getWinner() + " wins!");
            if (!checkersBoardManager.isHasSlain()) {
                moving = false;
            }
        }
        else if (checkersBoardManager.isValidSelect(position)){
            moving = true;
        }
        else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }

    void setMoving(boolean moving) {
        this.moving = moving;
    }
}