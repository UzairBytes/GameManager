package Twenty;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import fall2018.csc2017.CoreClasses.GestureDetectGridView;
import android.view.View;


public class TwentyGestureDetectGridView extends GestureDetectGridView implements View.OnTouchListener, GestureDetector.OnGestureListener {
    private static final int SWIPE_MIN_DISTANCE = 100;
    private final GestureDetector gDetector;
    private TwentyMovementController mController;

    public TwentyGestureDetectGridView(Context context) {
        super(context);
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener());
        mController = new TwentyMovementController();
    }

    public TwentyGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener());
        mController = new TwentyMovementController();
    }

    public TwentyGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener());
        mController = new TwentyMovementController();
    }

    public void onSwipeUp(){
        mController.processSlideMovement(this.getContext(), false, 'U');
    }

    public void onSwipeDown(){
        mController.processSlideMovement(this.getContext(), false, 'D');
    }

    public void onSwipeLeft() {
        mController.processSlideMovement(this.getContext(), true, 'L');
    }

    public void onSwipeRight() {
        mController.processSlideMovement(this.getContext(), true, 'R');
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gDetector.onTouchEvent(event);
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
                onSwipeUp();
            } else {
                onSwipeDown();
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_MIN_DISTANCE) {
            if (distanceX > 0) {
                onSwipeRight();
            } else {
                onSwipeLeft();
            }
            return true;

        } else if (Math.abs(distanceY) > Math.abs(distanceX) && Math.abs(distanceY) > SWIPE_MIN_DISTANCE) {
            if (distanceY > 0) {
                onSwipeUp();
            } else {
                onSwipeDown();
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e){
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {};

    @Override
    public void onLongPress(MotionEvent e) {};


    public void setBoardManager(TwentyBoardManager twentyBoardManager) {
        mController.setTwentyBoardManager(twentyBoardManager);
    }
}