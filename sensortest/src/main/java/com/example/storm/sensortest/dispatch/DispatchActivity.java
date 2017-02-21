package com.example.storm.sensortest.dispatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.example.storm.sensortest.R;

public class DispatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("-----Activity---dispatchTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-----Activity---dispatchTouchEvent---ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("-----Activity---onTouchEvent---ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-----Activity---onTouchEvent---ACTION_UP");
                break;

        }
        return super.onTouchEvent(ev);
    }

//
//    Activity
//
//    boolean dispatchTouchEvent(MotionEvent event) : 分发事件
//    boolean onTouchEvent(MotionEvent event) : 处理事件的回调（当没有子View消费时才调用该方法）


//    View
//    boolean dispatchTouchEvent(MotionEvent event) : 分发事件（没有子view，用来决定是使用onTouchEvent还是setOnTouchListener）
//    boolean onTouchEvent(MotionEvent event) : 处理事件的回调方法
//    void setOnTouchListener(OnTouchListener listener) : 设置事件监听器
//    void setOnClickListener(OnClickListener l)
//    void setOnLongClickListener(OnClickListener l)
//    void setOnCreateContextMenuListener(OnCreateContextMenuListener l) 用于创建菜单监听

//    ViewGroup:
//    boolean dispatchTouchEvent(MotionEvent event) : 分发事件
//    boolean onInterceptTouchEvent(MotionEvent event) : 拦截事件的回调方法
//    boolean onTouchEvent(MotionEvent event) : 处理事件的回调（当没有子View消费时才调用该方法）


    //View 不消费事件
    /**
     *Activity---dispatchTouchEvent---ACTION_DOWN
     ViewGroup---dispatchTouchEvent---ACTION_DOWN
     ViewGroup---onInterceptTouchEvent---ACTION_DOWN
     View---dispatchTouchEvent---ACTION_DOWN
     View---onTouchEvent---ACTION_DOWN
     ViewGroup---onTouchEvent---ACTION_DOWN
     Activity---onTouchEvent---ACTION_DOWN
     Activity---dispatchTouchEvent---ACTION_UP
     Activity---onTouchEvent---ACTION_UP
     */
    //View消费事件
//
//    -Activity---dispatchTouchEvent---ACTION_DOWN
//    -ViewGroup---dispatchTouchEvent---ACTION_DOWN
//    -ViewGroup---onInterceptTouchEvent---ACTION_DOWN
//    -View---dispatchTouchEvent---ACTION_DOWN
//    -View---onTouchEvent---ACTION_DOWN
//    -Activity---dispatchTouchEvent---ACTION_UP
//    -ViewGroup---dispatchTouchEvent---ACTION_UP
//    -ViewGroup---onInterceptTouchEvent---ACTION_UP
//    -View---dispatchTouchEvent---ACTION_UP
//    -View---onTouchEvent---ACTION_UP


    //ViewGroup拦截事件
//    Activity---dispatchTouchEvent---ACTION_DOWN
//    ViewGroup---dispatchTouchEvent---ACTION_DOWN
//    ViewGroup---onInterceptTouchEvent---ACTION_DOWN
//    ViewGroup---onTouchEvent---ACTION_DOWN
//    Activity---onTouchEvent---ACTION_DOWN
//    Activity---dispatchTouchEvent---ACTION_UP
//    Activity---onTouchEvent---ACTION_UP


/**
 * 一个事件序列是指从手指触摸屏幕开始，到手指离开屏幕结束，这个过程中产生的一系列事件。一个事件序列以ACTION_DOWN事件开始，中间可能经过若干个MOVE，以ACTION_UP事件结束。
 事件的传递过程是由外向内的，即事件总是由父元素分发给子元素
 如果某个View消费了ACTION_DOWN事件，那么通常情况下，这个事件序列中的后续事件也将交由其进行处理，但可以通过调用其父View的onInterceptTouchEvent方法，对后续事件进行拦截
 如果某个View它不消耗ACTION_DOWN事件，那么这个序列的后续事件也不会再交由它来处理
 如果事件没有View对其进行处理，那么最后将有Activity进行处理
 如果事件传递的结果为true，回传的结果直接通过不断调用父View#dispatchTouchEvent方法，传递给Activity；如果事件传递的结果为false，回传的结果不断调用父View#onTouchEvent方法，获取返回结果。
 View默认的onTouchEvent在View可点击的情况下，将会消耗事件，返回true；不可点击的情况下，则不消耗事件，返回false(longClickable的情况，读者可以自行测试，结果与clickable相同)
 如果某个ViewGroup的onInterceptTouchEvent返回为true，那么这个事件序列中的后续事件，不会在进行onInterceptTouchEvent的判断，而是由它的dispatchTouchEvent方法直接传递给onTouchEvent方法进行处理
 如果某个View接收了ACTION_DOWN之后，这个序列的后续事件中，在某一刻被父View拦截了，则这个字View会收到一个ACTION_CANCEL事件，并且也不会再收到这个事件序列中的后续事件
 */
}

