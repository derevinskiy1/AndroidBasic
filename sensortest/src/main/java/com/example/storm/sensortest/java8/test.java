package com.example.storm.sensortest.java8;

/**
 *
 */
public class test {
    public boolean horrible(boolean foo, boolean bar, boolean baz) {
        if (foo) {
            if (bar) {
                return true;
            }
        }
        if (baz) {
            return true;
        } else {
            return false;
        }
    }

    public boolean horrible1(boolean foo, boolean bar, boolean baz) {
        return foo && bar || baz;
    }




    public static void main(String[] args) {
        Integer a=3;//这是自动装箱
        int i = new Integer(2);//这是拆箱

        test t = new test();
        System.out.println(t.horrible(true, false, false));  //false
        System.out.println(t.horrible1(true, false, false)); //false
    }
}
