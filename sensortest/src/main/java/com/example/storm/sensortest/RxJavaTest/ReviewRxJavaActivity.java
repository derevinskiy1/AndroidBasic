package com.example.storm.sensortest.RxJavaTest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.storm.sensortest.R;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ReviewRxJavaActivity extends AppCompatActivity {


    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_rx_java);
//        doJustOne();
//        doJustTwo();
        //doJustFour();
        //  doMapOne();
        //doMapTwo();
        doRxJava4();
    }

    private void doJustOne() {
        //Subscriber继承Observer
        //Observer 即观察者，它决定事件触发的时候将有怎样的行为。 RxJava 中的 Observer 接口的实现方式：
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("TAG---", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG---", "onError: " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i("TAG---", "onNext: " + s);
            }
        };
        //Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。 RxJava 使用 create() 方法来创建一个 Observable ，并为它定义事件触发规则：
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World-->1");
                subscriber.onNext("Hello World-->2");
                subscriber.onNext("Hello World-->3");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(subscriber);
    }

    private void doJustTwo() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("doJustTwo", "onNext: " + s);
            }
        };
        Observable<String> observable = Observable.just("Hello World");
        observable.subscribe(subscriber);
    }

    private void doJustThree() {
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("doJustThree", "call: " + s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.d("doJustThree", "completed");
            }
        };
    }

    private void doJustFour() {
        String[] names = {"Allen", "Steven", "Ticker"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String names) {
                        Log.i("doJustFour", "call: " + names);
                    }
                });
        Observable.from(names).subscribe(s -> Log.i("doJustFour", "doJustFour: " + s.toString()));
    }

    /**
     * 这种方式仍然不能让人满意，因为我希望我的Subscribers越轻量越好，因为我有可能会在mainThread中运行subscriber
     * 。另外，根据响应式函数编程的概念，Subscribers更应该做的事情是“响应”，响应Observable发出的事件，而不是去修改。
     * 如果我能在某些中间步骤中对“Hello World！”进行变换是不是很酷？
     */

    //操作符就是为了解决对Observable对象的变换的问题，操作符用于在Observable和最终的Subscriber之间修改Observable发出的事件。RxJava提供了很多很有用的操作符。
    // 比如map操作符，就是用来把把一个事件转换为另一个事件的。
    private void doMapOne() {
        Observable.just("Hello World")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "--->Steven";
                    }
                }).subscribe(s -> Log.i("doMapOne", "doMapOne: " + s));

        Observable.just("Hello World")
                .map(s -> s + "--->Steven")
                .subscribe(s -> Log.i(TAG, "doMapOne: " + s));
    }

    private void doMapTwo() {
        Observable.just("Hello, world!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(i -> System.out.println(Integer.toString(i)));


        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .subscribe(i -> System.out.println(Integer.toString(i)));
    }

    /**
     * 1.Observable和Subscriber可以做任何事情
     * Observable可以是一个数据库查询，Subscriber用来显示查询结果；Observable可以是屏幕上的点击事件，Subscriber用来响应点击事件；Observable可以是一个网络请求，Subscriber用来显示请求结果。
     * <p>
     * 2.Observable和Subscriber是独立于中间的变换过程的。
     * 在Observable和Subscriber中间可以增减任何数量的map。整个系统是高度可组合的，操作数据是一个很简单的过程。
     */
    //------------------------------------------------------------------------------------------------------------------------------

    //RxJava操作符
    private void doMapThree() {
        String[] urls = {"url1", "url2", "url3"};
        Observable.from(urls)
                .subscribe(url -> Log.i(TAG, "doMapThree: " + url));
    }

    private void doRxJava1() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) //// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) //// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i(TAG, "call: " + integer);
                    }
                });
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.i(TAG, "doRxJava1: " + s));
    }

    //所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。
    private void doRxJava2() {
        Observable.just("images/logo.png")
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return null;
                        //         return getBitmapFromPath(filePath); // 返回类型 Bitmap
                    }
                }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                //   showBitmap(bitmap);
            }
        });
    }

    private void doRxJava3() {
        Student[] students = {new Student("Allan"), new Student("Ewa"), new Student("Ziv")};
        Observable.from(students)
                .subscribe(new Action1<Student>() {
                    @Override
                    public void call(Student student) {
                        Log.i(TAG, "call: " + student.getName());
                    }
                });
        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG, "call: " + s);
            }
        });
        Observable.from(students)
                .map(student -> student.getName())
                .subscribe(s -> Log.i(TAG, "doRxJava3: " + s));
    }

    private void doRxJava4() {
        ArrayList<Course> m1 = new ArrayList<>();
        Student s1 = new Student("Allan");
        Course c1;
        for (int i = 0; i < 3; i++) {
            c1 = new Course();
            c1.setName("java" + i);
            m1.add(c1);
        }
        s1.setCourseArrayList(m1);

        ArrayList<Course> m2 = new ArrayList<>();
        Student s2 = new Student("Ziv");
        Course c2;
        for (int i = 0; i < 3; i++) {
            c2 = new Course();
            c2.setName("c" + i);
            m2.add(c2);
        }
        s2.setCourseArrayList(m2);

        Student[] students = {s1, s2};

        Subscriber<Student> subscriber = new Subscriber<Student>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Student student) {
                System.out.println("doRxJava4" + student.getName());
                ArrayList<Course> courses = student.getCourseArrayList();
                for (int i = 0; i < courses.size(); i++) {
                    Course course = courses.get(i);
                    Log.i(TAG, "doRxJava4" + course.getName());
                }
            }
        };
        Observable.from(students).subscribe(subscriber);

        Student[] students1 = {s1, s2};
        Subscriber<Course> subscriber1 = new Subscriber<Course>() {

            @Override
            public void onNext(Course course) {
                Log.i(TAG, "doRxJava4"+course.getName());
            }
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
        };
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourseArrayList());
                    }
                }).subscribe(subscriber1);
    }
}
