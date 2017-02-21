package com.example.storm.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Base64
 */
public class Base64Activity extends AppCompatActivity {
    private Button aes;
    private Button des;
    private Button rsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base64);
        ImageView imageView = (ImageView) findViewById(R.id.img1);
        //获取一张图片
        Bitmap sourceBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //压缩
        sourceBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        byte[] sourceBitmapArr = out.toByteArray();
        //----->String
        String sourceBitmapString = Base64.encodeToString(sourceBitmapArr, Base64.DEFAULT); //编码    -----》json
        //base64-->String-->byte[]

        byte[] bitmapDecodeByteArr = Base64.decode(sourceBitmapString, Base64.DEFAULT);  //解码

        //byte[]---->Bitmap

        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapDecodeByteArr, 0, bitmapDecodeByteArr.length);
        imageView.setImageBitmap(bitmap);

        //aes 算法
        aes = (Button) findViewById(R.id.aes);
        aes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        //aes 算法

        des = (Button) findViewById(R.id.des);
        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rsa = (Button) findViewById(R.id.rsa);
        rsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
