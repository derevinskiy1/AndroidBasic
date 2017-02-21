package com.example.storm.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.storm.ui.R;

/**
 * PathMeasure是一个用来测量Path的类
 */
public class PathMeasureView extends View {
    private Paint mPaint;

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pathMeasure(canvas);

    }

    private void pathMeasure(Canvas canvas) {
       canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path = new Path();
        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);
        /**
         * forceClosed 为 false 测量的是当前 Path 状态的长度， forceClosed 为 true，则不论Path是否闭合测量的都是 Path 的闭合长度。
         */
        PathMeasure measure1 = new PathMeasure(path, false);
        PathMeasure measure2 = new PathMeasure(path, true);
        Log.e("TAG", "forceClosed=false---->" + measure1.getLength());
        Log.e("TAG", "forceClosed=true----->" + measure2.getLength());
        canvas.drawPath(path, mPaint);
    }

    private void pathMeasure2(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);          // 平移坐标系
        Path path = new Path();                                     // 创建Path并添加了一个矩形
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        Path dst = new Path();                                      // 创建用于存储截取后内容的 Path
        dst.lineTo(-300, -300);                                     // <--- 在 dst 中添加一条线段
        PathMeasure measure = new PathMeasure(path, false);         // 将 Path 与 PathMeasure 关联
        /**
         *
         参数	作用	备注
         返回值(boolean)	判断截取是否成功	true 表示截取成功，结果存入dst中，false 截取失败，不会改变dst中内容
         startD	开始截取位置距离 Path 起点的长度	取值范围: 0 <= startD < stopD <= Path总长度
         stopD	结束截取位置距离 Path 起点的长度	取值范围: 0 <= startD < stopD <= Path总长度
         dst	截取的 Path 将会添加到 dst 中	注意: 是添加，而不是替换
         startWithMoveTo	起始点是否使用 moveTo	用于保证截取的 Path 第一个点位置不变
         */
        measure.getSegment(200, 600, dst, true);                   // 截取一部分 并使用 moveTo 保持截取得到的 Path 第一个点的位置不变
        canvas.drawPath(dst, mPaint);  // 绘制 Path
    }

    private void pathMeasure3(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        Path dst = new Path();
        dst.lineTo(-300, -300);
        PathMeasure pm = new PathMeasure(path, false);
        pm.getSegment(200, 600, dst, false);
        canvas.drawPath(dst, mPaint);
    }

    private void nextContour(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);      // 平移坐标系
        Path path = new Path();
        path.addRect(-100, -100, 100, 100, Path.Direction.CW);  // 添加小矩形
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);  // 添加大矩形
        canvas.drawPath(path, mPaint);                    // 绘制 Path
        PathMeasure measure = new PathMeasure(path, false);     // 将Path与PathMeasure关联
        float len1 = measure.getLength();                       // 获得第一条路径的长度
        measure.nextContour();                                  // 跳转到下一条路径
        float len2 = measure.getLength();                       // 获得第二条路径的长度
        Log.i("LEN", "len1=" + len1);                              // 输出两条路径的长度
        Log.i("LEN", "len2=" + len2);

    }


    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    private void tan(Canvas canvas) {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.a1, options);
        mMatrix = new Matrix();
        canvas.translate(getWidth() / 2, getHeight() / 2);      // 平移坐标系
        Path path = new Path();                                 // 创建 Path
        path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形
        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure
        currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }
        measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势
        mMatrix.reset();                                                        // 重置Matrix
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合
        canvas.drawPath(path, mPaint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);                     // 绘制箭头
        invalidate();
    }
    /**
     * 1.通过 tan 得值计算出图片旋转的角度，tan 是 tangent 的缩写，即中学中常见的正切， 其中tan0是邻边边长，tan1是对边边长，而Math中 atan2 方法是根据正切是数值计算出该角度的大小,得到的单位是弧度，所以上面又将弧度转为了角度。
     2.通过 Matrix 来设置图片对旋转角度和位移，这里使用的方法与前面讲解过对 canvas操作 有些类似
     3.页面刷新，页面刷新此处是在 onDraw 里面调用了 invalidate 方法来保持界面不断刷新，但并不提倡这么做，正确对做法应该是使用 线程 或者 ValueAnimator 来控制界面的刷新，关于控制页面刷新这一部分会在后续的 动画部分 详细讲解，同样敬请期待。
     */
}
