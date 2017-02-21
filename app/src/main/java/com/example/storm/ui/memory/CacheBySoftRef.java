package com.example.storm.ui.memory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

//软/弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，
// Java虚拟机就会把这个软引用加入到与之关联的引用队列中。利用这个队列可以得知被回收的软/弱引用的对象列表，
// 从而为缓冲器清除已失效的软/弱引用。

/**
 * 假设我们的应用会用到大量的默认图片，比如应用中有默认的头像，默认游戏图标等等，
 * 这些图片很多地方会用到。如果每次都去读取图片，由于读取文件需要硬件操作，速度较慢，会导致性能较低。所以我们考虑将图片缓存起来，需要的时候直接从内存中读取。
 * 但是，由于图片占用内存空间比较大，
 * 缓存很多图片需要很多的内存，就可能比较容易发生OutOfMemory异常。这时，我们可以考虑使用软/弱引用技术来避免这个问题发生。以下就是高速缓冲器的雏形
 */
public class CacheBySoftRef {
    private Map<String, SoftReference<Bitmap>> imageCache = new HashMap();

    protected void addBitmapToCache(String path) {
        //强引用的Bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        //软引用的Bitmap
        SoftReference<Bitmap> softReference = new SoftReference<>(bitmap);
        imageCache.put(path, softReference);
    }

    protected Bitmap getBitmapByPath(String path) {
        SoftReference<Bitmap> softBitmap = imageCache.get(path);
        if (softBitmap == null) {
            return null;

        }
        return softBitmap.get();
    }

    //
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *
                (listAdapter.getCount() - 1));
        params.height += 5;// if without this statement,the listview will be a
        // little short
        listView.setLayoutParams(params);
    }
}
