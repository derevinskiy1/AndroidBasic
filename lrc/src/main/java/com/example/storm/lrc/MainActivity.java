package com.example.storm.lrc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MyService.MyBinder binder;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(8) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return super.sizeOf(key, value);
            }
        };
    }

    Intent intent;
    ServiceConnection serviceConnection;

    @Override
    protected void onStart() {
        super.onStart();
        intent = new Intent(this, MyService.class);
        startService(intent);
        System.out.println("-------开启一个Service");

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = (MyService.MyBinder) service;
                System.out.println("------绑定一个Service");
                System.out.println("------" + binder.getCount());
                flag = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                flag = false;
            }
        };
        bindService(new Intent(this, MyService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
        unbindService(serviceConnection);
        if (binder != null) {
            flag = false;
        }
        System.out.println("-----停止一个服务");
        Intent intent1 = new Intent();
        intent1.setAction("com.example.storm.barod");
        intent1.putExtra("data", "开启一个广播");
        sendBroadcast(intent1);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
