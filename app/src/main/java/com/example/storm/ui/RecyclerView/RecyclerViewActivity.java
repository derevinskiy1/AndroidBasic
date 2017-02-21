package com.example.storm.ui.RecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.storm.ui.R;

import java.util.ArrayList;


public class RecyclerViewActivity extends AppCompatActivity {
    private Channel mChannel;
    private ArrayList<Channel> mChannels = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewMore;
    private RecyclerView mRecyclerViewCommon;


    private Channel mChannelMore;
    private ArrayList<Channel> mChannelMores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.news_channel_mine_rv);
        mRecyclerViewMore = (RecyclerView) findViewById(R.id.news_channel_more_rv);
        mRecyclerViewCommon = (RecyclerView) findViewById(R.id.news_channel_common);
        initDatas();
    }

    private void initDatas() {
        for (int i = 0; i < 8; i++) {
            mChannel = new Channel();
            mChannel.setName("头条-" + i);
            mChannels.add(mChannel);
        }
        for (int j = 0; j < 8; j++) {
            mChannelMore = new Channel();
            mChannelMore.setName("新闻-" + j);
            mChannelMores.add(mChannelMore);
        }
        initLayouManager();

    }

    ChannelAdapter channelAdapter, channelAdapterMore;

    private void initLayouManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        channelAdapter = new ChannelAdapter(mChannels, this);
        channelAdapterMore = new ChannelAdapter(mChannelMores, this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerViewMore.setLayoutManager(gridLayoutManager1);
        mRecyclerView.setAdapter(channelAdapter);
        mRecyclerViewMore.setAdapter(channelAdapterMore);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //actionState : action状态类型，有三类 ACTION_STATE_DRAG （拖曳），ACTION_STATE_SWIPE（滑动），ACTION_STATE_IDLE（静止）
                int dragFlags = makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.UP | ItemTouchHelper.DOWN
                        | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//支持上下左右的拖曳
                int swipeFlags = makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//表示支持左右的滑动
                return makeMovementFlags(dragFlags, swipeFlags);//直接返回0表示不支持拖曳和滑动
            }
            //位置的交换
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder
                    viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                channelAdapter.moveItem(fromPosition, toPosition);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();//获取要滑动删除的Item位置
                channelAdapter.removeItem(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        channelAdapter.setOnItemClickListener(new ChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Channel channel = new Channel(mChannels.get(position).getName());
                channelAdapter.removeItem(position);
                channelAdapterMore.addMore(channelAdapterMore.getChannels().size(), channel);
            }
        });
        channelAdapterMore.setOnItemClickListener(new ChannelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Channel channel = new Channel(mChannelMores.get(position).getName());
                channelAdapterMore.removeItem(position);
                channelAdapter.addMore(channelAdapter.getChannels().size(), channel);
            }
        });

        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewCommon.setLayoutManager(gridLayoutManager2);
        ChannelCommonAdapter channelCommonAdapter = new ChannelCommonAdapter
                (mChannelMores, this, R.layout.item_channel);
        mRecyclerViewCommon.setAdapter(channelCommonAdapter);
    }
}
