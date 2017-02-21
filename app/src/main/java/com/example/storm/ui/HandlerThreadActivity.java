package com.example.storm.ui;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HandlerThreadActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        HandlerThread handlerThread = new HandlerThread("handler");
        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }
    /**
     * Android 系统接口 HandlerThread 继承了 Thread，它是一个可以使用 Handler 的 Thread，
     * 一个具有消息循环的线程。run()方法中通过 Looper.prepare() 来创建消息队列，通过 Looper.loop() 来开启消息循环。
     * 可以在 run() 方法中执行耗时的任务，而 HandlerThread 内部创建了消息队列外界需要通过 Handler 的方式来通知 HandlerThread
     * 执行一个具体任务；HandlerThread 的 run() 方法是一个无限的循环，可以通过它的 quite() 或 quitSafely() 方法来终止线程的执行；
     */
}
