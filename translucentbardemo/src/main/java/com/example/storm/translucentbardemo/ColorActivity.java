package com.example.storm.translucentbardemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by fan on 2016/6/7.
 */
public class ColorActivity extends BaseColorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_color;
    }

    public void showMsg(View view){

        Toast.makeText(this, "这是纯色界面", Toast.LENGTH_SHORT).show();

    }
}
