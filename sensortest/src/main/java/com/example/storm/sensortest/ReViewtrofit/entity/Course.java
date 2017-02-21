package com.example.storm.sensortest.ReViewtrofit.entity;

/**
 * Created by zhiwenyan on 12/19/16.
 */

public class Course {

    public Course(String name) {
        this.name = name;
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
