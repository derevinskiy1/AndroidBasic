package com.example.storm.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver1 extends BroadcastReceiver {
    public MyReceiver1() {


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.provider.Telephony.SMS_RECEIVED")) {
            Log.i("------", "onReceive2: android.provider.Telephony.SMS_RECEIVED");
        }

    }


    /**
     *
     下面是广播接收者的生命周期以及一些细节部分：

     1.广播接收者的生命周期是非常短暂的，在接收到广播的时候创建，onReceive()方法结束之后销毁；

     2.广播接收者中不要做一些耗时的工作，否则会弹出Application No Response错误对话框；

     3.最好也不要在广播接收者中创建子线程做耗时的工作，因为广播接收者被销毁后进程就成为了空进程，很容易被系统杀掉；

     4.耗时的较长的工作最好放在服务中完成。
     */
}
