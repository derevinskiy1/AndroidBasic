package com.example.storm.sensortest.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AnimationSet;
import android.widget.Button;

import com.example.storm.sensortest.R;

public class GoodActivity extends AppCompatActivity {
    private Paint mPaint;
    private AnimationSet mAnimationSet;
    private int mFromY;
    private int mToY;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(v -> Log.i("onClick", "onCreate: "));

    }

}
