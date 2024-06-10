package com.example.aircarftwar2024.music
import android.content.Context;
import android.media.MediaPlayer;
import com.example.aircarftwar2024.R
public class MyMediaPlayer {
    private MediaPlayer bgmp;
    private MediaPlayer bossmp;
    private boolen on = false;
    public MyMediaPlayer(Context context, boolen music_c){
        bgmp = MediaPlayer.create(context, R.raw.bgm);
        bossmp = MediaPlayer.create(context, R.raw.boss_bgm);
        on = music_c;
    }
    public void playBgm(){
        if (on == false) return;
        bgmp.start();
        bgmp.setLooping(true);
    }
    public void pauseBgm(){
        if (on == false) return;
        bgmp.pause();
    }
    public void playBoss(){
        if (on == false) return;
        boss_bgm.start();
        boss_bgm.setLooping(true);
    }
    public void stopBoss(){
        if (on == false) return;
        boss_bgm.stop();
    }
    public void continueBgm(){
        if (on == false) return;
        int prog = bgmp.getCurrentPosition();
        bgmp.seekTo(prog);
        bgmp.start();
    }
    public void stopAll(){
        if (on == false) return;
        bgmp.stop();
        boss_bgm.stop();
        bgmp.release();
        boss_bgm.release();
    }
}
