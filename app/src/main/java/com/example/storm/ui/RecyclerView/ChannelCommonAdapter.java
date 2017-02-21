package com.example.storm.ui.RecyclerView;

import android.content.Context;
import android.widget.TextView;

import com.example.storm.ui.R;

import java.util.ArrayList;



public class ChannelCommonAdapter extends CommonAdapter<Channel> {
    public ChannelCommonAdapter(ArrayList<Channel> datas, Context context, int layoutId) {
        super(datas, context, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, Channel channel) {
        TextView textView = holder.getView(R.id.news_channel_tv);
        textView.setText(channel.getName());
    }
}
