package com.example.storm.animator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    private Paint paint;
    private Point point;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (point == null) {
            point = new Point(50f, 50f);
            drawCircle(canvas);
            startAnim();
        } else {
            drawCircle(canvas);
        }
        super.onDraw(canvas);
    }

    private void drawCircle(Canvas canvas) {
        float x = point.getX();
        float y = point.getY();
        canvas.drawCircle(x, y, 50f, paint);
    }

    private void startAnim() {
        Point startPoint = new Point(50f, 50f);
        Point endPoint = new Point(getWidth() - 50f, getHeight() - 50f);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                point = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setDuration(5000);
        anim.start();

    }

}
