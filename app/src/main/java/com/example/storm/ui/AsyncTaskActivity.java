package com.example.storm.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 异步任务
 */
public class AsyncTaskActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar5);
        MyTask myTask = new MyTask();
        myTask.execute();
        Toast.makeText(this, CPU_COUNT + "", Toast.LENGTH_SHORT).show();

    }

    class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            mProgressBar.setProgress(0);
        }

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i <= 100; i++) {
                publishProgress(i); // 将会调用onProgressUpdate方法
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "后台任务执行完毕";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(AsyncTaskActivity.this, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            Toast.makeText(AsyncTaskActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
