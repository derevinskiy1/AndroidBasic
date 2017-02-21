package com.example.storm.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * 自动更新App
 */
public class UpdateApp {
    //获取版本代码 （int）
    public static int getVerCode(Context context) {
        int verCode = -1;

        try {
            verCode = context.getPackageManager().getPackageInfo("com.example.storm.ui", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("------", e.getMessage());
        }
        return verCode;
    }

    //获取版本号  （String）
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo("com.example.storm.ui", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("------", e.getMessage());
        }
        return verName;
    }

    /**
     * 在服务端放置最新版本的apk文件，如：http://localhost/myapp/myapp.apk
     * 同时，在服务端放置对应此apk的版本信息调用接口或者文件，如:http://localhost/myapp/ver.json
     * ver.json中的内容为：
     * [{"appname":"jtapp12","apkname":"jtapp-12-updateapksamples.apk","verName":1.0.1,"verCode":2}]
     * 然后，在手机客户端上进行版本读取和检查：
     */
}
