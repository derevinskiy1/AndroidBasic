package com.example.storm.ui.memory;

/**
 * Created by Administrator on 2016/10/17.
 */

public class test {
    int[] nums = new int[8];

    private void test1() {
        for (int i = 0; i < nums.length; i++) {
            // 产生一个100以内的随机数，并赋值给数组的每个成员
            nums[i] = (int) (Math.random() * 100);
            System.out.print("-----" + nums[i]);
        }
        System.out.println();
    }

    private void test2() {
        for (int num : nums) {
            // 产生一个100以内的随机数，并赋值给数组的每个成员
            num = (int) (Math.random() * 100);
            System.out.print("-----" + num);
        }

    }

    /**
     * 结果是for方法能产生8个100以内的随机数，而foreach只能产生8个0。
     * 深入分析：for 循环每次循环会调用nums.length来比较长度. 而 foreach 不考虑长度,只调用一次nums.length
     * 结论.
     * 在固定长度或长度不需要计算的时候for循环效率高于foreach.
     * 在不确定长度,或计算长度有性能损耗的时候,用foreach比较方便.
     * 并且foreach的时候会锁定集合中的对象.期间不能修改。
     *
     * @param args
     */


    private void test3() {
        Integer a = 200;
        Integer b = 200;
        if (a == b) {
            System.out.println("a==b");
        } else {
            System.out.println("a!=b");

        }
    }

    public static void main(String[] args) {
        new test().test1();
        new test().test2();
        new test().test3();

    }
}
