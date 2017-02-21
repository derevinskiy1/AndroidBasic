package com.example.storm.sensortest.RxJavaTest;

import java.util.ArrayList;



public class Student {
    private String name;
    private Course  course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    private ArrayList<Course> mCourseArrayList=new ArrayList<>();

    public ArrayList<Course> getCourseArrayList() {
        return mCourseArrayList;
    }

    public void setCourseArrayList(ArrayList<Course> courseArrayList) {
        mCourseArrayList = courseArrayList;
    }

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
