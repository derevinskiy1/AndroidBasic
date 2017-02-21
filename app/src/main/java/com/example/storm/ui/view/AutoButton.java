package com.example.storm.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;


public class AutoButton extends Button {

    public AutoButton(Context context) {
        this(context, null);
    }

    public AutoButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("-------", "dispatchTouchEvent: 按下");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("-------", "dispatchTouchEvent: 移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("-------", "dispatchTouchEvent: 抬起");
                break;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("-------", "onTouchEvent: 按下");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("-------", "onTouchEvent: 移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("-------", "onTouchEvent: 抬起");
                break;
        }
        return false;
    }
}
