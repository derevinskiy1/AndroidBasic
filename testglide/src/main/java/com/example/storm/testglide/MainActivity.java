package com.example.storm.testglide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class MainActivity extends AppCompatActivity {
    private String url = "http://b.hiphotos.baidu.com/image/pic/item/d788d43f8794a4c274c8110d0bf41bd5ad6e3928.jpg";
    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.img);
        imageView1 = (ImageView) findViewById(R.id.img1);
        imageView2 = (ImageView) findViewById(R.id.img2);
        test1();
        test2();
        test3();
    }

    private void test1() {
        Glide.with(this).load(url).transform(new BitmapTransformation(this) {
            @Override
            protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
                return pool.get(outWidth / 2, outHeight / 2, Bitmap.Config.ARGB_8888);
            }
            @Override
            public String getId() {
                return MainActivity.class.getSimpleName();
            }
        }).into(imageView);
    }
    private void test2() {
        Glide.with(this).load(url).transform(new OvalTransform(this)).into(imageView1);
    }
    private void test3(){
        Glide.with(this).load(url).transform(new CircleTransform(this)).into(imageView2);

    }

    static class CircleTransform extends BitmapTransformation {

        public CircleTransform(Context context) {
            super(context);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint=new Paint();
            paint.setAntiAlias(true);
//            Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG | Paint
//                   .ANTI_ALIAS_FLAG);
//            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader
//                    .TileMode.CLAMP));
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(squared,0,0,paint);
            return result;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

    static class OvalTransform extends BitmapTransformation {


        public OvalTransform(Context context) {
            super(context);
        }
        public static Bitmap OvalBitmap(BitmapPool pool, Bitmap source) {
            if (null == source) return null;
            int width = source.getWidth();
            int height = source.getHeight();
            Bitmap squared = Bitmap.createBitmap(source, 0, 0, width, height);
            Bitmap result = pool.get(width, height, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG | Paint
                    .ANTI_ALIAS_FLAG);
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader
                    .TileMode.CLAMP));
            canvas.drawOval(new RectF(0, 0, width, height), paint);

            return result;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return OvalBitmap(pool, toTransform);
        }

        @Override
        public String getId() {
            return getClass().getSimpleName();
        }
    }

}
