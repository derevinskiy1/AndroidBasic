package com.example.storm.sensortest.RxJavaTest.interfaces;


import com.example.storm.sensortest.RxJavaTest.Entity.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieService {
    @GET("top250")
    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
    @GET("top250")
    Observable<MovieEntity> RxJavaGetTopMovie(@Query("start") int start, @Query("count") int count);
}
