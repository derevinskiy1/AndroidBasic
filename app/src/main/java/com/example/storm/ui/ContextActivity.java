package com.example.storm.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ContextActivity extends AppCompatActivity {
    public static final String TAG = ContextActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        myApp myApp = (myApp) getApplication();
        Log.i(TAG, "onCreate: "+myApp);
        Context context=getApplicationContext();
        Log.i(TAG, "onCreate: "+context);
        Context baseContext=getBaseContext();
        Log.i(TAG, "onCreate: "+baseContext);

    }
}
