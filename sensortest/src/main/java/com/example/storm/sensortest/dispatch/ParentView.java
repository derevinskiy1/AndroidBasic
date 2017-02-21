package com.example.storm.sensortest.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


public class ParentView extends RelativeLayout {
    private View childView;

    public ParentView(Context context) {
        this(context, null);
    }

    public ParentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        childView = getChildAt(0);
    }

    //测量子布局
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("-----ViewGroup---dispatchTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-----ViewGroup---dispatchTouchEvent---ACTION_UP");
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("-----ViewGroup---onInterceptTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-----ViewGroup---onInterceptTouchEvent---ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("-----ViewGroup---onTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-----ViewGroup---onTouchEvent---ACTION_UP");
                break;

        }
        return super.onTouchEvent(event);
    }

    //决定子布局的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
    }
}
