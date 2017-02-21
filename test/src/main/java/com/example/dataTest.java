package com.example;

class Test {
    private int count = 0;
    private data Listener = null;

    public void get() {
        new Thread() {
            @Override
            public void run() {
                while (count < 10) {
                    try {
                        Thread.sleep(1000);
                        count++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (Listener != null) {
                        Listener.getCount(count);
                    }
                }
            }
        }.start();
    }

    public void setListener(data listener) {
        this.Listener = listener;
    }
}

public class dataTest {
    public static void main(String[] args) {
        Test test = new Test();
        test.get();

        test.setListener(new data() {
            @Override
            public void getCount(int a) {
                System.out.println(a);
            }
        });
    }
}
