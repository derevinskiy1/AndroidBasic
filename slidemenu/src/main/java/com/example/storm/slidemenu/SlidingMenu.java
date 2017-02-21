package com.example.storm.slidemenu;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public class SlidingMenu extends ViewGroup {
    private ViewDragHelper mViewDragHelper;
    private View mMenuView;
    private View mContentView;
    private int mMenuWidth;

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelperCallBack());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mContentView = getChildAt(1);
        LayoutParams params = mMenuView.getLayoutParams();
        mMenuWidth = params.width;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递给ViewDragHelper
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int leftWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mMenuWidth, MeasureSpec.AT_MOST);
        mMenuView.measure(leftWidthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        mContentView.measure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mMenuView.layout(-mMenuView.getMeasuredWidth(), 0, 0, mMenuView.getMeasuredHeight());
        mContentView.layout(0, 0, mContentView.getMeasuredWidth(), mContentView.getMeasuredHeight());
    }


    class ViewDragHelperCallBack extends ViewDragHelper.Callback {
        //tryCaptureView----》触摸时捕捉到哪个View
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mContentView || child == mMenuView;
        }

        /**
         * 当touch水平移动后回调
         *
         * @param child child移动了
         * @param left  移动后的左上角坐标
         * @param dx    增量的x  ---》相对于上次移动增加了多少
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //解决滑动时边界问题
            if (child == mContentView) {
                if (left < 0) {
                    return 0;
                } else if (left > mMenuWidth) {
                    return mMenuWidth;
                }
            } else if (child == mMenuView) {
                if (-left > mMenuWidth) {
                    return -mMenuWidth;
                } else if (left > 0) {
                    return 0;
                }
            }
            return left;
        }

        //当位置改变时候发生移动
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
            int contentHeight = mContentView.getMeasuredHeight();
            int contentWidth = mContentView.getMeasuredWidth();
            int menuHeight = mMenuView.getMeasuredHeight();
            int menuWidth = mMenuView.getMeasuredWidth();
            if (changedView == mContentView) {
                mMenuView.layout(-mMenuWidth + left, 0, left, menuHeight);
            } else if (changedView == mMenuView) {
                mContentView.layout(left + menuWidth, 0, contentWidth + menuWidth + left, contentHeight);
            }
        }

        //View释放时回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (mContentView.getLeft() > mMenuWidth / 2f) {
                mViewDragHelper.smoothSlideViewTo(mMenuView, 0, 0);
            } else {
                mViewDragHelper.smoothSlideViewTo(mMenuView, -mMenuWidth, 0);
            }
            ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);

            super.onViewReleased(releasedChild, xvel, yvel);
        }
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
