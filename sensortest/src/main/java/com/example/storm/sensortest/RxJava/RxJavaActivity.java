package com.example.storm.sensortest.RxJava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.storm.sensortest.R;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RxJava基础篇
 */
public class RxJavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
//        test();
//        test2();
//        test3();
        test4();
        Context
    }

    private void test() {
        //被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxJava11-1");
                subscriber.onNext("Hello RxJava11-2");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(subscriber);
    }

    //订阅者
    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(String s) {
            System.out.println("-----" + s + s);
            //    Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_LONG).show();
            //  Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_LONG).show();
        }
    };

    //简化操作
    private void test2() {
        //简化创建Observable
        Observable<String> observable = Observable.just("Hello 简化创建Observable");
        //简化Subscriber
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("-----" + s);
            }
        };
        observable.subscribe(onNextAction);
    }

    private void test3() {
        Observable.just("Hello　World")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("-----" + s);
                    }
                });
    }

    /**
     * map操作符更有趣的一点是它不必返回Observable对象返回的类型，你可以使用map操作符返回一个发出新的数据类型的observable对象。
     */
    //map操作符更有趣的一点是它不必返回Observable对象返回的类型，你可以使用map操作符返回一个发出新的数据类型的observable对象。
    //比如上面的例子中，subscriber并不关心返回的字符串，而是想要字符串的hash值
    private void test4() {
        Observable.just("map操作符")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                }).subscribe(s -> System.out.println("======" + s));

        Observable.just("Hello Word")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(s -> System.out.println("======" + s));

    }

    /**
     * 1.Observable和Subscriber可以做任何事情
     Observable可以是一个数据库查询，Subscriber用来显示查询结果；
     Observable可以是屏幕上的点击事件，Subscriber用来响应点击事件；Observable可以是一个网络请求，Subscriber用来显示请求结果。

     2.Observable和Subscriber是独立于中间的变换过程的。
     在Observable和Subscriber中间可以增减任何数量的map。整个系统是高度可组合的，操作数据是一个很简单的过程。

     */
}
