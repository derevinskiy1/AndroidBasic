package com.example.storm.sensortest.Thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.storm.sensortest.R;

import java.util.concurrent.Executors;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    private void test(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
//    private void test1(){
//        ThreadPoolExecutor executor=new ThreadPoolExecutor(
//                1,Integer.MAX_VALUE,5, TimeUnit.SECONDS,new LinkedBlockingDeque<>(),new
//        );
//    }

}
