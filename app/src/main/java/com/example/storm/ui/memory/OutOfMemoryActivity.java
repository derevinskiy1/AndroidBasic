package com.example.storm.ui.memory;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.storm.ui.R;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 内存泄露总结
 * Stack
 * <p>
 * Heap
 * <p>
 * Static Field
 * <p>
 * 常量池
 */
public class OutOfMemoryActivity extends AppCompatActivity {

    @InjectView(R.id.btn_request)
    Button mBtnRequest;
    @InjectView(R.id.ll)
    LinearLayout request_layout;

    private PhantomReference<Bitmap> phantomReference;
    private WeakReference<Bitmap> weakReference;
    private SoftReference<Bitmap> softReference;
    private Bitmap strongReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_of_memory);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.btn_request)
    public void onClick() {
        testReference();
    }

    private void testReference() {
        //模拟内存使用 往一个布局中不断加入ImageView来模拟内存使用
        ImageView imageView = new ImageView(this);
        Bitmap imageBitmap = readBitmapFromResource(getResources(), R.mipmap.c);
        imageView.setImageBitmap(imageBitmap);
        request_layout.addView(imageView);
        //强引用
        if (strongReference == null) {
            strongReference = readBitmapFromResource(getResources(), R.mipmap.c);
        }
        //软
        Log.e("Reference", "StrongReference---->" + strongReference);
        if (softReference == null) {
            softReference = new SoftReference<>(readBitmapFromResource(getResources(), R.mipmap.c));
        }
        Bitmap bitmap = softReference.get();
        Log.e("Reference", "SoftReference---->" + bitmap);
        //弱
        if (weakReference == null) {
            weakReference = new WeakReference<>(readBitmapFromResource(getResources(), R.mipmap.c));
        }
        Bitmap bitmap1 = weakReference.get();
        Log.e("Reference", "WeakReference---->" + bitmap1);
        //需
        if (phantomReference == null) {
            ReferenceQueue<Bitmap> queue = new ReferenceQueue<>();
            phantomReference = new PhantomReference<>(readBitmapFromResource(getResources(), R.mipmap.c), queue);
        }
        Bitmap bitmap2 = phantomReference.get();
        Log.e("Reference", "PhantomReference---->" + bitmap2);
    }

    public Bitmap readBitmapFromResource(Resources resources, int resourcesId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        return BitmapFactory.decodeResource(resources, resourcesId, options);
    }
}
