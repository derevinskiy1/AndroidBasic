package com.example.storm.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.Window;

public class BundleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (savedInstanceState != null) { //判断是否有以前的保存状态信息
//            savedInstanceState.get("Key");
//        }
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        setContentView(R.layout.activity_matrix);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //普通广播
//        Intent intent = new Intent();
//        intent.setAction("android.provider.Telephony.SMS_RECEIVED");
//        this.sendBroadcast(intent);
//        //除了清单文件中以外，也可以直接在代码中订阅
//        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
//        MyReceiver receiver = new MyReceiver();
//        this.registerReceiver(receiver, filter);

        //有序广播
    }

//    protected void onSaveInstanceState(Bundle outState) {
//        //可能被回收内存前保存状态和信息，
//        Bundle data = new Bundle();
//        data.putString("key", "last words before be kill");
//        outState.putAll(data);
//        super.onSaveInstanceState(outState);
//    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        if (savedInstanceState != null) { //判断是否有以前的保存状态信息
//            savedInstanceState.get("Key");
//        }
//        super.onRestoreInstanceState(savedInstanceState);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        mGestureDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }

//    //手势监听器
//    GestureDetector.OnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {
//        @Override
//        public boolean onDoubleTap(MotionEvent e) {
//            return super.onDoubleTap(e);
//        }
//
//    };
//    //创建手势
//    GestureDetector mGestureDetector = new GestureDetector(this, mGestureListener);
}

