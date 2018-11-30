package Checkers;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import fall2018.csc2017.CoreClasses.GestureDetectGridView;

public class CheckersGestureDetectGridView extends GestureDetectGridView {

    public CheckersGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckersGestureDetectGridView(Context context) {
        super(context);
    }

    public CheckersGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
