package com.example.aircarftwar2024.music;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.example.aircarftwar2024.R;

import java.util.HashMap;
import java.util.Map;

public class MySoundPool {
    public SoundPool soundPool;
    AudioAttributes audioAttributes = null;
    Context context;
    Map<Integer,Integer> soundpoolMap = new HashMap<>();
    public MySoundPool(Context context){
        this.context = context;
        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();
        soundpoolMap.put(1,soundPool.load(context, R.raw.bullet_hit,1));
        soundpoolMap.put(2,soundPool.load(context, R.raw.get_supply,1));
        soundpoolMap.put(3,soundPool.load(context, R.raw.game_over,1));

    }

    public void play_bullet_hit()
    {
        soundPool.play(soundpoolMap.get(1),1,1,0,0,1.2f);
    }
    public void play_get_supply()
    {
        soundPool.play(soundpoolMap.get(2),1,1,0,0,1.2f);
    }
    public void play_game_over()
    {
        soundPool.play(soundpoolMap.get(3),1,1,0,0,1.2f);
    }

}
