package com.example.storm.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class PathView extends View {
    private Paint mPaint = new Paint();             // 创建画笔

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(5);              // 边框宽
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // addRect1(canvas);
       // drawLineTo(canvas);
        addSquare(canvas);
    }

    /**
     * 第一次由于之前没有过操作，所以默认点就是坐标原点O，结果就是坐标原点O到A(200,200)之间连直线(用蓝色圈1标注)。
     *
     * @param canvas
     */
    private void drawLineTo(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path = new Path();
        path.lineTo(200, 200);
        path.lineTo(200, 0);
        canvas.drawPath(path, mPaint);
    }


    private void addRect(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path = new Path();
        path.addRect(new RectF(-200, -200, 200, 200), Path.Direction.CW);
        path.addCircle(0, 0, 200, Path.Direction.CW);
        canvas.drawPath(path, mPaint);
    }


    //多边形
    private void addRect1(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);  // 移动坐标系到屏幕中心
        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
        path.setLastPoint(-300, 300);                // <-- 重置最后一个点的位置
        canvas.drawPath(path, mPaint);
    }

    private void addSquare(Canvas canvas) {
        Path mPath = null;
        if (mPath == null) {
            mPath = new Path();
            mPath.moveTo(0, 0);
            mPath.lineTo(getWidth(), 0);
            mPath.lineTo(getWidth() / 2, getHeight() / 2);
            mPath.close();
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(Color.BLACK);
        }
        canvas.drawPath(mPath, mPaint);
    }
}