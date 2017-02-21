package com.example.storm.mvp.View;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;


public class TestIntentService extends IntentService {

    public TestIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    private final class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

    }
}
