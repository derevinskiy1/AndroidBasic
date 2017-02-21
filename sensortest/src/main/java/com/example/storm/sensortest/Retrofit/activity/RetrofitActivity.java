package com.example.storm.sensortest.Retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.storm.sensortest.R;
import com.example.storm.sensortest.Retrofit.bean.HttpMethods;
import com.example.storm.sensortest.Retrofit.bean.MovieEntity;
import com.example.storm.sensortest.Retrofit.bean.Subject;
import com.example.storm.sensortest.Retrofit.interface_test.MovieService;
import com.example.storm.sensortest.Retrofit.interface_test.MovieService2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class RetrofitActivity extends AppCompatActivity {
    private Subscriber<List<Subject>> subscriber1;
    private Subscriber<MovieEntity> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        //   getMovie();
        //getMovieAndRxJava();
        getMovies();
    }

    private void getMovies() {
        subscriber1 = new Subscriber<List<Subject>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Subject> movieEntities) {
                System.out.println("-----httpMethods" + movieEntities.get(0).getTitle());
            }
        };
        HttpMethods.getInstance().getTopMovie(subscriber1, 0, 10);
    }

    String baseUrl = "https://api.douban.com/v2/movie/";

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
                System.out.println("--返回结果:" + response.body().getTitle());
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {

            }
        });
    }

    //结合RxJava
    private void getMovieAndRxJava() {
        String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        MovieService2 movieService = retrofit.create(MovieService2.class);
        //使用RxJava，你可以使用subscribeOn()指定观察者代码运行的线程，使用observerOn()指定订阅者运行的线程：
        movieService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                //    .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RetrofitActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        System.out.println("-----与Rxjava结合" + movieEntity.getTitle());

                    }
                });
    }
}
