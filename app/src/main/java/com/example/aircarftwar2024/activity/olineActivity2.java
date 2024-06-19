package com.example.aircarftwar2024.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.aircarftwar2024.R;

public class olineActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oline2);
        TextView view1 = (TextView) findViewById(R.id.text1);
        TextView view2 = (TextView) findViewById(R.id.text12);
        int score=0,e_score=0;
        if(getIntent() != null){
             score = getIntent().getIntExtra("score",0);
             e_score = getIntent().getIntExtra("e_score",0);
        }
        System.out.println(getIntent().getStringExtra("score"));
        view1.setText("你的分数: "+ score);
        view2.setText("对手分数："+e_score);
    }
}