package com.example.storm.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;


public class CustomeProgressDialog extends ProgressDialog {
    public CustomeProgressDialog(Context context, String content, int id) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);
    }
}
