package com.example.storm.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private TestView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TestView) findViewById(R.id.textView);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("-------textView------ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("-------textView------ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("-------textView------ACTION_UP");
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("-------MainActivity------ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("-------MainActivity------ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("-------MainActivity------ACTION_UP");
                break;
        }
        return false;
    }
}