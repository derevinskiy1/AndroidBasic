package com.example.storm.sensortest.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.storm.sensortest.R;

public class NotificationActivity extends AppCompatActivity {
    NotificationManager mNotifyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        //获取 NotificationManager 对象：
        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        sendNotification();
        sendSimplestNotificationWithAction();
    }

    //简单的Notification
    private void sendNotification() {
        NotificationCompat.Builder notify = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("最简单的Notification")
                .setContentText("只有小图标、标题、内容")
                .setDefaults(Notification.DEFAULT_SOUND);
        mNotifyManager.notify(0x1, notify.build());

    }

    private void sendSimplestNotificationWithAction() {
        NotificationCompat.Builder notify = new NotificationCompat.Builder(this)
                .setAutoCancel(true)//点击后自动清除
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是带Action的Notification")
                .setContentText("点我会打开MainActivity")
                .setContentIntent(PendingIntent.getActivity(this, 0,
                        new Intent(this, NotificationActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT));
        mNotifyManager.notify(0x3, notify.build());

    }
}
