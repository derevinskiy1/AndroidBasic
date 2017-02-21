package com.example.storm.ui.memory;

import android.content.Context;


public class AppManager {
    private Context context;
    private static AppManager instance;

    private AppManager(Context context) {
        this.context = context.getApplicationContext();// 使用Application 的context
    }

    public static AppManager getInstance(Context context) {
        if (instance == null) {
            synchronized (AppManager.class) {
                instance = new AppManager(context);
            }
        }
        return instance;
    }
}
