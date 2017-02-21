package com.example.storm.surfaceview;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {
    private SurfaceView surfaceView;
    private Button button;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    private String url = "http://flv2.bn.netease.com/videolib3/1607/08/Yrveo7774/SD/Yrveo7774-mobile.mp4";
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        button = (Button) findViewById(R.id.play);
        button1 = (Button) findViewById(R.id.stop);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                play();
                break;
            case R.id.stop:
                stop();
                break;
        }

    }

    private void release() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void play() {
        if (mediaPlayer == null) {
            //     release();
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.setDisplay(surfaceHolder);
                mediaPlayer.setOnPreparedListener(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }
    private void stop() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        play();
    }
}
