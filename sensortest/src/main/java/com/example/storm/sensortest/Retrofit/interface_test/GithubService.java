package com.example.storm.sensortest.Retrofit.interface_test;


import com.example.storm.sensortest.Retrofit.bean.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface GithubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
