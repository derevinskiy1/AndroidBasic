package com.example.storm.testbmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/9/7.
 */
//BmobObject想当于数据库中的一张表，每个属性相当于表的字段
public class Person extends BmobObject {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
