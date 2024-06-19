package com.example.aircarftwar2024.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.example.aircarftwar2024.R;
import com.example.aircarftwar2024.aircraft.HeroAircraft;
import com.example.aircarftwar2024.game.BaseGame;
import com.example.aircarftwar2024.game.HardGame;

import java.util.ArrayList;

public class OnlinePlay extends AppCompatActivity {
    private static final String TAG = "OnlinePlay";
    public Handler mHandler;
    public static int screenWidth, screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean music = false;
        mHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                if (msg.what == 1){
                    Intent intent = new Intent(OnlinePlay.this, olineActivity2.class );//LeaderBoardActivity.class  OnlineActivity.class
                    ArrayList<Integer> list = (ArrayList<Integer>) msg.obj;
                    Log.i("bdn", list.get(0)+"");
                    intent.putExtra("score",list.get(0) );
                    intent.putExtra("e_score",list.get(1));
                    startActivity(intent);
                }
            }
        };
        BaseGame baseGameView = null;
        baseGameView = new HardGame(OnlinePlay.this, mHandler, 2, music, true);
        HeroAircraft.getHeroAircraft().fuhuo();
        setContentView(baseGameView);
    }

    public void getScreenHW(){
        //定义DisplayMetrics 对象
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getDisplay().getRealMetrics(dm);

        //窗口的宽度
        screenWidth= dm.widthPixels;
        //窗口高度
        screenHeight = dm.heightPixels;

        Log.i(TAG, "screenWidth : " + screenWidth + " screenHeight : " + screenHeight);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}