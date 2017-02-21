package com.example.storm.sensortest.Retrofit.interface_test;

import com.example.storm.sensortest.Retrofit.bean.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


//https://api.douban.com/v2/movie/top250?start=0&count=10

public interface MovieService {
    @GET("top250")
    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

}
