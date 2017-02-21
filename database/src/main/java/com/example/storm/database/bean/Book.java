package com.example.storm.database.bean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

//DataSupport类之后，这些实体类就拥有了进行CRUD操作的能力
public class Book extends DataSupport {
    private int id;
    private String name;
    private int age;
    private String sex;
    private float height;
    private List<Person> personList = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
