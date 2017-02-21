package com.example.storm.mvp.Presenter;

import com.android.volley.RequestQueue;
import com.example.storm.mvp.View.HotView;
import com.example.storm.mvp.bean.Hot;
import com.example.storm.mvp.model.HotModel;
import com.example.storm.mvp.model.HotModelImpl;


public class HotPresenterImpl implements HotPresenter, OnHotListener {

    private HotView hotView;
    private HotModel hotModel;

    public HotPresenterImpl(HotView hotView) {
        this.hotView = hotView;
        hotModel = new HotModelImpl();
    }

    @Override
    public void getHot(RequestQueue queue) {
        hotModel.loadHot(queue, this);
    }

    @Override
    public void onSuccess(Hot hot) {
        hotView.setHotinfo(hot);
    }

    @Override
    public void onError() {
        hotView.showError();
    }
}
