package com.example.storm.mvp.model;


import android.content.Context;

import com.example.storm.mvp.Presenter.OnWeatherListener;

/**
 * 天气Model接口
 */
public interface WeatherModel {
    void loadWeather(Context context, OnWeatherListener listener);
}
