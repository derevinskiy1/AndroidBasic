package com.example.storm.sensortest.ReViewtrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.storm.sensortest.R;
import com.example.storm.sensortest.ReViewtrofit.entity.MovieEntity;
import com.example.storm.sensortest.ReViewtrofit.service.MovieService;
import com.example.storm.sensortest.ReViewtrofit.utils.HttpMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaAndReofitActivity extends AppCompatActivity {
    String baseUrl = "https://api.douban.com/v2/movie/";
    public static final String TAG = RxJavaAndReofitActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_and_reofit);
        getMovie();
        getMovieRxJava();
        HttpMethod.getInstance().getTopMovie(new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                Log.i(TAG, "onNext: " + movieEntity.getTitle());
            }
        }, 0, 10);
    }

    private void getMovie() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieEntity> call = movieService.getTopMovie(0, 10);
        call.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                Log.i(TAG, "onResponse: " + response.body().getCount());
                Log.i(TAG, "onResponse: " + Thread.currentThread().getName());
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void getMovieRxJava() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        MovieService movieService = retrofit.create(MovieService.class);
        movieService.getTopMovieRxJava(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        Log.i(TAG, "onNext: " + movieEntity.getCount());
                        Log.i(TAG, "onNext: " + Thread.currentThread().getName());
                    }
                });
    }
}
