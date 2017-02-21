package com.example.storm.ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


//java8新特性

public class testjava8 {
    public static void main(String[] args) {
        List<String> items = Arrays.asList("a", "b", "c");
        for (String s : items) {
            System.out.println(s);
        }
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }

        });
//        Collections.sort(items,(String a, String b) -> {
//            return b.compareTo(a);
//        });
    }
}
