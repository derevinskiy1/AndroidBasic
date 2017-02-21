package com.example.storm.sweepview;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


public class VDLineaLayout extends LinearLayout {
    private ViewDragHelper mViewDragHelper;
    private View mView1;
    private View mView2;
    private int maxWidth;

    public VDLineaLayout(Context context) {
        this(context, null);
    }

    public VDLineaLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VDLineaLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelperCallBack());
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        maxWidth = dm.widthPixels;
        System.out.println("-------------------------" + maxWidth);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mView1 = getChildAt(0);
        mView2 = getChildAt(1);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    class ViewDragHelperCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mView1 || child == mView2;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (left <= 0) {
                return 0;
            }
            if (left >= maxWidth - child.getWidth()) {
                return maxWidth - child.getWidth();
            }
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (releasedChild == mView2) {
                mViewDragHelper.settleCapturedViewAt(left, top);
            }
            invalidate();
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            mViewDragHelper.captureChildView(mView2, pointerId);
            mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);  //
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    private int left;
    private int top;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        left = mView2.getLeft();
        top = mView2.getTop();
    }

}
