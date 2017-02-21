package com.example.storm.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class CounterDownActivity extends AppCompatActivity {
    private Button btn;
    private MyCount count;
    private int time = 60;
    private Timer mTimer = new Timer();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x1) {
                if (time > 0) {
                    time--;
                    mHandler.sendMessageDelayed(obtainMessage(0x1), 1000);
                    btn.setText(time + "s");
                    btn.setClickable(false);
                } else {
                    btn.setText("重新发送");
                    btn.setClickable(true);
                }
            }
            super.handleMessage(msg);
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_down);
        btn = (Button) findViewById(R.id.btn_time);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                count = new MyCount(60000, 1000);
//                count.start();
                test1();
            }
        });

    }

    private void test() {
        mHandler.sendEmptyMessage(0x1);
    }

    private void test1() {
        mTimer.schedule(mTimerTask, 1000, System.currentTimeMillis());
    }

    TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (time > 0) {
                        time--;
                        btn.setText(time + "s");
                    } else {
                        mTimer.cancel();
                    }

                }
            });
        }
    };


    /*定义一个倒计时的内部类*/
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            btn.setClickable(true);
            btn.setText("重新发送");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn.setClickable(false);
            btn.setText(millisUntilFinished / 1000 + "秒后可重发");
        }
    }

}
