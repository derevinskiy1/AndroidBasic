package com.example.skinchange;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mListView = (ListView) findViewById(R.id.skinListView);
        initView();
        initDatas();
        initEvents();
    }

    private void initView() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.menu_left_fragment, new MenuLeftFragment()).commit();

        // Fragment fragment = fm.findFragmentById(R.id.menu_left_fragment);
//        if (fragment != null) {
//        }
    }

    private void initDatas() {
        ArrayList<String> mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add("换肤" + i);
        }
        mListView.setAdapter(new ItemAdapter(this, mDatas));
    }

    private void initEvents() {
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View content = mDrawerLayout.getChildAt(0);
                View menu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;
                if (drawerView.getTag().equals("LEFT")) {
                    float leftScale = 1 - 0.3f * scale;
                    ViewHelper.setScaleX(menu, leftScale);
                    ViewHelper.setScaleY(menu, leftScale);
                    ViewHelper.setAlpha(menu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(content, menu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(content, 0);
                    ViewHelper.setPivotY(content, content.getMeasuredHeight() / 2);
                    content.invalidate();
                    ViewHelper.setScaleX(content, rightScale);
                    ViewHelper.setScaleY(content, rightScale);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

}
