package com.example.storm.mvp.View;

import android.content.Intent;
import android.os.Bundle;

import com.example.storm.mvp.Presenter.WeatherPresenter;
import com.example.storm.mvp.Presenter.WeatherPresenterImpl;
import com.example.storm.mvp.R;
import com.example.storm.mvp.bean.Weather;

import java.util.List;

/**
 * MVP是模型（Model）、视图（View）、控制者（Presenter）的缩写，分别代表项目中3个不同的模块。
 * 　　      模型（Model）：负责处理数据的加载或者存储，比如从网络或本地数据库获取数据等；
 * 　　      视图（View）：负责界面数据的展示，与用户进行交互；
 * 　　      控制者（Presenter）：相当于协调者，是模型与视图之间的桥梁，将模型与视图分离开来。
 */

/**
 * MVC的表现形式描述一下：
 * M层：适合做一些业务逻辑处理，比如数据库存取操作，网络操作，复杂的算法，耗时的任务等都在model层处理。
 * V层：应用层中处理数据显示的部分，XML布局可以视为V层，显示Model层的数据结果。
 * C层：在Android中，Activity处理用户交互问题，因此可以认为Activity是控制器，Activity读取V视图层的数据（eg.读取当前EditText控件的数据），控制用户输入（eg.EditText控件数据的输入），并向Model发送数据请求（eg.发起网络请求等）。
 * 为方便理解，我下面举一个例子，由于MVC容易理解，也鉴于篇幅问题，这里只给出简短的伪代码，因为具体的代码也没有什么意义：
 */

public class MainActivity extends BaseActivity implements WeatherView {
    private WeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherPresenter = new WeatherPresenterImpl(this); //传入WeatherView
        weatherPresenter.getWeather(this);
        Intent intent=new Intent(this,MyIntentService.class);
        intent.setAction(MyIntentService.ACTION_FOO);
        intent.putExtra(MyIntentService.EXTRA_PARAM1,"Hello IntentService");
        startService(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void setWeatherInfo(Weather weather) {
        System.out.println("11111111111");
        List<Weather.推荐Entity> list = weather.get推荐();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("-------" + list.get(i).getTitle());
            System.out.println("-------" + list.get(i).getDigest());
        }
    }
}
