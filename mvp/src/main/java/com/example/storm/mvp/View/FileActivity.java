package com.example.storm.mvp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.storm.mvp.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Android中的数据存储方式
 */
public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        write();
        read();
    }

    private void write() {
        try {
            FileOutputStream fileOutputStream = this.openFileOutput("a.txt", MODE_PRIVATE);
            fileOutputStream.write("测试写入写出文件".getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        String FILENAME = "a.txt";
        try {
            FileInputStream inStream = openFileInput(FILENAME);
            int len = 0;
            byte[] buf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while ((len = inStream.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            inStream.close();
            System.out.println("-------sb" + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
