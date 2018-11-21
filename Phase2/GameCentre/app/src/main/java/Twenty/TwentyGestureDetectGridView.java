//package Twenty;
//import android.content.Context;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//
//import fall2018.csc2017.CoreClasses.GestureDetectGridView;
//
//public class TwentyGestureDetectGridView extends GestureDetectGridView {
//    private static final int SWIPE_MIN_DISTANCE = 100;
//    private final GestureDetector gDetector;
//    private TwentyMovementController mController;
//
//
//    public OnSwipeTouchListener(Context context) {
//        gDetector = new GestureDetector(context, new GestureListener());
//    }
//
//    public void onSwipeUp(){
//    }
//
//    public void onSwipeDown(){
//    }
//
//    public void onSwipeLeft() {
//    }
//
//    public void onSwipeRight() {
//    }
//
//    public boolean onTouch(View v, MotionEvent event) {
//        return gDetector.onTouchEvent(event);
//    }
//
//    private final class GestureListener extends SimpleOnGestureListener {
//
//        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
//        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
//
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            float distanceX = e2.getX() - e1.getX();
//            float distanceY = e2.getY() - e1.getY();
//            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
//                if (distanceX > 0)
//                    onSwipeRight();
//                else
//                    onSwipeLeft();
//                return true;
//            }
//            return false;
//        }
//    }
//}
