package com.example.storm.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.wx.goodview.GoodView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private int mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final GoodView goodView = new GoodView(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                goodView.setTextInfo("+1", Color.RED, 12);
                goodView.show(fab);
                startActivity(new Intent(MainActivity.this, SwapebackActivity.class));
            }
        });

        System.out.println("-------" + UpdateApp.getVerName(this));
        System.out.println("-------" + UpdateApp.getVerCode(this));
    }

    int newVerCode;
    String newVerName;

    //从服务器中获取 代码的版本和应用的版本
    private boolean getServerVerCode() {

        try {
            //     String verjson = NetworkTool.getContent(Config.UPDATE_SERVER + Config.UPDATE_VERJSON);
            JSONArray array = new JSONArray("verjson");
            if (array.length() > 0) {
                JSONObject obj = array.getJSONObject(0);
                try {
                    newVerCode = Integer.
                            parseInt(obj.getString("verCode"));
                    newVerName = obj.getString("verName");

                } catch (Exception e) {
                    newVerCode = -1;
                    newVerName = "";
                    return false;
                }
            }
        } catch (Exception e) {
            Log.e("-----", e.getMessage());
            return false;
        }
        return true;
    }

    //通过比较VerCode；
    private boolean IsUpdateApp() {
        if (getServerVerCode()) {
            int vercode = UpdateApp.getVerCode(this); // 用到前面第一节写的方法
            if (newVerCode > vercode) {
                doNewVersionUpdate(); // 更新新版本
                return true;
            } else {
                notNewVersionShow(); // 提示当前为最新版本
                return false;
            }
        }
        return false;
    }

    private void notNewVersionShow() {
        int verCode = UpdateApp.getVerCode(this);
        String verName = UpdateApp.getVerName(this);
        StringBuffer sb = new StringBuffer();
        sb.append("当前版本:");
        sb.append(verName);
        sb.append(" Code:");
        sb.append(verCode);
        sb.append(",/n已是最新版,无需更新!");
        Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新")
                .setMessage(sb.toString())// 设置内容
                .setPositiveButton("确定",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }

    ProgressDialog pBar;

    private void doNewVersionUpdate() {
        int verCode = UpdateApp.getVerCode(this);
        String verName = UpdateApp.getVerName(this);
        StringBuffer sb = new StringBuffer();
        sb.append("当前版本:");
        sb.append(verName);
        sb.append(" Code:");
        sb.append(verCode);
        sb.append(", 发现新版本:");
        sb.append(newVerName);
        sb.append(" Code:");
        sb.append(newVerCode);
        sb.append(", 是否更新?");
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("软件更新")
                .setMessage(sb.toString())
                // 设置内容
                .setPositiveButton("更新",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                pBar = new ProgressDialog(MainActivity.this);
                                pBar.setTitle("正在下载");
                                pBar.setMessage("请稍候...");
                                pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                downFile("");
                            }
                        })
                .setNegativeButton("暂不更新",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // 点击"取消"按钮之后退出程序
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }


    //url  从服务器当中获取APK文件  下载
    private void downFile(final String apkurl) {
        pBar.show();
        new Thread() {
            public void run() {
                try {
                    URL url = new URL(apkurl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(Environment.getExternalStorageDirectory(), "test.apk");
                        fileOutputStream = new FileOutputStream(file);
                        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
                        byte[] buf = new byte[1024];
                        int ch;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            bufferedOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                                mProgress = (int) (((float) count / length) * 100);   //下载的进度
                            }
                        }
                    }
                    down();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                }
            }
        }.start();
    }

    //下载完成，通过handler通知主ui线程将下载对话框取消。
    void down() {
        new Handler().post(new Runnable() {
            public void run() {
                pBar.cancel();
                update();
            }
        });
    }

    //安装应用
    void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "")),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
