package com.example.storm.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.provider.Telephony.SMS_RECEIVED")) {
            Log.i("------", "onReceive1: android.provider.Telephony.SMS_RECEIVED");
        }
    }
}
