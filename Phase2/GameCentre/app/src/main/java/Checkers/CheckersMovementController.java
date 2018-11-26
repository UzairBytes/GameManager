package Checkers;

import android.content.Context;
import android.widget.Toast;

import fall2018.csc2017.CoreClasses.MovementController;
import phase1.AccountManager;
import phase1.GameScore;
import phase1.LeaderBoard;

public class CheckersMovementController extends MovementController {

    private CheckersBoardManager checkersBoardManager = null;

    public CheckersMovementController() {
    }

    public void setCheckersBoardManager(CheckersBoardManager checkersBoardManager) {

        this.checkersBoardManager = checkersBoardManager;
    }

    public void processTapMovement(Context context, int position) {
        if (checkersBoardManager.isValidMove(position)) {

            checkersBoardManager.touchMove(position);
            if (checkersBoardManager.gameComplete()){
                LeaderBoard.updateScores(new GameScore(
                        "CheckersTile", checkersBoardManager.getGameFile().getName(),
                        AccountManager.activeAccount.getUsername(), checkersBoardManager.score()));
                Toast.makeText(context, checkersBoardManager.getWinner() + " wins!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}