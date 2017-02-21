package com.example.storm.lrc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private int count;

    public class MyBinder extends Binder {
        public int getCount() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (count <= 10) {
                        try {
                            Thread.sleep(1000);
                            System.out.println("-----" + count);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ++count;
                    }
                }
            }).start();

            return count;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("-----被解绑");

        return super.onUnbind(intent);
    }
}
