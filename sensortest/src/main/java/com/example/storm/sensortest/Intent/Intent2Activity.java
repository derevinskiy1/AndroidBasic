package com.example.storm.sensortest.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.storm.sensortest.R;

public class Intent2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,getIntent().getData().toString(),Toast.LENGTH_SHORT).show();
    }
}
