package Sliding;

/*
Adapted from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/GestureDetectGridView.java

This extension of GridView contains built in logic for handling swipes between buttons
 */

import android.content.Context;
import android.util.AttributeSet;

import fall2018.csc2017.CoreClasses.GestureDetectGridView;

public class SlidingGestureDetectGridView extends GestureDetectGridView {

    public SlidingGestureDetectGridView(Context context) {
        super(context);
    }

    public SlidingGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
