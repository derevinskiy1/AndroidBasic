package com.example.storm.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class URLActivity extends AppCompatActivity {
    String imgurl = "http://b.hiphotos.baidu.com/image/pic/item/d788d43f8794a4c274c8110d0bf41bd5ad6e3928.jpg";
    private ImageView img_url;
    private Bitmap bitmap;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x12) {
                img_url.setImageBitmap(bitmap);

            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        img_url = (ImageView) findViewById(R.id.img_url);
        requestImg();
    }

    private void requestImg() {
        new Thread() {
            @Override
            public void run() {
                try {
                    /**
                     * 使用URL访问网络资源
                     */
                    URL url = new URL(imgurl);
                    InputStream inputStream = url.openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    handler.sendEmptyMessage(0x12);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
