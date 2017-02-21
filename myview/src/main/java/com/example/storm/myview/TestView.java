package com.example.storm.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class TestView extends View {
    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("-------View------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("-------View------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-------View------ACTION_UP");
                break;
        }
        return false;
    }
}
