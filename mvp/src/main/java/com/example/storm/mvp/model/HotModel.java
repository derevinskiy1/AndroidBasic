package com.example.storm.mvp.model;

/**
 * Created by Administrator on 2016/6/23.
 */

import com.android.volley.RequestQueue;
import com.example.storm.mvp.Presenter.OnHotListener;

/**
 * 天气Model接口
 */
public interface HotModel {
    void loadHot(RequestQueue queue, OnHotListener listener);
}
