package com.example.storm.sensortest.RxJava.demo;

import java.util.List;

import rx.Observable;


public interface RxJavaUtils {
    Observable<List<String>> query(String text);

}
