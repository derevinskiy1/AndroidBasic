package com.example.storm.glidedemo;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by zhiwenyan on 12/27/16.
 */
//自定义GlideModule 内存缓存  磁盘缓存
//实现方法快捷键 Control+I;
public class ConfigurationGlide implements GlideModule {
    public static final String TAG = ConfigurationGlide.class.getSimpleName();

    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {
        /**
         * 在 Android 中有两个主要的方法对图片进行解码：ARGB8888（每像素4字节存储） 和
         * RGB565（每像素2字节存储）。当然ARGB8888有更高的图片质量，Glide默认使用RGB565进行解码，
         * 所以内存占用相对较小，如果我们想要更高的图片质量，可以设置，如下
         */
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

        /**
         * Glide提供了一个类MemorySizeCalculator，用于决定内存缓存大小以及 bitmap 的缓存池。
         * bitmap 池维护了你 App 的堆中的图像分配。正确的 bitmap 池是非常必要的，因为它避免很多的图像重复回收
         * 这样可以确保垃圾回收器的管理更加合理。它的默认计算实现
         */
        MemorySizeCalculator memorySizeCalculator = new MemorySizeCalculator(context);
        //内部
        int defaultMemoryCacheSize = memorySizeCalculator.getMemoryCacheSize();
        int defalutBitmapPoolSize = memorySizeCalculator.getBitmapPoolSize();
        Log.i(TAG, "applyOptions: " + defaultMemoryCacheSize / 1024 / 1024 + "," + defalutBitmapPoolSize / 1024 / 1024);

        //调整大小
        builder.setMemoryCache(new LruResourceCache((int) (defalutBitmapPoolSize * 1.2)));//内部
        builder.setBitmapPool(new LruBitmapPool((int) (defalutBitmapPoolSize * 1.2)));

        //磁盘缓存
        //内部
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, 1024 * 1024 * 100));//内部磁盘缓存
        //外部
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, 100 * 1024 * 1024));//磁盘缓存到外部存储

        //指定缓存路径
        //公有目录  外部存储
        String downLoadPath = Environment.getDownloadCacheDirectory().getPath();
        Log.i(TAG, "applyOptions: " + downLoadPath);
        builder.setDiskCache(new DiskLruCacheFactory(downLoadPath, defaultMemoryCacheSize));
        //指定缓存目录2 内部存储
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                File cacheLocation = new File(context.getExternalCacheDir(), "cache_dir");
                if (!cacheLocation.exists())
                    cacheLocation.mkdirs();
                Log.i(TAG, "build: " + cacheLocation);
                return DiskLruCacheWrapper.get(cacheLocation, 1024 * 1024 * 100);
            }
        });
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
