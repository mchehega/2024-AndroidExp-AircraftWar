package com.example.aircarftwar2024.game;

import android.content.Context;
import android.os.Handler;

import com.example.aircarftwar2024.ImageManager;
import com.example.aircarftwar2024.aircraft.AbstractEnemyAircraft;

import java.util.LinkedList;
import java.util.List;


public class EasyGame extends BaseGame{

    public EasyGame(Context context, Handler handler,int difficulty, boolean music) {
        super(context, handler,difficulty, music, false);
        this.backGround = ImageManager.BACKGROUND1_IMAGE;
        this.enemyMaxNumber = 2;
    }

    @Override
    protected void tick() {
    }

    /**
     * 简单模式没有 boss
     * @return
     */
    @Override
    protected List<AbstractEnemyAircraft> produceBoss() {
        return new LinkedList<>();
    }


}
