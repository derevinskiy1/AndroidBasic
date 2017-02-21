package com.example.storm.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

public class TestColorMatrixActivity extends AppCompatActivity implements View.OnClickListener {
    private GridLayout mGroup;
    private Button btn1, btn2, btn3;
    private ImageView imageView;
    private Bitmap bitmap;
    private int width;
    private int height;
    private float[] mColorMatrix = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_color_matrix);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        mGroup = (GridLayout) findViewById(R.id.GridLayout);
        btn1 = (Button) findViewById(R.id.change);
        btn2 = (Button) findViewById(R.id.Resize);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.iv);
        imageView.setImageBitmap(bitmap);
        mGroup.post(new Runnable() {
            @Override
            public void run() {
                width = mGroup.getWidth() / 5;
                height = mGroup.getHeight() / 4;
                addEts();
                initMatrix();
            }
        });
    }

    EditText[] ets = new EditText[20];

    private void addEts() {
        EditText editText;
        for (int i = 0; i < 20; i++) {
            editText = new EditText(this);
            ets[i] = editText;
            mGroup.addView(editText, width, height);
        }
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                ets[i].setText(String.valueOf(1));
            } else {
                ets[i].setText(String.valueOf(0));
            }
        }
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf(ets[i].getText().toString());
        }
    }

    private void setImageViewMatrix() {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        imageView.setImageBitmap(bitmap1);

    }

    int[] pixels = new int[]{};
    int color;
    int r, g, b, a;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change:
                getMatrix();
                setImageViewMatrix();
                break;
            case R.id.Resize:
                initMatrix();
                getMatrix();
                setImageViewMatrix();
                break;
            case R.id.btn3:  //像素点数
                bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, width, height);
                //获取每个像素的具体的ARGB值
                for (int i = 0, count = pixels.length; i < count; i++) {
                    color = pixels[i];
                    r = Color.red(color);
                    g = Color.green(color);
                    b = Color.blue(color);
                    a = Color.alpha(color);
                }
                break;
        }
    }
}
