package com.example.storm.mvp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.storm.mvp.Presenter.HotPresenterImpl;
import com.example.storm.mvp.R;
import com.example.storm.mvp.bean.Hot;

import java.util.List;


public class HotActivity extends AppCompatActivity implements HotView {
    HotPresenterImpl hotPresenter;
    public RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);
        queue = Volley.newRequestQueue(this);
        hotPresenter = new HotPresenterImpl(this);
        hotPresenter.getHot(queue);  //逻辑  ————》传入一些参数
        test();
    }

    private void test() {
        for (int i = 0; i < 10; i++) {
            String s = i + "";
            System.out.println(i);
        }
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
    public void setHotinfo(Hot hot) {
        List<Hot.T1348647853363Entity> list = hot.getT1348647853363();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("--------" + list.get(i).getDigest());
            System.out.println("--------" + list.get(i).getTitle());
        }
    }
}
