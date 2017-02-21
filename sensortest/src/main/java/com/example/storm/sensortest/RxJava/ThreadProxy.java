package com.example.storm.sensortest.RxJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java线程和线程池
 */
//Executors： 提供了一系列工厂方法用于创先线程池，返回的线程池都实现了ExecutorService 接口
//ThreadPoolExecutor：线程池的具体实现类,一般用的各种线程池都是基于这个类实现的。


public class ThreadProxy implements Runnable {
    private String command;

    public ThreadProxy(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End.");
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            ThreadProxy worker = new ThreadProxy("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("Finished all threads");
    }
}
