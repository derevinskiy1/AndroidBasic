package com.example.storm.sensortest.ReViewtrofit.activity;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhiwenyan on 12/19/16.
 */

public class CustomInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request.url().newBuilder()
                .addQueryParameter("token", "tokenValue")
                .build();
        request = request.newBuilder().url(httpUrl).build();

        return chain.proceed(request);
    }
}
