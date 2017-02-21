package com.example.storm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {

    @InjectView(R.id.btn1)
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn1)
    public void onClick() {

        //1、调用相机
        //   File mPhotoFile = new File(folder,filename);
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //   Uri fileUri = Uri.fromFile(mPhotoFile);
        //    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(captureIntent, 0x1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
