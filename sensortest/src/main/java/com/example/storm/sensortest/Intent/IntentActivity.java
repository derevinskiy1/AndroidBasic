package com.example.storm.sensortest.Intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.storm.sensortest.R;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.action.a");
                intent.setAction("com.action.b");
                intent.addCategory("com.category.a");
                intent.addCategory("com.category.b");
                intent.setDataAndType(Uri.parse("www.baidu.com"), "text/plain");
                startActivity(intent);

            }
        });
    }
}
