package fall2018.csc2017.CoreClasses;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MovementController extends AppCompatActivity {

    protected BoardManager boardManager;

    public void processMovement(Context context, String toastMessage, int position) {
        if (boardManager.isValidMove(position)) {
            performMove(context, toastMessage, position);
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }

    protected void performMove(Context context, String toastMessage, int position) {
        boardManager.touchMove(position);
        AccountManager.activeAccount.setActiveGameFile(boardManager.getGameFile());
        AccountManager.activeAccount.addGameFile(boardManager.getGameFile());
        if (boardManager.gameComplete()) {
            if (!AccountManager.activeAccount.getActiveGameName().equals(Game.CHECKERS_NAME)) {
                LeaderBoard.updateScores(new GameScore(
                        AccountManager.activeAccount.getActiveGameName(),
                        boardManager.getGameFile().getName(),
                        AccountManager.activeAccount.getUsername(),
                        boardManager.score()));
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }


}
