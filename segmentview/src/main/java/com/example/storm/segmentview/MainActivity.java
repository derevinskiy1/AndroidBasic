package com.example.storm.segmentview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements com.example.storm.segmentview.segmentView.onSegmentViewClickListener {
    private segmentView segmentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        segmentView = (segmentView) findViewById(R.id.segmentView);
        segmentView.setOnSegmentViewClickListener(this);
    }

    @Override
    public void onSegmentViewClick(View view, int position) {
        switch (position) {
            case 0:
                Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "电话", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
