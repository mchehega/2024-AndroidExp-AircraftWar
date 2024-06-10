package com.example.aircarftwar2024.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aircarftwar2024.R;
import com.example.aircarftwar2024.aircraft.HeroAircraft;
import com.example.aircarftwar2024.game.BaseGame;
import com.example.aircarftwar2024.game.EasyGame;
import com.example.aircarftwar2024.game.HardGame;
import com.example.aircarftwar2024.game.MediumGame;

import java.util.ArrayList;


public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";
    public Handler mHandler;

    private String gameType;
    public static int screenWidth,screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreenHW();

        boolean music = false;
        if(getIntent() != null){
            gameType = getIntent().getStringExtra("difficulty");
            music = getIntent().getBooleanExtra("music", false);
        }
        mHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                if (msg.what == 1){
                    Intent intent = new Intent(GameActivity.this, LeaderBoardActivity.class);
                    intent.putExtra("difficulty", gameType);
                    startActivity(intent);
                }
            }
        };
        /*TODO:根据用户选择的难度加载相应的游戏界面*/
        BaseGame baseGameView = null;
        if (gameType.equals("easy")){
            baseGameView = new EasyGame(GameActivity.this, mHandler,0, music);
        } else if (gameType.equals("normal")){
            baseGameView = new MediumGame(GameActivity.this, mHandler,1, music);
        } else if (gameType.equals("difficult")) {
            baseGameView = new HardGame(GameActivity.this, mHandler,2, music);
        }
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