package com.example.storm.mvp.model;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.storm.mvp.Presenter.OnHotListener;
import com.example.storm.mvp.bean.Hot;
import com.google.gson.Gson;

import org.json.JSONObject;


public class HotModelImpl implements HotModel {
    String url = "http://c.m.163.com/nc/article/headline/T1348647853363/0-10.html";

    @Override
    public void loadHot(RequestQueue queue, final OnHotListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Hot hot = new Gson().fromJson(response.toString(), Hot.class);
                //成功时回调
                listener.onSuccess(hot);
                System.out.println("============="+hot.getT1348647853363().get(2).getTitle());
            }
        }, null);
        queue.add(request);
    }
}