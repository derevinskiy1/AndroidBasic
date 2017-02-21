package com.example.storm.sensortest.Retrofit.interface_test;

import com.example.storm.sensortest.Retrofit.bean.HttpResult;
import com.example.storm.sensortest.Retrofit.bean.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


//https://api.douban.com/v2/movie/top250?start=0&count=10

public interface MovieService1 {
    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

}
