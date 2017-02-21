package com.example.storm.ui;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class test {
    private void aVoid() {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

            }
        });


    }

    public static void main(String[] args) {
        int i = 0;

        System.out.println("-----");

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "个人博客：sunfusheng.com";
            }
        };
        FutureTask<String> task = new FutureTask(callable);
        while (true) {
            try {
                System.out.println(callable.call());
                i = i + 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread thread = new Thread(task);
            thread.start();
            if (i == 6) {
                task.cancel(true);
                //     thread.isInterrupted();
            }
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--------" + i);

        }
    }
}
