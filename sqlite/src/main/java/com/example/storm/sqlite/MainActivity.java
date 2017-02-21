package com.example.storm.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.storm.sqlite.db.MyDataBaseHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn_insert)
    Button btnInsert;
    @InjectView(R.id.btn_search)
    Button btnSearch;
    @InjectView(R.id.btn_delete)
    Button btnDelete;
    private MyDataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        dataBaseHelper = new MyDataBaseHelper(this, "myDict.db3", 1);
        database = dataBaseHelper.getReadableDatabase();
    }

    @OnClick({R.id.btn_insert, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                //插入数据
//                String boy = "boy";
//                String sql = "insert into dict(word,detail) values('boy','I am Boy')";
//                database.execSQL(sql);

                ContentValues cv = new ContentValues();//实例化一个ContentValues用来装载待插入的数据cv.put("username","Jack Johnson");//添加用户名
                cv.put("word", "apple"); //添加密码
                cv.put("detail", "A red Apple!");
                database.insert("dict", null, cv);//执行插入操作
                break;
            case R.id.btn_search:
                Cursor cursor = database.query("dict", null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    String word = cursor.getString(cursor.getColumnIndex("word"));
                    String detail = cursor.getString(cursor.getColumnIndex("detail"));
                    System.out.println("------" + word + "-------" + detail);
                }
                cursor.close();

                break;
        }
    }

    @OnClick(R.id.btn_delete)
    public void onClick() {

//        database.delete("dict", whereClause, whereArgs);
        String sql = "delete  from dict where word='apple'";
        database.execSQL(sql);


        ContentValues contentValues = new ContentValues();
        contentValues.put("word", "apple");
        contentValues.put("detail", "A red Apple!!!");
        String whereClause = "word=?";
        String[] whereArgs = {"%A%"};
        database.update("dict", contentValues, whereClause, whereArgs);
        //String sql = "update [user] set password = 'iHatePopMusic' where username='Jack Johnson'";//修改的SQL语句
        // db.execSQL(sql);//执行修改
    }
}
