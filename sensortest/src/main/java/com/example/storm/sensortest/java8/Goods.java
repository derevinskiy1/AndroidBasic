package com.example.storm.sensortest.java8;

/**
 * Created by Administrator on 2016/10/18.
 */

public class Goods {
    private class Content implements Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    protected class GDestination implements Destination {
        private String label;

        private GDestination(String whereTo) {
            label = whereTo;
        }

        public String readLabel() {
            return label;
        }
    }

    public Destination dest(String s) {
        return new GDestination(s);
    }

    public Contents cont() {
        return new Content();
    }
}

/**
 * 隐藏你不想让别人知道的操作，也即封装性
 * <p>
 * 内部类的第二个好处——一个内部类对象可以访问创建它的外部类对象的内容，甚至包括私有变量！。
 */
class TestGoods {
    public static void main(String[] args) {
        Goods p = new Goods();
        Contents c = p.cont();
        System.out.println(c.value());
        Destination d = p.dest("Beijing");
        System.out.println(d.readLabel());
    }
}
