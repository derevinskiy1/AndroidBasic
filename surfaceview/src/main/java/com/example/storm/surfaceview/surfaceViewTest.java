package com.example.storm.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class surfaceViewTest extends SurfaceView implements SurfaceHolder.Callback {
    private boolean mIsDrawing;
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;

    public surfaceViewTest(Context context) {
        this(context, null);
    }

    public surfaceViewTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public surfaceViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                draw();
            }
        }).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    int x = 0;


    private void draw() {
        final int width = getWidth();
        int height = getHeight();
        final int centerY = height / 2;
        mCanvas = mSurfaceHolder.lockCanvas();
        mCanvas.drawColor(Color.WHITE);
        mPaint.setTextSize(30);
        mCanvas.drawText("X", 5, 25, mPaint);
        mCanvas.drawText("Y", 5, centerY + 25, mPaint);
        mCanvas.drawLine(0, centerY, width, centerY, mPaint);//在屏幕中心绘制x轴
        mCanvas.drawLine(0, 0, 0, height, mPaint);//绘制Y轴
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        while (x < width) {
            double rad = degreeToRad(x);//角度转换成弧度
            int y = (int) (centerY - Math.sin(rad) * 100);
            mCanvas.drawPoint(x, y, mPaint);
            x++;
        }
        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
    }

    /**
     * 角度转换成弧度
     *
     * @param degree
     * @return
     */
    /**
     * 角（弧度）＝弧长/半径
     * 圆的周长是半径的 2π倍，所以一个周角（360度）是 2π弧度。
     * 半圆的长度是半径的 π倍，所以一个平角（180度）是 π弧度。
     * 据上所述，一个平角是 π 弧度。即 180度＝π弧度
     * 由此可知：
     * 1度＝π/180 弧度 ( ≈0.017453弧度 )
     * 因此，得到 把度化成弧度的公式：
     * 弧度＝度×π/180
     *
     * @param degree
     * @return
     */
    private double degreeToRad(double degree) {
        return degree * Math.PI / 180;
    }
}
