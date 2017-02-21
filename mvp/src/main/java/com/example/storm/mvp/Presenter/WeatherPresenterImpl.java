package com.example.storm.mvp.Presenter;

import android.content.Context;

import com.example.storm.mvp.View.WeatherView;
import com.example.storm.mvp.bean.Weather;
import com.example.storm.mvp.model.WeatherModel;
import com.example.storm.mvp.model.WeatherModelImpl;

//Presenter作为中间层，持有View和Model的引用
public class WeatherPresenterImpl implements WeatherPresenter, OnWeatherListener {
    private WeatherView weatherView;
    private WeatherModel weatherModel;

     public WeatherPresenterImpl(WeatherView weatherView) {
        this.weatherView = weatherView;
        weatherModel = new WeatherModelImpl();
    }

    @Override
    public void getWeather(Context context) {
        weatherView.showLoading();
        weatherModel.loadWeather(context, this);
    }

    @Override
    public void onSuccess(Weather weather) {
        weatherView.hideLoading();
        weatherView.setWeatherInfo(weather);
    }

    @Override
    public void onError() {
        weatherView.hideLoading();
        weatherView.showError();
    }
}
