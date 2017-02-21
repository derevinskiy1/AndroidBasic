package com.example.storm.testbmob;

import android.app.Application;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;


public class MyApp extends Application {
    // fb70075461a6d8121101dd6655fa0550
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "fb70075461a6d8121101dd6655fa0550");
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation().save();
        // 启动推送服务
        BmobPush.startWork(this);

    }
}
