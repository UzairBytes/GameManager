package Checkers;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.AccountManager;
import fall2018.csc2017.CoreClasses.Game;
import fall2018.csc2017.CoreClasses.GameScore;
import fall2018.csc2017.CoreClasses.LeaderBoard;
import fall2018.csc2017.CoreClasses.MovementController;

public class CheckersMovementController extends MovementController {
    // TODO: Improve implementation of this, perhaps?

    private boolean moving = false;

    public CheckersMovementController() {
        moving = false;
    }

    public void setCheckersBoardManager(CheckersBoardManager checkersBoardManager) {
        this.boardManager = checkersBoardManager;
    }

    public void processTapMovement(Context context, int position) {
        if (moving && boardManager.isValidMove(position)) {
            super.performMove(context, ((CheckersBoardManager)boardManager).getWinner() + " wins!", position);
            if (!((CheckersBoardManager)boardManager).isHasSlain()) {
                moving = false;
            }
        }
        else if (((CheckersBoardManager)boardManager).isValidSelect(position)){
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