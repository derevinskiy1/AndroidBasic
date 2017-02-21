package com.example.storm.testglide;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * 我们可以通过GlideMode来定制相关的策略，比如说磁盘缓存，内存缓存，加载图片使用的编码等！
 */
public class GlideConfiguration implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Prefer higher quality images unless we're on a low RAM device
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        System.out.println("---------" + Formatter.formatFileSize(context, defaultMemoryCacheSize) + "," +
                Formatter.formatFileSize(context, defaultBitmapPoolSize));

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);
        //1、内存缓存相关
        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
        // set size & external vs. internal
        int cacheSize100MegaBytes = 104857600;
        //2、磁盘缓存相关 new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes)//内部使用的磁盘缓存区
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));//外部可以访问的磁盘缓存区);
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        //3、根据运行内存情况，自定义对图像的编码格式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            builder.setDecodeFormat(activityManager.isLowRamDevice() ? DecodeFormat.PREFER_RGB_565 : DecodeFormat.PREFER_ARGB_8888);
        }
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
