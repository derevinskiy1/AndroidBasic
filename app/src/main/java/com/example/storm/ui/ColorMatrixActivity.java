package com.example.storm.ui;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

public class ColorMatrixActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private SeekBar seekBar3;
    private ImageView imageView;
    //ColorMatrix 颜色矩阵
    private ColorMatrix colorMatrix = new ColorMatrix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        imageView = (ImageView) findViewById(R.id.img);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar1.setMax(100);
        colorMatrix.setSaturation(1);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar2.setMax(255);
        seekBar3.setOnSeekBarChangeListener(this);
        seekBar3.setMax(255);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            if (seekBar.getId() == R.id.seekBar1) {
                colorMatrix.setSaturation(0);  //饱和度
            } else if (seekBar.getId() == R.id.seekBar2) {
                //色调
                colorMatrix.setRotate(0, progress); //R
                colorMatrix.setRotate(1, progress);  //G
                colorMatrix.setRotate(2, progress);  //B
            } else if (seekBar.getId() == R.id.seekBar3) {
                //亮度
                colorMatrix.setScale(progress, progress, progress, 1);
            }
            ColorMatrixColorFilter cmcf = new ColorMatrixColorFilter(colorMatrix);
            imageView.setColorFilter(cmcf);

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
