package com.example.aircarftwar2024.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aircarftwar2024.R;

public class MainActivity extends AppCompatActivity {

    boolean music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, OfflineActivity.class);

        Button on_audio = (Button) findViewById(R.id.on_audio);
        Button off_audio = (Button) findViewById(R.id.off_audio);
        Button start = (Button) findViewById(R.id.start);
        ActivityManager.getActivityManager().addActivity(this);

        on_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music = true;
            }
        });

        off_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music = false;
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("music",music);
                startActivity(intent);
            }
        });
    }
}