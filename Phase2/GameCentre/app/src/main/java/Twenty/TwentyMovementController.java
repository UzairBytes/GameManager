package Twenty;
import android.content.Context;
import android.widget.Toast;

import Sliding.SlidingBoardManager;
import fall2018.csc2017.CoreClasses.MovementController;
import phase1.AccountManager;
import phase1.GameScore;
import phase1.LeaderBoard;

public class TwentyMovementController extends MovementController {


    public TwentyMovementController() {
    }



    public void processSlideMovement(Context context, int direction) {
        super.processMovement(context,"Game Over! No more moves possible!", direction);
    }
}