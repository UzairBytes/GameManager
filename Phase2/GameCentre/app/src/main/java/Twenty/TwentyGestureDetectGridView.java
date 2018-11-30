package Twenty;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import fall2018.csc2017.CoreClasses.GestureDetectGridView;
import fall2018.csc2017.CoreClasses.MovementController;

import android.view.View;

public class TwentyGestureDetectGridView extends GestureDetectGridView implements GestureDetector.OnGestureListener {
    private static final int SWIPE_MIN_DISTANCE = 100;
    private final GestureDetector gDetector = new GestureDetector(this);
    private String toastMessage = "Game Over! No more moves possible!";

    public TwentyGestureDetectGridView(Context context) {
        super(context);
        mController = new TwentyMovementController();
    }

    public TwentyGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mController = new TwentyMovementController();
    }

    public TwentyGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mController = new TwentyMovementController();
    }

    public void onSwipeUp() {
        mController.processMovement(this.getContext(),  toastMessage, 0);
    }

    public void onSwipeDown() {
        mController.processMovement(this.getContext(),  toastMessage, 1);
    }

    public void onSwipeLeft() {
        mController.processMovement(this.getContext(),  toastMessage, 2);
    }

    public void onSwipeRight() {
        mController.processMovement(this.getContext(),  toastMessage, 3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        super.dispatchTouchEvent(e);
        return gDetector.onTouchEvent(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float distanceX = e2.getX() - e1.getX();
        float distanceY = e2.getY() - e1.getY();
        if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_MIN_DISTANCE) {
            if (distanceX > 0) {
                onSwipeRight();
            } else {
                onSwipeLeft();
            }
            return true;

        } else if (Math.abs(distanceY) > Math.abs(distanceX) && Math.abs(distanceY) > SWIPE_MIN_DISTANCE) {
            if (distanceY > 0) {
                onSwipeDown();
            } else {
                onSwipeUp();
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    public void setBoardManager(TwentyBoardManager twentyBoardManager) {
        mController.setBoardManager(twentyBoardManager);
    }
}