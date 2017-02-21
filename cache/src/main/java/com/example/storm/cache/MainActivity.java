package com.example.storm.cache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

//三级缓存
public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    String url = "http://b.hiphotos.baidu.com/image/pic/item/d788d43f8794a4c274c8110d0bf41bd5ad6e3928.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.img);
        MyBitmapUtils myBitmapUtils = new MyBitmapUtils();
        myBitmapUtils.disPlay(imageView, url);
    }
}
