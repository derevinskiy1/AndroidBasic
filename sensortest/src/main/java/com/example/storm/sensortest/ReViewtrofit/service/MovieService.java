package com.example.storm.sensortest.ReViewtrofit.service;


import com.example.storm.sensortest.ReViewtrofit.entity.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiwenyan on 12/20/16.
 */

public interface MovieService {
    @GET("top250")
    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<MovieEntity> getTopMovieRxJava(@Query("start") int start,
                                              @Query("count") int count);
}
