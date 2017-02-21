package com.example.storm.httpurlconnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection();
    }

    private void connection() {
//        HttpURLConnection urlConnection = null;
//        int len;
//        try {
//            URL url = new URL("http://www.baidu.com/");
//            urlConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = urlConnection.getInputStream();
//            BufferedInputStream inputStream1 = new BufferedInputStream(inputStream);
//            while ((len = inputStream1.read()) != -1) {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//        }
        HttpURLConnection urlConnection=null;

        try{
            URL url=new URL("");
            urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.getInputStream();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
