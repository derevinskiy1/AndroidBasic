package com.example.storm.sensortest.okhttp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.storm.sensortest.R;
import com.squareup.okhttp.FormEncodingBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class Main2Activity extends AppCompatActivity {
    OkHttpClient mOkHttpClient;
    private String url = "http://www.baidu.com";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mOkHttpClient = new OkHttpClient();
        getRequest();
        postRequest();
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.submit()

//        test();
    }

    //通过 OkHttpClient 将构建的 Request 转换为Call，然后在RealCall中进行异步或同步任务，
//最后通过一些的拦截器 interceptor 发出网络请求和得到返回的 response 。
    //get请求
    private void test() {
        mOkHttpClient = new OkHttpClient();
        //Request是OkHttp中访问的请求，Builder是辅助类。Response即OkHttp中的响应。
        Request request = new Request.Builder()
                .url(url).build();
        Call call = mOkHttpClient.newCall(request);
        mOkHttpClient.newCall(request).execute()
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.i("------", response.body().toString());
                }
            }
        });
    }

    //POST提交Json数据
    private String post(String url, String json) {
        //使用Request的post方法来提交请求体RequestBody
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }

//    private String post1(String url, String json) throws IOException {
//
////        RequestBody formBody = new FormEncodingBuilder()
////                .add("platform", "android")
////                .add("name", "bug")
////                .add("subject", "XXXXXXXXXXXXXXX")
////                .build();
////
////        Request request = new Request.Builder()
////                .url(url)
////                .post(formBody)
////                .build();
////
////        Response response = mOkHttpClient.newCall(request).execute();
////        if (response.isSuccessful()) {
////            return response.body().string();
////        } else {
////            throw new IOException("Unexpected code " + response);
////        }
//  }

    OkHttpClient client = new OkHttpClient();

    private void getRequest() {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url("http://www.wooyun.org")
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("WY", "打印GET响应的数据：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private String postRequest() throws IOException {
        com.squareup.okhttp.RequestBody formBody = new FormEncodingBuilder()
                .add("platform", "android")
                .add("name", "bug")
                .add("subject", "XXXXXXXXXXXXXXX")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
}
