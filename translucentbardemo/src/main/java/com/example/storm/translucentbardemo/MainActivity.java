package com.example.storm.translucentbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.Button btnone;
    private android.widget.Button btntwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_one:

                startActivity(new Intent(MainActivity.this, ImageActivity.class));

                break;

            case R.id.btn_two:

                startActivity(new Intent(MainActivity.this, ColorActivity.class));

                break;

        }
    }
}
