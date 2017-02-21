package com.example.storm.translucentbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by fan on 2016/6/7.
 */
public class ImageActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

    }


    public void showMsg(View view){

        Toast.makeText(this, "这是图片界面", Toast.LENGTH_SHORT).show();

    }

}
