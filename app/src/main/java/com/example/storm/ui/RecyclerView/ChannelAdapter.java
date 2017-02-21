package com.example.storm.ui.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.storm.ui.R;

import java.util.ArrayList;
import java.util.Collections;


public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {
    private ArrayList<Channel> mChannels;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public ChannelAdapter(ArrayList<Channel> mChannels, Context context) {
        this.mChannels = mChannels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_channel, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTextView.setText(mChannels.get(position).getName());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChannels.size();
    }

    /**
     * 移动Item
     *
     * @param fromPosition
     * @param toPosition
     */
    public void moveItem(int fromPosition, int toPosition) {
        Collections.swap(mChannels, fromPosition, toPosition);//做数据的交换
        notifyItemMoved(fromPosition, toPosition);
        //notifyItemRangeRemoved(fromPosition, toPosition);
    }


    public void addMore(int position, Channel channel) {
        mChannels.add(position, channel);
        notifyItemInserted(mChannels.size());
    }

    public ArrayList<Channel> getChannels() {
        return mChannels;
    }

    /**
     * 滑动Item
     *
     * @param position
     */
    public void removeItem(int position) {
        mChannels.remove(position);//删除数据
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.news_channel_tv);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
