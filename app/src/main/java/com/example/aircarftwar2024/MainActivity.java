package com.example.aircarftwar2024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this,OfflineActivity.class);
        //传音乐开关
        //intent.putExtra()

        //在开始游戏按钮的onclik函数里调用
        startActivity(intent);
    }
}