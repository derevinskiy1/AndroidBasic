package com.example.storm.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * ValueAnimator高级用法
 * <p/>
 * <p/>
 * ObjectAnimator的高级用法
 */


public class MainActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);
        //ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(mTextView, "translationX", -500f, 0f);
//        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mTextView, "rotation", 0, 360f);
//        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(mTextView, "alpha", 1.0f, 0f, 1.0f);
//        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(mTextView, "scaleX", 1.0f, 2f);
//        ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(mTextView, "scaleY", 1.0f, 2f);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(objectAnimator2).with(objectAnimator3).after(objectAnimator1).after(objectAnimator4).with(objectAnimator5);
//        animatorSet.setDuration(5000);
//        animatorSet.start();



        Animator animator=AnimatorInflater.loadAnimator(this,R.animator.set);
        animator.setTarget(mTextView);
        animator.start();
    }
}
