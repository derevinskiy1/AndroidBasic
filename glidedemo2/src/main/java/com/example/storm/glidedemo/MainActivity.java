package com.example.storm.glidedemo;

import android.animation.ObjectAnimator;
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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private String imgUrl = "http://img2.3lian.com/2014/f6/173/d/51.jpg";
    private ImageView img;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.glide_img);
        //  img = (ImageView) findViewById(R.id.glide_img2);
        initGlide();
    }

    private void initGlide() {
        //加载网络图片
        //   Glide.with(this).load(imgUrl).into(mImageView);
        /**
         * placeholder 占位符
         * RequestListener 监听器
         * animate 加载自定义的动画
         * crossfade 设置动画的时间  默认是一个淡入淡出的动画
         */
        Glide.with(this).load(imgUrl).placeholder(R.mipmap.ic_launcher)
                //.animate(R.anim.img_anim) //加载自定义的动画
                .crossFade(1000)
                //   .override(400,500)
                .transform(new GlideRoundImageView(this))
                .listener(new RequestListener<String, GlideDrawable>() {
                    //设置加载错误的监听器
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        mImageView.setImageResource(R.mipmap.r);
                        Log.i(TAG, "onException: " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.i(TAG, "onResourceReady: isFromMemoryCache" + isFromMemoryCache + "," + model);
                        return false;
                    }
                }).into(mImageView);
        //加载动态图片
        //   Glide.with(this).load("http://img1.3lian.com/2015/w4/17/d/64.gif").into(img);
        //java文件设置动画
        ViewPropertyAnimation.Animator animator = new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
                view.setAlpha(0f);
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                fadeAnim.setDuration(2500);
                fadeAnim.start();
            }
        };
        //Glide加载图片大小是自动调整的，他根据ImageView的尺寸自动调整加载的图片大小，并且缓存的时候也是按图片大小缓存，每种尺寸都会保留一份缓存，如果图片不会自动适配到 ImageView，
        // 调用 override(horizontalSize, verticalSize) 。这将在图片显示到 ImageView之前重新改变图片大小
        //Glide.with(context).load("http://img2.3lian.com/2014/f6/173/d/51.jpg").dontAnimate().override(400,600).fitCenter().into(imageView);
        //资源文件加载
//        int ResourceId = R.mipmap.ic_launcher;
//        Glide.with(this).load(ResourceId).into(img);

        //本地文件加载
        //  File file = new File(Environment.getExternalStorageDirectory() + File.separator +  "image", "image.jpg");
        // Glide.with(this).load(file).into(imageView);

        //File file = new File(Environment.getExternalStorageDirectory() + File.separator +  "image", "image.jpg");
        //Uri uri = Uri.fromFile(file);
        //Glide.with(this).load(uri).into(imageView);//uri加载方式

        //URL方式
//        try {
//           URL url=new URL("http://img2.3lian.com/2014/f6/173/d/51.jpg");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Glide.with(this).load(url).into(imageView);//URL加载方式    URL加载方式
    }

    public class GlideRoundImageView extends BitmapTransformation {
        public GlideRoundImageView(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            //  return setBitmapShader(pool, toTransform);
            return setPorterDuffXfermode(pool, toTransform);
        }

        @Override
        public String getId() {
            return getClass().getSimpleName();
        }


    }


    private Bitmap setBitmapShader(BitmapPool pool, Bitmap source) {
        int radius = 100;
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        Log.e("11aa", radius + "");
        return result;
    }

    private Bitmap setPorterDuffXfermode(BitmapPool pool, Bitmap toTransform) {
        if (toTransform == null) return null;
        Bitmap result = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();
        canvas.drawCircle(width / 2, height / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(toTransform, 0, 0, paint);
        return toTransform;
    }
}
