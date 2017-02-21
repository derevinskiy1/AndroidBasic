package com.example.storm.sweepview;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SweepView extends ViewGroup {
    private View mContentView;
    private View mDeleteView;
    private int mDeleteWidth;
    private ViewDragHelper viewDragHelper;
    private OnSweepListener mListener;
    private boolean isOpened = false;

    //用来分析touch事件的工具类
    //Touch事件分析和监听
    //实现自己的CallBack
    public SweepView(Context context) {
        this(context, null);
    }

    public SweepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SweepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化ViewDragHelper
        viewDragHelper = ViewDragHelper.create(this, new ViewDragHelperCallBack());
    }

    //从Xml加载组件时候回调方法
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
        mDeleteView = getChildAt(1);
        LayoutParams params = mDeleteView.getLayoutParams();
        mDeleteWidth = params.width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量
        mContentView.measure(widthMeasureSpec, heightMeasureSpec);
        //测量删除部分
        int deleteWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mDeleteWidth, MeasureSpec.getMode(MeasureSpec.EXACTLY));
        mDeleteView.measure(deleteWidthMeasureSpec, heightMeasureSpec);
        //确定自己的高度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    //回调该方法来确定子布局显示的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //内容区域
        mContentView.layout(0, 0, mContentView.getMeasuredWidth(), mContentView.getMeasuredHeight());
        //删除部分
        mDeleteView.layout(mContentView.getMeasuredWidth(), 0,
                mContentView.getMeasuredWidth() + mDeleteView.getMeasuredWidth(),
                mDeleteView.getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    class ViewDragHelperCallBack extends ViewDragHelper.Callback {
        //是否分析view的touch
        //1.down的时候触发tryCaptureView
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //child:触摸的View
            //pointerId:touch的id
            return child == mContentView || child == mDeleteView;
        }
        //2.move的时候触发clampViewPositionHorizontal
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
                if (left < 0 && -left > mDeleteWidth) {
                    return -mDeleteWidth;
                } else if (left > 0) {
                    return 0;
                }
            } else if (child == mDeleteView) {
                if (left < mContentView.getMeasuredWidth() - mDeleteWidth) {
                    return mContentView.getMeasuredWidth() - mDeleteWidth;
                } else if (left > mContentView.getMeasuredWidth()) {
                    return mContentView.getMeasuredWidth();
                }
            }
            //确定要移动多少
            return left;
        }

        //当位置改变时候发生移动
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            ViewCompat.postInvalidateOnAnimation(SweepView.this);
            int contentWidth = mContentView.getMeasuredWidth();
            int contentHeight = mContentView.getMeasuredHeight();
            int deleteHeight = mContentView.getMeasuredWidth();
            //如果移动是内容的view
            if (changedView == mContentView) {
                mDeleteView.layout(contentWidth + left, 0, contentWidth + left + mDeleteWidth, deleteHeight);
                //如果移动是删除的view
            } else if (changedView == mDeleteView) {
                mContentView.layout(left - contentWidth, 0, left, contentHeight);
            }
        }
        //Up时回调

        /**
         * @param releasedChild 松开了哪个 View
         * @param xvel          速率
         * @param yvel
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int left = mContentView.getLeft();
//            int contentWidth = mContentView.getMeasuredWidth();
//            int contentHeight = mContentView.getMeasuredHeight();
//            int deleteHeight = mDeleteView.getMeasuredHeight();
//            int deleteWidth = mDeleteView.getMeasuredWidth();
            //移动到mDeleteWidth/2f,判断内容区域是否关闭或者打开
            if (-left < mDeleteWidth / 2f) {
//
//                //布局内容区域
//                mContentView.layout(0, 0, contentWidth, contentHeight);
//                mDeleteView.layout(mContentView.getMeasuredWidth(), 0, contentWidth + deleteWidth, deleteWidth);
                close();
            } else {
//                mContentView.layout(-mDeleteWidth, 0, contentWidth - mDeleteWidth, contentHeight);
//                mDeleteView.layout(contentWidth - mDeleteWidth, 0, contentWidth, deleteHeight);
                open();
            }
            ViewCompat.postInvalidateOnAnimation(SweepView.this);
            super.onViewReleased(releasedChild, xvel, yvel);
        }
    }

    //关闭
    protected void close() {
        isOpened = false;
        if (mListener != null) {
            mListener.onSweepChanged(SweepView.this, isOpened);
        }
        viewDragHelper.smoothSlideViewTo(mContentView, 0, 0);
        viewDragHelper.smoothSlideViewTo(mDeleteView, mContentView.getMeasuredWidth(), 0);
    }


    //打开
    protected void open() {
        isOpened = true;
        if (mListener != null) {
            mListener.onSweepChanged(SweepView.this, isOpened);
        }
        viewDragHelper.smoothSlideViewTo(mContentView, -mDeleteWidth, 0);
        viewDragHelper.smoothSlideViewTo(mDeleteView, mContentView.getMeasuredWidth() - mDeleteView.getMeasuredWidth(), 0);
    }

    public boolean isOpened() {
        return isOpened;
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(SweepView.this);
        }
    }

    public void setOnSweepListener(OnSweepListener listener) {
        this.mListener = listener;
    }

    public interface OnSweepListener {
        void onSweepChanged(SweepView view, boolean isOpened);
    }


}
