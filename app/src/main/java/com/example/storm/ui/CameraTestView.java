package com.example.storm.ui;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CameraTestView extends View {
    private Paint paint;
    private Camera camera;
    private Matrix matrix;

    public CameraTestView(Context context) {
        this(context, null);
    }

    public CameraTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        camera = new Camera();
        matrix = new Matrix();

        setBackgroundColor(Color.parseColor("#3f51b5"));
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#ff4081"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        matrix.reset();
        camera.save();
        camera.rotateY(60);
        camera.getMatrix(matrix);
        camera.restore();
/**
 matrix.setScale(interpolatedTime, interpolatedTime);
 matrix.preTranslate(-centerX, -centerY);
 matrix.postTranslate(centerX, centerY);
 经常在中心缩放的应用中看到这段代码.
 preTranslate是指在setScale前,平移,postTranslate是指在setScale后平移
 注意他们参数是平移的距离,而不是平移目的地的坐标!
 由于缩放是以(0,0)为中心的,所以为了把界面的中心与(0,0)对齐,就要preTranslate(-centerX, -centerY),
 setScale完成后,调用postTranslate(centerX, centerY),再把图片移回来,这样看到的动画效果就是activity的界面图片从中心不停的缩放了
 */
        matrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        matrix.postTranslate(getWidth() / 2, getHeight() / 2);

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.b, option);
        option.inSampleSize = calculateInSampleSize(option, getWidth() / 2, getHeight() / 2);
        option.inJustDecodeBounds = false;
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.b, option), matrix, paint);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
