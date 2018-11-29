package Sliding;

/*
Adapted from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/GestureDetectGridView.java

This extension of GridView contains built in logic for handling swipes between buttons
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import fall2018.csc2017.CoreClasses.GestureDetectGridView;

public class SlidingGestureDetectGridView extends GestureDetectGridView {

    /**
     * Swipe distance in gesture detect gridvew.
     */
    public static final int SWIPE_MIN_DISTANCE = 100;

    /**
     * Controls detection of gestures on gridview.
     */
    private GestureDetector gDetector;

    /**
     * Controls movements of buttons and tiles in gridview.
     */
    private SlidingMovementController mController;

    private boolean mFlingConfirmed = false;

    /**
     * x coordinate of touch movement on gridview.
     */
    private float mTouchX;

    /**
     * y coordinate of touch movement on gridview.
     */
    private float mTouchY;

    public SlidingGestureDetectGridView(Context context) {
        super(context);
        init(context);
    }

    public SlidingGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlidingGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) // API 21

    private void init(final Context context) {
        mController = new SlidingMovementController();
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int position = SlidingGestureDetectGridView.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));

                mController.processTapMovement(context, position);
                return true;
            }

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        gDetector.onTouchEvent(ev);

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mFlingConfirmed = false;
        } else if (action == MotionEvent.ACTION_DOWN) {
            mTouchX = ev.getX();
            mTouchY = ev.getY();
        } else {

            if (mFlingConfirmed) {
                return true;
            }

            float dX = (Math.abs(ev.getX() - mTouchX));
            float dY = (Math.abs(ev.getY() - mTouchY));
            if ((dX > SWIPE_MIN_DISTANCE) || (dY > SWIPE_MIN_DISTANCE)) {
                mFlingConfirmed = true;
                return true;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

    /**
     * Return true if movement event occurs.
     *
     * @param ev motion event.
     * @return true or false.
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return gDetector.onTouchEvent(ev);
    }

    /**
     * Sets board manager in movement controller.
     *
     * @param slidingBoardManager sliding Board manager object.
     */
    public void setBoardManager(SlidingBoardManager slidingBoardManager) {
        mController.setSlidingBoardManager(slidingBoardManager);
    }
}
