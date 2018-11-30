package fall2018.csc2017.CoreClasses;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import phase1.AccountManager;
import phase1.GameScore;
import phase1.LeaderBoard;

public abstract class MovementController extends AppCompatActivity {

    protected BoardManager boardManager;

    protected void processMovement(Context context, String toastMessage){
//        Uncomment below when boardManager implements
        AccountManager.activeAccount.setActiveGameFile(boardManager.getGameFile());
        AccountManager.activeAccount.addGameFile(boardManager.getGameFile());
        if (boardManager.gameComplete()) {
            LeaderBoard.updateScores(new GameScore(
                    "Twenty", boardManager.getGameFile().getName(),
                    AccountManager.activeAccount.getUsername(), boardManager.score()));
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
