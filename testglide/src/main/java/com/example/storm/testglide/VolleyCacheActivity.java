package com.example.storm.testglide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Volley的缓存
 */
public class VolleyCacheActivity extends AppCompatActivity {
    private String url = "http://c.m.163.com/nc/article/headline/T1348647853363/0-10.html";

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_cache);
        mRequestQueue = Volley.newRequestQueue(this);
        if (mRequestQueue.getCache().get(url) == null) {
            test();
        }else{
            Log.i("=====Cache", "onResponse: " + (new String(mRequestQueue.getCache().get(url).data)));
        }
    }

    private void test() {
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("=====", "onResponse: " + jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        mRequestQueue.add(request);
    }
}

