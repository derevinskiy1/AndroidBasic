package com.example.storm.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


public class CircleImageView extends ImageView {
    private Paint paint;
    private Bitmap mBitmap;
    Bitmap bitmapSrc;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        //获取drawable的宽高
        if (null != drawable) {
            bitmapSrc = ((BitmapDrawable) drawable).getBitmap();
            Bitmap bitmap = getBitmap(bitmapSrc);
//        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
//                new RectF(0, 0, bitmapSrc.getWidth(), bitmapSrc.getHeight()), null);
            paint.reset();
            canvas.drawBitmap(bitmap, 0, 0, paint);
        } else {
            super.onDraw(canvas);
        }
    }
    public Bitmap getBitmap(Bitmap bitmap) {
        mBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        //   final Rect rect = new Rect(0, 0, bitmapSrc.getWidth(), bitmapSrc.getHeight());
        paint.setAntiAlias(true);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
        //canvas.drawRoundRect(new RectF(0, 0, bitmapSrc.getWidth(), bitmapSrc.getHeight()), 80, 80, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas.drawBitmap(bitmapSrc, rect, rect, paint);
        canvas.drawBitmap(bitmapSrc, 0, 0, paint);
        return mBitmap;
    }
}
