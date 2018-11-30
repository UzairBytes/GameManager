package Checkers;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.MovementController;
import phase1.AccountManager;
import phase1.Game;
import phase1.GameScore;
import phase1.LeaderBoard;

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
        if (moving && this.boardManager.isValidMove(position)) {
            LeaderBoard.updateScores(new GameScore(Game.CHECKERS_NAME,
                    this.boardManager.getGameFile().getName(), AccountManager.activeAccount.getUsername(), ((CheckersBoardManager)boardManager).score()));
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