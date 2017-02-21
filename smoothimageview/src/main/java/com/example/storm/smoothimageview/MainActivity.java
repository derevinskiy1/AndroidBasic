package com.example.storm.smoothimageview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


/**
 * Author: liuk
 * Created at: 15/12/15
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager linearLayoutManager;
    private MainAdapter mainAdapter;

    private String[] urls = {"http://b.hiphotos.baidu.com/image/pic/item/d788d43f8794a4c274c8110d0bf41bd5ad6e3928.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/8435e5dde71190efa7aa1231ca1b9d16fcfa608f.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/6a600c338744ebf84821a4edddf9d72a6159a794.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/6c224f4a20a44623c458fa889c22720e0df3d74e.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/d788d43f8794a4c274c8110d0bf41bd5ad6e3928.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/8435e5dde71190efa7aa1231ca1b9d16fcfa608f.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/6a600c338744ebf84821a4edddf9d72a6159a794.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/6c224f4a20a44623c458fa889c22720e0df3d74e.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/d788d43f8794a4c274c8110d0bf41bd5ad6e3928.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/8435e5dde71190efa7aa1231ca1b9d16fcfa608f.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/6a600c338744ebf84821a4edddf9d72a6159a794.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/6c224f4a20a44623c458fa889c22720e0df3d74e.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/6c224f4a20a44623c458fa889c22720e0df3d74e.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/6c224f4a20a44623c458fa889c22720e0df3d74e.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/6c224f4a20a44623c458fa889c22720e0df3d74e.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/6c224f4a20a44623c458fa889c22720e0df3d74e.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/d788d43f8794a4c274c8110d0bf41bd5ad6e3928.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/8435e5dde71190efa7aa1231ca1b9d16fcfa608f.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/6a600c338744ebf84821a4edddf9d72a6159a794.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/6c224f4a20a44623c458fa889c22720e0df3d74e.jpg"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        linearLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // linearLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);  //滑动中，不处理 gap：
        mainAdapter = new MainAdapter(this, urls);
        recyclerView.setAdapter(mainAdapter);
    }
}
