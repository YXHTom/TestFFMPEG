package com.yy.linux;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static int CODE_REQUEST_WRITE_EXTERNAL = 0x100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v -> ffmpegTest());
        checkPermission();

    }

    private void checkPermission() {
        int permissions = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissions != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CODE_REQUEST_WRITE_EXTERNAL
            );
        }

    }

    int temp = 2;

    private void ffmpegTest() {
        temp++;
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        new Thread() {
            @Override
            public void run() {

                ArrayList<String> cmd = new ArrayList<>();
                cmd.add("ffmpeg");
                cmd.add("-d");//输出日志
                cmd.add("-y");//-y：覆盖输出文件
                cmd.add("-i");
                cmd.add("/storage/emulated/0/PictureSubmitDir/RecorderCashDir/1564552188087.mp4");
                cmd.add("-b:v");
                cmd.add(1600 + "k");
//                cmd.add("-vcodec");
//                cmd.add("h264");
                cmd.add("/storage/emulated/0/PictureSubmitDir/RecorderCashDir/" + "test1" + temp + ".mp4");
                String[] cmdArr = new String[cmd.size()];
                FFmpegCmd.run(cmd.size(), cmd.toArray(cmdArr));
            }
        }.start();
    }

}
