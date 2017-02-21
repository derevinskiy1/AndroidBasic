package com.example.storm.sensortest.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/**
 * Created by Administrator on 2016/10/13.
 */

public class GoodView extends View {
    private Paint mPaint;
    private AnimationSet mAnimationSet;
    private int mFromY;
    private int mToY;

    public GoodView(Context context) {
        this(context, null);
    }

    public GoodView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoodView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    //    getLocationOnScreen();
    }

    public void show() {
        mAnimationSet=createAnimation();
        this.startAnimation(mAnimationSet);

    }

    /**
     * 动画
     *
     * @return
     */
    private AnimationSet createAnimation() {
        mAnimationSet = new AnimationSet(true);
        TranslateAnimation translateAnim = new TranslateAnimation(0, 0, mFromY, -mToY);
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.1f);
        mAnimationSet.addAnimation(translateAnim);
        mAnimationSet.addAnimation(alphaAnim);
        mAnimationSet.setDuration(1000);
        mAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        return mAnimationSet;
    }
}
