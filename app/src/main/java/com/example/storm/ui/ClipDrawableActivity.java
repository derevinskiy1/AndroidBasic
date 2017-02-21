package com.example.storm.ui;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class ClipDrawableActivity extends AppCompatActivity {
    private ImageView img;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_drawable);
        AppManager.getInstance(this);
        img = (ImageView) findViewById(R.id.img_clip);
        final ClipDrawable clipDrawable = (ClipDrawable) img.getDrawable();
         final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x1)
                    clipDrawable.setLevel(clipDrawable.getLevel() + 200);
            }
        };
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 0x1;
                handler.sendMessage(message);
                if (clipDrawable.getLevel() >= 10000)
                    timer.cancel();
            }
        }, 0, 300);
    }
}
