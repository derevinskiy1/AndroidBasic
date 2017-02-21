package com.example.storm.systemui.activity.bs;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.storm.systemui.R;
import com.example.storm.systemui.adapter.RecyclerItemAdapter;

public class BottomSheetsDemoActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheets_demo);

        findViewById(R.id.btn_show_dialog).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_show_dialog) {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            View contentView = View.inflate(this, R.layout.bottom_sheets_layout, null);
            RecyclerView itemView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            itemView.setLayoutManager(layoutManager);
            RecyclerView.Adapter adapter = new RecyclerItemAdapter();
            itemView.setAdapter(adapter);
            dialog.setContentView(contentView);
            dialog.show();
        }
    }
}
