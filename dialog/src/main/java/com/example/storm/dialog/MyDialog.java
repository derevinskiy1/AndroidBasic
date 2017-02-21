package com.example.storm.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MyDialog extends AlertDialog {
    public MyDialog(Context context, View layout, int style) {
        this(context, 500, 500, layout, style);
    }

    public MyDialog(Context context, int width, int height, View layout, int style) {
        super(context, style);
        setContentView(layout);
        Window window = getWindow();

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = width;
        params.height = height;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
}
