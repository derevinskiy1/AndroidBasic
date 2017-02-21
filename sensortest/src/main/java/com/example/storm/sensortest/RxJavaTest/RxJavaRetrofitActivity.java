package com.example.storm.sensortest.RxJavaTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storm.sensortest.R;
import com.example.storm.sensortest.RxJavaTest.Entity.MovieEntity;
import com.example.storm.sensortest.RxJavaTest.interfaces.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaRetrofitActivity extends AppCompatActivity {

    Button mClickMeBN;
    TextView mResultTV;
    String baseUrl="https://api.douban.com/v2/movie/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_retrofit);
        mClickMeBN=(Button)findViewById(R.id.click_me_BN);
        mResultTV=(TextView)findViewById(R.id.result_TV);
        mClickMeBN.setOnClickListener(v -> OkHttpgetMovie());
    }
    private void getMovie(){
        Toast.makeText(this,mResultTV.getText().toString(),Toast.LENGTH_SHORT).show();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService movieService=retrofit.create(MovieService.class);
        Call<MovieEntity> movieEntityCall=movieService.getTopMovie(0,10);
        movieEntityCall.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                mResultTV.setText(response.body().toString());
            }
            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                Toast.makeText(RxJavaRetrofitActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void RxJavaGetMovie(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        MovieService movieService=retrofit.create(MovieService.class);
        movieService.RxJavaGetTopMovie(0,10)
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
                        Toast.makeText(RxJavaRetrofitActivity.this,movieEntity.getCount()+"",Toast.LENGTH_SHORT).show();
                    }
                });
    }
     private void OkHttpgetMovie(){
         Subscriber<MovieEntity> subscriber =new Subscriber<MovieEntity>() {
             @Override
             public void onCompleted() {
             }
             @Override
             public void onError(Throwable e) {
             }
             @Override
             public void onNext(MovieEntity movieEntity) {
                 Toast.makeText(RxJavaRetrofitActivity.this,movieEntity.getCount()+"",Toast.LENGTH_SHORT).show();
             }
         };
         HttpMethods.getInstance().getTopMovie(subscriber,0,10);
  }

}
