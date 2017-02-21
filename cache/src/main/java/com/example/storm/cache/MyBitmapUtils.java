package com.example.storm.cache;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 1. 自定义的图片缓存工具类（MyBitmapUtils）
 * * 通过 new MyBitmapUtils().display(ImageView ivPic, String url) 提供给外部方法进行图片缓存的接口
 * 参数含义：ivPic 用于显示图片的ImageView，url 获取图片的网络地址
 */
public class MyBitmapUtils {
    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public MyBitmapUtils() {
        mMemoryCacheUtils = new MemoryCacheUtils();  //内存缓存
        mLocalCacheUtils = new LocalCacheUtils();  //本地缓存
        mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils, mMemoryCacheUtils); //网络缓存
    }

    public void disPlay(ImageView ivPic, String url) {
        //   ivPic.setImageResource(R.mipmap.ic_launcher);
        Bitmap bitmap;
        //内存缓存
        bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap);
            System.out.println("从内存获取图片啦.....");
            return;
        }

        //本地缓存
        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap);
            System.out.println("从本地获取图片啦.....");
            //从本地获取图片后,保存至内存中
            mMemoryCacheUtils.setBitmapToMemory(url, bitmap);
            return;
        }
        //网络缓存
        mNetCacheUtils.getBitmapFromNet(ivPic, url);
    }
}
