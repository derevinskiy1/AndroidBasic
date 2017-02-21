package com.example.storm.mvp.View;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

//// HandlerThread extends Thread  ServiceHandler

public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.example.storm.mvp.View.action.FOO";
    public static final String ACTION_BAZ = "com.example.storm.mvp.View.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.storm.mvp.View.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.storm.mvp.View.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                Toast.makeText(this,param1,Toast.LENGTH_SHORT).show();
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }


    private void handleActionFoo(String param1, String param2) {
    }


    private void handleActionBaz(String param1, String param2) {
    }
}
