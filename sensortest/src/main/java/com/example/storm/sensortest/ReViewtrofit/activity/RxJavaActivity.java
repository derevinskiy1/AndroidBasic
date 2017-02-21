package com.example.storm.sensortest.ReViewtrofit.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.storm.sensortest.R;
import com.example.storm.sensortest.ReViewtrofit.entity.Course;
import com.example.storm.sensortest.ReViewtrofit.entity.Student;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    public static final String TAG = RxJavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java3);
        test();
        test2();
        test3();
        test5();
        test6();
    }

    private void test() {
        //Observer 即观察者，它决定事件触发的时候将有怎样的行为。
        // RxJava 中的 Observer 接口的实现方式：
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
            }
        };
        //除了Observer接口之外，RxJava 还内置了一个实现了 Observer 的抽象类：
        // Subscriber。 Subscriber对Observer接口进行了一些扩展，但他们的基本使用方式是完全一样的：
        Subscriber<String> sub = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
            }
        };
        //.create()方法是RxJava最基本的创造事件序列的方法
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello1");
                subscriber.onNext("hello2");
                subscriber.onNext("hello3");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(sub);
    }

    private void test2() {
        String[] names = {"Steven", "Tom", "Kein"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "call: " + s);
            }
        });
    }

    //线程调度Scheduler
    private void test3() {
        Observable.just(1, 2, 3, 4)
                //可以利用 subscribeOn() 结合 observeOn() 来实现线程控制，让事件的产生和消费发生在不同的线程
                .subscribeOn(Schedulers.io()) //指定发生在IO线程
                .observeOn(AndroidSchedulers.mainThread())//回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, "call: " + integer);
                    }
                });
//        int drawableRes = 0;
//        ImageView imageView = null;
//        Observable.create(new Observable.OnSubscribe<Drawable>() {
//            @Override
//            public void call(Subscriber<? super Drawable> subscriber) {
//                Drawable drawable = null if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                    drawable = getTheme().getDrawable(drawableRes);
//                });
//                subscriber.onNext(drawable);
//                subscriber.onCompleted();
//            }
//        })
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Observer<Drawable>() {
//                    @Override
//                    public void onNext(Drawable drawable) {
//                        imageView.setImageDrawable(drawable);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//                });

    }

    //4 变换
    //变换:所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列

    //Func1接口有返回值  Action没有返回值
    private void test4() {
        Observable.just("images/logo.png") //输入的是String
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return null;
                    }
                }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) { //输出的是Bitmap

            }
        });
    }

    //map变换
    private void test5() {
        Student[] students = {new Student("学生1"),
                new Student("学生2"),
                new Student("学生3")};
        Observable.from(students)
                .map(new Func1<Student, String>() { //传入的是Student,返回的是学生的姓名 String
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
            }
        });
    }

    //flatMap;
    private void test6() {
        Student[] students = {new Student("学生1"),
                new Student("学生2"),
                new Student("学生3")};
        Course c1 = new Course("c");
        Course c2 = new Course("java");
        Course c3 = new Course("android");
        List<Course> lists = new ArrayList<>();
        lists.add(c1);
        lists.add(c2);
        lists.add(c3);
        for (int i = 0; i < students.length; i++) {
            students[i].setCoures(lists);
        }
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCoures());
                    }
                }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {

                Log.i(TAG, "call: " + course.getName());
            }
        });
        //变换原理:代理机制 通过事件拦截和处理实现事件序列的变换。
    }

    //与Retrofit
    private void test7() {

    }

}