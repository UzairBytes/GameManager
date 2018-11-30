package Checkers;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.MovementController;
import phase1.AccountManager;
import phase1.Game;
import phase1.GameScore;
import phase1.LeaderBoard;

public class CheckersMovementController extends MovementController {

    private CheckersBoardManager checkersBoardManager;

    private boolean moving = false;

    public CheckersMovementController() {
        moving = false;
    }

    public void setCheckersBoardManager(CheckersBoardManager checkersBoardManager) {

        this.checkersBoardManager = checkersBoardManager;
    }

    public void processTapMovement(Context context, int position) {
        if (moving && checkersBoardManager.isValidMove(position)) {

            checkersBoardManager.touchMove(position);
            if (checkersBoardManager.gameComplete()){
                LeaderBoard.updateScores(new GameScore(
                        Game.CHECKERS_NAME, checkersBoardManager.getGameFile().getName(),
                        AccountManager.activeAccount.getUsername(), checkersBoardManager.score()));
                Toast.makeText(context, checkersBoardManager.getWinner() + " wins!", Toast.LENGTH_SHORT).show();
            }
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