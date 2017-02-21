package com.example;


import java.util.ArrayList;

public class CollectionGenFooDemo {

    public static void main(String[] args) {
        CollectionGenFoo<ArrayList> genFoo = new CollectionGenFoo<>(new ArrayList());
        System.out.println("success");
    }
}
