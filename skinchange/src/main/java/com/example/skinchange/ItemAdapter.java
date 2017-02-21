package com.example.skinchange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ItemAdapter extends BaseAdapter {
    private ArrayList<String> mDatas;
    private Context mContext;

    public ItemAdapter(Context context, ArrayList<String> mDatas) {
        this.mDatas = mDatas;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TextView tv_title = (TextView) convertView.findViewById(R.id.textView);
        tv_title.setText(mDatas.get(position));
        return convertView;
    }

    static class ViewHolder {
        TextView tv_title;
    }
}
