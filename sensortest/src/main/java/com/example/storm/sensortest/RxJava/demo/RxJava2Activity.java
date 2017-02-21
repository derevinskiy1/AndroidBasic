package com.example.storm.sensortest.RxJava.demo;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.storm.sensortest.R;
import java.io.File;
import java.util.ArrayList;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Map操作符
 * <p>
 * 源码及分析
 */
public class RxJava2Activity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java2);
        imageView = (ImageView) findViewById(R.id.img);
        test_2();
        test_4();
        test5();
        test_6();
        test7();
    }

    private void test() {
        Observable.just("Hello World!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s;
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });
        Observable.just("Hello World")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println(s));
    }

    ArrayList<File> folders = new ArrayList<>();

    private void test2() {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                for (File folder : folders) {
//                    File[] files = folder.listFiles();
//                    for (File file : files) {
//                        if (file.getName().endsWith(".png")) {
//                            final Bitmap bitmap = getBitmapFromFile(file);
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    imageCollectorView.addImage(bitmap);
//                                }
//                            });
//                        }
//                    }
//                }
//            }
//        }.start();
//        Observable.from(folders)
//                .flatMap(new Func1<File, Observable<File>>() {
//                    @Override
//                    public Observable<File> call(File file) {
//                        return Observable.from(file.listFiles());
//                    }
//                })
//                .filter(new Func1<File, Boolean>() {
//                    @Override
//                    public Boolean call(File file) {
//                        return file.getName().endsWith(".png");
//                    }
//                })
//                .map(new Func1<File, Bitmap>() {
//                    @Override
//                    public Bitmap call(File file) {
//                        return getBitmapFromFile(file);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) {
//                        imageCollectorView.addImage(bitmap);
//                    }
//                });


    }

    //被观察者;
    private void test_1() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(subscriber);
    }

    //订阅者
    Subscriber subscriber = new Subscriber() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {

        }
    };

    private void test_2() {
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable = Observable.from(words);
        Observable.from(words).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("----", "call: " + s);
            }
        });
        Observable.from(words).subscribe(s -> Log.i("----", "test_2: " + s));
    }

    private void test_3() {
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(R.drawable.a);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onNext(Drawable drawable) {
                        imageView.setImageDrawable(drawable);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJava2Activity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void test_4() {
        Drawable drawable = getResources().getDrawable(R.drawable.a);
        Observable.just(drawable).subscribe(drawable1 -> imageView.setImageDrawable(drawable1));
    }

    /**
     * 在 RxJava 的默认规则中，事件的发出和消费都是在同一个线程的。也就是说，如果只用上面的方法，
     * 实现出来的只是一个同步的观察者模式。观察者模式本身的目的就是『后台处理，前台回调』的异步机制，
     * 因此异步对于 RxJava 是至关重要的。而要实现异步，则需要用到 RxJava 的另一个概念： Scheduler 。
     */
    private void test5() {

        /**
         * Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
         Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
         Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，
         区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread()
         更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
         Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，
         例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O
         操作的等待时间会浪费 CPU。另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
         有了这几个 Scheduler ，就可以使用 subscribeOn() 和 observeOn() 两个方法来对线程进行控制了。 * subscribeOn(): 指定 subscribe()
         所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。 * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
         */
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) //指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) //指定Subscriber的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i("-----", "call: " + integer);
                    }
                });
        //这种在 subscribe() 之前写上两句 subscribeOn(Scheduler.io()) 和 observeOn(AndroidSchedulers.mainThread())
        // 的使用方式非常常见，它适用于多数的 『后台线程取数据，主线程显示』的程序策略。
    }

    private void test6() {
        Observable.just("images/logo.png")
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String filePath) { // 参数类型 String
                        return getBitmapFromPath(filePath); // 返回类型 Bitmap
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) { // 参数类型 Bitmap
                        showBitmap(bitmap);
                    }
                });
    }

    private Bitmap getBitmapFromPath(String path) {
        return null;
    }

    private void showBitmap(Bitmap bitmap) {
    }

    private void test_6() {
        Student[] stus = new Student[]{new Student(12, "Tom"), new Student(13, "Kim")};
        Observable.from(stus)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("-------", "call: " + s);
            }
        });
    }


    private void test7() {
        Student[] stus = new Student[]{new Student(13, "Time"), new Student(14, "King")};
        Courses c;
        ArrayList<Courses> ac = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            c = new Courses();
            c.setName("编程" + i);
            ac.add(c);
        }
        stus[0].setCourses(ac);
        Subscriber<Student> subscriber = new Subscriber<Student>() {
            @Override
            public void onNext(Student student) {
                ArrayList<Courses> courses = student.getCourses();
                for (int i = 0; i < courses.size(); i++) {
                    Courses course = courses.get(i);
                    Log.i("-----", course.getName());
                }
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        Observable.from(stus).subscribe(subscriber);
        Observable.from(stus)
                .flatMap(new Func1<Student, Observable<Courses>>() {
                    @Override
                    public Observable<Courses> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                }).subscribe(courses -> Log.i("----", "test7: " + courses.getName()));
    }
    private void test8(){
        //由于可以在嵌套的 Observable 中添加异步代码， flatMap() 也常用于嵌套的异步操作，例如嵌套的网络请求。示例代码
//        networkClient.token() // 返回 Observable<String>，在订阅时请求 token，并在响应后发送 token
//                .flatMap(new Func1<String, Observable<Messages>>() {
//                    @Override
//                    public Observable<Messages> call(String token) {
//                        // 返回 Observable<Messages>，在订阅时请求消息列表，并在响应后发送请求到的消息列表
//                        return networkClient.messages();
//                    }
//                })
//                .subscribe(new Action1<Messages>() {
//                    @Override
//                    public void call(Messages messages) {
//                        // 处理显示消息列表
//                        showMessages(messages);
//                    }
//                });
    }

}
