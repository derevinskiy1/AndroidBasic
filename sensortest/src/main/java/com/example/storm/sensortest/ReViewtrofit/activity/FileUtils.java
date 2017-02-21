package com.example.storm.sensortest.ReViewtrofit.activity;

/**
 * Created by zhiwenyan on 12/19/16.
 */

public class FileUtils {
//    public static void getFile() {
//        return new File("");
//    }

    private void test() {
        for (int i = 0; i < 10; i++) {
            //获取当前i的值
            int selector = i;
            //打log查看当前i的值（此步多余，实际开发请忽略）
            System.out.println("for当前的i的值：" + i);
            //调用方法
            stepNext(i);
        }
    }
    private void stepNext(int i){
        System.out.println("stepNext当前的值"+i);
    }

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        fileUtils.test();
    }
}

