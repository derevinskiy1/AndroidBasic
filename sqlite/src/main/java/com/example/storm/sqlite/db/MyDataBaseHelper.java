package com.example.storm.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDataBaseHelper extends SQLiteOpenHelper {
    private final String CREATE_TABLE_SQL = "create table dict(_id integer primary key autoincrement,word,detail)";

    public MyDataBaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("------------" + oldVersion + "," + "-----------" + newVersion);

    }
}
