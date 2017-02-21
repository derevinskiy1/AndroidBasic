package com.example.storm.mvp.View;

import com.example.storm.mvp.bean.Hot;

/**
 * 先定义一个View视图显示的接口
 * 新闻列表模块主要是展示从网络获取的新闻列表信息，View层的接口大概需要如下方法：
 * 　　（1）加载数据的过程中需要提示“正在加载”的反馈信息给用户
 * 　　（2）加载成功后，将加载得到的数据填充到RecyclerView展示给用户
 * 　　（3）加载成功后，需要将“正在加载”反馈信息取消掉
 * 　　（4）若加载数据失败，如无网络连接，则需要给用户提示信息
 */
public interface HotView {
    void showLoading();

    void hideLoading();

    void showError();

    void setHotinfo(Hot Hot);

}
