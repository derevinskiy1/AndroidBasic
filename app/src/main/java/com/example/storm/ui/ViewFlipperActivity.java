package com.example.storm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ViewFlipperActivity extends AppCompatActivity {

    @InjectView(R.id.ViewFlipper)
    ViewFlipper mViewFlipper;
    @InjectView(R.id.bnt1)
    Button mBnt1;
    @InjectView(R.id.bnt2)
    Button mBnt2;
    @InjectView(R.id.bnt3)
    Button mBnt3;
    @InjectView(R.id.activity_view_flipper)
    LinearLayout mActivityViewFlipper;
    @InjectView(R.id.TextSwitcher)
    TextSwitcher mTextSwitcher;
    int currentStr = 0;
    float y = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        ButterKnife.inject(this);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(ViewFlipperActivity.this);
                tv.setTextSize(30);
                return tv;
            }
        });
        next();
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        y = event.getY();
                        Log.i("----------", "onTouch: " + y);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (event.getY() < y) {
                            mViewFlipper.setInAnimation(ViewFlipperActivity.this, R.anim.up_in);
                            mViewFlipper.setOutAnimation(ViewFlipperActivity.this, R.anim.up_out);
                            mViewFlipper.showNext();
                        }
                        break;
                }
                return false;
            }
        });
    }

    @OnClick({R.id.bnt1, R.id.bnt2, R.id.bnt3, R.id.TextSwitcher})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnt1:
                mViewFlipper.setInAnimation(this, R.anim.up_in);
                mViewFlipper.setOutAnimation(this, R.anim.up_out);
                mViewFlipper.showPrevious();
                mViewFlipper.stopFlipping();
                break;
            case R.id.bnt2:
                mViewFlipper.setInAnimation(this, R.anim.up_in);
                mViewFlipper.setOutAnimation(this, R.anim.up_out);
                mViewFlipper.showNext();
                mViewFlipper.stopFlipping();
                break;
            case R.id.bnt3:
                mViewFlipper.setInAnimation(this, R.anim.up_in);
                mViewFlipper.setOutAnimation(this, R.anim.up_out);
                mViewFlipper.startFlipping();
                break;
            case R.id.TextSwitcher:
                next();
                break;
        }
    }

    private void next() {
        String[] strs = new String[]{"java语言", "C语言", "android开发"};
        mTextSwitcher.setText(strs[currentStr++ % strs.length]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main2_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
