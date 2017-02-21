package com.example.storm.sensortest.Retrofit.interface_test;

import com.example.storm.sensortest.Retrofit.bean.MovieEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


//https://api.douban.com/v2/movie/top250?start=0&count=10

public interface MovieService2 {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
