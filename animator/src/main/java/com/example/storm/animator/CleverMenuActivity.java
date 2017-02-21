package com.example.storm.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

/**
 * 实现灵动的菜单
 */
public class CleverMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton fab;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private boolean mFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clever_menu);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (!mFlag) {
            start();
        } else {
            close();
        }
    }

    private void start() {

        ObjectAnimator animator = ObjectAnimator.ofFloat(fab, "rotation", 0, 180);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(img1, "translationY", -100f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(img2, "translationY", -80f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(img3, "translationY", -80f);

        ObjectAnimator animator4 = ObjectAnimator.ofFloat(img2, "translationX", -80f);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(img3, "translationX", 80f);


        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setInterpolator(new BounceInterpolator());
        animationSet.playTogether(animator, animator1, animator2, animator3, animator4, animator5);
        animationSet.setDuration(1000);
        animationSet.start();
        mFlag = false;

    }

    private void close() {
        mFlag = true;
    }
}
