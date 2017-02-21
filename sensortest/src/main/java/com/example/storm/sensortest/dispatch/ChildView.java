package com.example.storm.sensortest.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class ChildView extends View {
    public ChildView(Context context) {
        this(context, null);
    }

    public ChildView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChildView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("-----View---dispatchTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-----View---dispatchTouchEvent---ACTION_UP");
                break;

        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("-----View---onTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-----View---onTouchEvent---ACTION_UP");
                break;
        }
       // 子View默认返回-----false
        return super.onTouchEvent(event);
    }

}