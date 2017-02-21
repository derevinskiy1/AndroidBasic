package com.example.storm.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public class TestViewGroup extends ViewGroup {
    private View contentView;
    private int contentWidth;
    private int contentHeight;

    public TestViewGroup(Context context) {
        this(context, null);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        ViewGroup.LayoutParams params = contentView.getLayoutParams();
        contentWidth = params.width;
        contentHeight = params.height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("---------" + widthMeasureSpec + "," + heightMeasureSpec);
        int contentWidthMeasureSpec = MeasureSpec.makeMeasureSpec(contentWidth, MeasureSpec.getMode(MeasureSpec.EXACTLY));
        int contentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(contentHeight, MeasureSpec.getMode(MeasureSpec.EXACTLY));
        contentView.measure(contentWidthMeasureSpec, contentHeightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        contentView.layout(((this.getMeasuredWidth() - contentWidth)) / 2 / 2, ((this.getMeasuredHeight() - contentView.getMeasuredHeight()) / 2 / 2),
                contentView.getMeasuredWidth() + this.getMeasuredWidth() / 2, contentView.getMeasuredHeight() + this.getMeasuredHeight() / 2);
    }

    float downX;
    float downY;
    float moveX;
    float moveY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                System.out.println("-------onInterceptTouchEvent------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = event.getX();
                moveY = event.getY();
                System.out.println("-------" + event.getX() + "," + event.getY());
                if (Math.abs(moveX - downX) - Math.abs(moveY - downY) > 0) {
                    System.out.println("-------onInterceptTouchEvent------ACTION_MOVE");
                    System.out.println("-------onInterceptTouchEvent------横向移动");
                    return true;
                }
                downX = moveX;
                downY = moveY;
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-------onInterceptTouchEvent------ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("-------ViewGroup------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("-------ViewGroup------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-------ViewGroup------ACTION_UP");
                break;
        }
        return false;
    }
}
