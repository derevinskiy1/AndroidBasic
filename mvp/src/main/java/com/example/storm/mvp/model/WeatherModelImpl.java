package com.example.storm.mvp.model;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.storm.mvp.Presenter.OnWeatherListener;
import com.example.storm.mvp.bean.Weather;
import com.google.gson.Gson;

import org.json.JSONObject;


public class WeatherModelImpl implements WeatherModel {
    String url = "http://c.3g.163.com/recommend/getSubDocPic?passport=&devId=862949023308336&size=10&version=5.3.6&from=yuedu&net=wifi";

    @Override
    public void loadWeather(Context context, final OnWeatherListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //数据层操作
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Weather weather = new Gson().fromJson(response.toString(), Weather.class);
                //成功时回调
                listener.onSuccess(weather);
            }
        }, null);
        queue.add(request);
    }
}