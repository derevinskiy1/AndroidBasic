package com.example.storm.sensortest.ReViewtrofit.entity;


import java.util.List;

public class Student {
    private String name;

    public Student() {

    }

    public Student(String name, List<Course> coures) {
        this.name = name;
        this.coures = coures;
    }

    public Student(String name) {
        this.name = name;
    }

    private List<Course> coures;

    public void setCoures(List<Course> coures) {
        this.coures = coures;
    }

    public List<Course> getCoures() {
        return coures;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
