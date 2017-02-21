package com.example;


public class GenericsFooDemo {

    public static void main(String[] args) {
        GenericsFoo<String> strFoo = new GenericsFoo<>("Hello Generics!");

        GenericsFoo<Double> douFoo = new GenericsFoo<>(new Double("33"));

        GenericsFoo<Object> objFoo = new GenericsFoo<>(new Object());

        System.out.println("strFoo.getX=" + strFoo.getX());

        System.out.println("douFoo.getX=" + douFoo.getX());

        System.out.println("objFoo.getX=" + objFoo.getX());
    }
}

