package com.example.storm.systemui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.storm.systemui.R;
import com.example.storm.systemui.activity.bs.BottomSheetsDemoActivity;
import com.example.storm.systemui.activity.bt.BarTabDemoActivity;
import com.example.storm.systemui.activity.cardview.CardViewDemoActivity;
import com.example.storm.systemui.activity.collapsing.CollapsingDemoActivity;
import com.example.storm.systemui.activity.listview.ListViewDemoActivity;
import com.example.storm.systemui.activity.navigation.NavigationDemoActivity;
import com.example.storm.systemui.activity.recycler.RecyclerDemoActivity;
import com.example.storm.systemui.activity.snackbar.SnackBarDemoActivity;
import com.example.storm.systemui.activity.toolbar.ToolDemoActivity;
import com.example.storm.systemui.activity.translucent.TranslucentDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_translucent_demo).setOnClickListener(this);
        findViewById(R.id.btn_toolbar_demo).setOnClickListener(this);
        findViewById(R.id.btn_navigation_demo).setOnClickListener(this);
        findViewById(R.id.btn_listview_demo).setOnClickListener(this);
        findViewById(R.id.btn_recycler_demo).setOnClickListener(this);
        findViewById(R.id.btn_card_view).setOnClickListener(this);
        findViewById(R.id.btn_snack_bar).setOnClickListener(this);
        findViewById(R.id.btn_bar_tab).setOnClickListener(this);
        findViewById(R.id.btn_collapsing).setOnClickListener(this);
        findViewById(R.id.btn_bottom_sheets).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_translucent_demo) {
            Intent intent = new Intent(this, TranslucentDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_toolbar_demo) {
            Intent intent = new Intent(this, ToolDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_navigation_demo) {
            Intent intent = new Intent(this, NavigationDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_listview_demo) {
            Intent intent = new Intent(this, ListViewDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_recycler_demo) {
            Intent intent = new Intent(this, RecyclerDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_card_view) {
            Intent intent = new Intent(this, CardViewDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_snack_bar) {
            Intent intent = new Intent(this, SnackBarDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_bar_tab) {
            Intent intent = new Intent(this, BarTabDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_collapsing) {
            Intent intent = new Intent(this, CollapsingDemoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.btn_bottom_sheets) {
            Intent intent = new Intent(this, BottomSheetsDemoActivity.class);
            startActivity(intent);

        }

    }
}
