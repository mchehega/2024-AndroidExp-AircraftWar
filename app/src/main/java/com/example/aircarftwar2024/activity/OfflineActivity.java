package com.example.aircarftwar2024.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aircarftwar2024.R;

public class OfflineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        Button easy = (Button) findViewById(R.id.button_easy);
        Button normal = (Button) findViewById(R.id.button_normal);
        Button difficult =(Button) findViewById((R.id.button_difficult));

        //获取音乐开关
        boolean music = getIntent().getBooleanExtra("music",false);
        Intent intent = new Intent(OfflineActivity.this,GameActivity.class);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("difficulty","easy");
                intent.putExtra("music",music);
                startActivity(intent);

            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("difficulty","normal");
                intent.putExtra("music",music);
                startActivity(intent);

            }
        });

        difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("difficulty","difficult");
                intent.putExtra("music",music);
                startActivity(intent);

            }
        });
    }
}