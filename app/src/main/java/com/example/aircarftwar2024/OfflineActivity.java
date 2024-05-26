package com.example.aircarftwar2024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OfflineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        Button easy = (Button) findViewById(R.id.button_easy);
        Button normal = (Button) findViewById(R.id.button_normal);
        Button difficult =(Button) findViewById((R.id.button_difficult));
        //获取音乐开关
        //getIntent()

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}