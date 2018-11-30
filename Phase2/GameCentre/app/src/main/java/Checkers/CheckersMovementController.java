package Checkers;

import android.content.Context;
import android.widget.Toast;


import fall2018.csc2017.CoreClasses.MovementController;

/**
 * A class to process movements
 */
public class CheckersMovementController extends MovementController {

    /**
     * An attribute which
     */
    private boolean moving = false;

    public CheckersMovementController() {
        moving = false;
    }


    @Override
    public void processMovement(Context context, String toastMessage, int position) {
        if (moving && boardManager.isValidMove(position)) {
            super.performMove(context, "A player wins!", position);
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

}