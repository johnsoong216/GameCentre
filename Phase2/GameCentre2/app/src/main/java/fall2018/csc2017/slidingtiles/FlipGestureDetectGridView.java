package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridView;

public class FlipGestureDetectGridView extends GridView {
    public static final int SWIPE_MIN_DISTANCE = 100;
    //public static final int SWIPE_MAX_OFF_PATH = 100;
    //public static final int SWIPE_THRESHOLD_VELOCITY = 100;
    private GestureDetector gDetector;
    private FlipMovementController mFlipController;
    private boolean mFlingConfirmed = false;
    private float mTouchX;
    private float mTouchY;

    public FlipGestureDetectGridView(Context context) {
        super(context);
        init(context);
    }

    public FlipGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlipGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) // API 21
    public FlipGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                 int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(final Context context) {
        mFlipController = new FlipMovementController();
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int position = FlipGestureDetectGridView.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));

                mFlipController.processTapMovement(context, position);
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return gDetector.onTouchEvent(ev);
    }

    public void setFlipManager(FlipManager flipManager) {
        mFlipController.setFlipManager(flipManager);
    }
}

