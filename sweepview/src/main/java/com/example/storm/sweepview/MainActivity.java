package com.example.storm.sweepview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private ArrayList<String> mDatas;
    private ArrayList<SweepView> mOpenedViews = new ArrayList<>();
    private boolean isOpend;
    private SweepView sweepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv);
        // 模拟数据
        mDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mDatas.add("内容区域----" + i);
        }
        // 给listView设置数据
        mListView.setAdapter(new MyAdapter());// adapter --》list
        //   mListView.setOnScrollListener(this);



    }


    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (mDatas != null) {
                return mDatas.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mDatas != null) {
                return mDatas.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(MainActivity.this, R.layout.item, null);
                convertView.setTag(holder);
                // view的初始化
                holder.sv = (SweepView) convertView.findViewById(R.id.item_sv);
                holder.tvContent = (TextView) convertView.findViewById(R.id.item_tv_content);
                holder.tvDelete = (TextView) convertView.findViewById(R.id.item_tv_delete);
                holder.sv.setOnSweepListener(new SweepView.OnSweepListener() {
                    @Override
                    public void onSweepChanged(SweepView view, boolean isOpened) {
                        MainActivity.this.isOpend = isOpened;
                        MainActivity.this.sweepView = view;
                        if (isOpened) {
                            // 打开了,记录下来
                            if (!mOpenedViews.contains(view)) {
                                mOpenedViews.add(view);
                            }
                        } else {
                            // 移除
                            mOpenedViews.remove(view);
                        }
                    }
                });
            } else {
                // 有复用
                holder = (ViewHolder) convertView.getTag();
            }
            // 数据的加载
            final String data = mDatas.get(position);
            holder.tvContent.setText(data);
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDatas.remove(data);
                    // 关闭所有打开的View
                    closeAll();
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

    public void closeAll() {
        ListIterator<SweepView> iterator = mOpenedViews.listIterator();
        while (iterator.hasNext()) {
            SweepView view = iterator.next();
            view.close();
        }
    }

    class ViewHolder {
        SweepView sv;
        TextView tvContent;
        TextView tvDelete;
    }

//
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        if (isOpend) {
//            if (scrollState == SCROLL_STATE_FLING || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
//                sweepView.close();
//            }
//        }
//    }
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//    }
}
