package com.example.storm.sensortest.activity;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/9/18.
 */
public class delay {
    //延迟操作
    private void test() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 3000);
    }
    private void test1(){
     new Timer().schedule(new TimerTask() {
         @Override
         public void run() {

         }
     },3000);
    }
    /**
     *
     * 方法三：使用布局的隐藏属性

     这个方法感觉不是那么高大上，但是单纯的实现功能还是没问题的。使用一个Activity，可以用到View.gone() 这个方法。把Acitivity的某些元素移除。

     在布局中初始让闪屏页的布局在主界面中显示，其他的为隐藏

     <LinearLayout xmlns:android=”http://schemas.android.com/apk/res/android”
     android:orientation=”vertical”
     android:layout_width=”fill_parent”
     android:layout_height=”fill_parent”>

     <LinearLayout
     android:id=”@+id/splashscreen”
     android:orientation=”vertical”
     android:layout_width=”fill_parent”
     android:layout_height=”fill_parent”>
     <ImageView android:layout_width=”wrap_content”
     android:layout_height=”wrap_content”
     android:src=”@drawable/splash”
     android:layout_gravity=”center”
     android:layout_marginTop=”130px”/>
     </LinearLayout>

     <View android:id=”@+id/browser”
     android:layout_width=”fill_parent”
     android:layout_height=”fill_parent”
     android:layout_weight=”1″/>
     </LinearLayout>

     此处，首先我们让splashscreen显示，让browser不显示，在三秒过后发送一个消息去将splashscreen不显示，将browser显示。

     */

}
