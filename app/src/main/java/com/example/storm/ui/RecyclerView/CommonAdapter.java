package com.example.storm.ui.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;


public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    private ArrayList<T> mDatas;
    private Context mContext;
    private int mLayoutId;
    private LayoutInflater mLayoutInflater;

    public CommonAdapter(ArrayList<T> datas, Context context, int layoutId) {
        this.mDatas = datas;
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(CommonViewHolder holder, T t);

}
