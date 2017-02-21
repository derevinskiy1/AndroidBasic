package com.example.storm.net;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 使用URLConnection提交请求
 */
public class URLConnectionActivity extends AppCompatActivity {

    @InjectView(R.id.get)
    Button get;
    @InjectView(R.id.post)
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlconnection);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.get, R.id.post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get:
                getRequest();
                break;
            case R.id.post:
                postRequest();
                break;
        }
    }

    private void getRequest() {

    }

    private void postRequest() {

    }
}
