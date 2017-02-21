package com.example.storm.testbmob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Button btn1;
    private TextView info;
    private Button push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn1);
        info = (TextView) findViewById(R.id.info);
        push = (Button) findViewById(R.id.push);
        //插入
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person p = new Person();
                p.setName("张大成");
                p.setAddress("上海静安区");
                p.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "创建数据成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "创建数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        //查询
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查找Person表里面id为6b6c11c537的数据
                BmobQuery<Person> bmobQuery = new BmobQuery<>();
                bmobQuery.getObject("fae9d14c7e", new QueryListener<Person>() {
                    @Override
                    public void done(Person object, BmobException e) {
                        info.setText(object.toString());
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    //更新

    private void update() {
        Person p2 = new Person();
        p2.setAddress("北京朝阳");
        p2.update("fae9d14c7e", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void delete() {
        Person p2 = new Person();
        p2.setObjectId("6b6c11c537");
        p2.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }

        });

        //推送服务
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
