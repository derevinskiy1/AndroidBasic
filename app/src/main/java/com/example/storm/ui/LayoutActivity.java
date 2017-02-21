package com.example.storm.ui;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 布局的一些技巧
 */

/**
 * Android5.0过渡动画
 */
public class LayoutActivity extends AppCompatActivity {
    private Button btn5;
    private ListView mListView;
    private GridLayout gridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        btn5 = (Button) findViewById(R.id.btn5);
        mListView = (ListView) findViewById(R.id.myList);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(new Intent(LayoutActivity.this, BundleActivity.class), ActivityOptionsCompat.
                            makeSceneTransitionAnimation(LayoutActivity.this).toBundle());
                }
            }
        });
        final View oval = this.findViewById(R.id.oval);
        oval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 20) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(oval,
                            oval.getWidth() / 2,
                            oval.getHeight() / 2,
                            oval.getWidth(), 0);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(2000);
                    animator.start();
                }
            }
        });

        List<Map<String, Object>> item = new ArrayList<>();
        Map<String, Object> map;
        for (int i = 0; i < 5; i++) {
            map = new HashMap<>();
            map.put("contacts", "联系人----" + i);
            map.put("tel", "电话----" + i);
            item.add(map);
        }
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, item, R.layout.item, new String[]{"contacts", "tel"},
                new int[]{R.id.contacts, R.id.tel});
        mListView.setAdapter(mSimpleAdapter);

//        initGridLayout();
    }

    private void initGridLayout() {
       // gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        String[] strs = new String[]{
                "联系人", "电话",
                "联系人", "电话",
                "联系人", "电话",
                "联系人", "电话",
                "联系人", "电话",};
        gridLayout.setRowCount(5);
        gridLayout.setColumnCount(2);
        TextView tv;
        for (int i = 0; i < 5; i++) {
            tv = new TextView(this);
            tv.setText(strs[i]);
            GridLayout.Spec rowSpec = GridLayout.spec(i / 5+1);
            GridLayout.Spec columnSpec = GridLayout.spec(i % 5+1);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            params.setGravity(Gravity.FILL);
            gridLayout.addView(tv, params);
        }
    }
}
