package com.example.storm.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 三级缓存之内存缓存  ---->LruCache
 */

/**
 * 实现方法：
 * 通过 HashMap 键值对的方式保存图片，key为地址，value为图片对象，但因是强引用对象，很容易造成内存溢出可以尝试SoftReference软引用对象
 * 通过 HashMap> SoftReference 为软引用对象（GC垃圾回收会自动回收软引用对象），但在Android2.3+后，系统会优先考虑回收弱引用对象，官方提出使用LruCache
 * 通过 LruCache least recentlly use 最少最近使用算法会将内存控制在一定的大小内, 超出最大值时会自动回收, 这个最大值开发者自己定
 */
public class MemoryCacheUtils {
    // private HashMap<String,Bitmap> mMemoryCache=new HashMap<>();//1.因为强引用,容易造成内存溢出，所以考虑使用下面弱引用的方法
    // private HashMap<String, SoftReference<Bitmap>> mMemoryCache = new HashMap<>();//2.因为在Android2.3+后,系统会优先考虑回收弱引用对象,官方提出使用LruCache
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCacheUtils() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 8;//得到手机最大允许内存的1/8,即超过指定内存,则开始回收
        //需要传入允许的内存最大值,虚拟机默认内存16M,真机不一定相同
        mMemoryCache = new LruCache<String, Bitmap>((int) maxMemory) {
            //用于计算每个条目的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                System.out.println("----" + key + "," + value.getByteCount());
                return value.getByteCount();
            }
        };
    }

    /**
     * 从内存中读图片
     *
     * @param url
     */
    public Bitmap getBitmapFromMemory(String url) {
        //Bitmap bitmap = mMemoryCache.get(url);//1.强引用方法
        /*2.弱引用方法
        SoftReference<Bitmap> bitmapSoftReference = mMemoryCache.get(url);
        if (bitmapSoftReference != null) {
            Bitmap bitmap = bitmapSoftReference.get();
            return bitmap;
        }
        */
        return mMemoryCache.get(url);

    }

    /**
     * 往内存中写图片
     *
     * @param url
     * @param bitmap
     */
    public void setBitmapToMemory(String url, Bitmap bitmap) {
        //mMemoryCache.put(url, bitmap);//1.强引用方法
        /*2.弱引用方法
        mMemoryCache.put(url, new SoftReference<>(bitmap));
        */
        mMemoryCache.put(url, bitmap);
    }
}
