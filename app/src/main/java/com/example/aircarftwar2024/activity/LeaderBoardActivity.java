package com.example.aircarftwar2024.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircarftwar2024.R;
import com.example.aircarftwar2024.game.EasyGame;
import com.example.aircarftwar2024.game.HardGame;
import com.example.aircarftwar2024.game.MediumGame;
import com.example.aircarftwar2024.scorerank.Difficulty;
import com.example.aircarftwar2024.scorerank.Score;
import com.example.aircarftwar2024.scorerank.ScoreDaoImpl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderBoardActivity extends AppCompatActivity {
    private String gameType;
    private Difficulty difficulty1 = Difficulty.easy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(getIntent() != null){
            gameType = getIntent().getStringExtra("difficulty");
        }
        if (gameType.equals("easy")){
            difficulty1 =Difficulty.easy;
        } else if (gameType.equals("normal")){
            difficulty1 = Difficulty.normal;
        } else if (gameType.equals("difficult")) {
            difficulty1 = Difficulty.difficult;
        }
        Log.i("abc", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        TextView textView = (TextView)findViewById(R.id.textView2);
        ListView list = (ListView) findViewById(R.id.ListView01);
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = null;
        try {
            listItemAdapter = new SimpleAdapter(
                    this,
                    getData(),
                    R.layout.listitem,
                    new String[]{"rank","name","score","time"},
                    new int[]{R.id.rank,R.id.name,R.id.score,R.id.time});
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        list.setAdapter(listItemAdapter);
        if(getIntent() != null){
            gameType = getIntent().getStringExtra("difficulty");
        }
        if (gameType.equals("easy")){
            textView.setText("简单模式");
            difficulty1 =Difficulty.easy;
        } else if (gameType.equals("normal")){
            textView.setText("普通模式");
            difficulty1 = Difficulty.normal;
        } else if (gameType.equals("difficult")) {
            textView.setText("困难模式");
            difficulty1 = Difficulty.difficult;
        }

    }
    private List<Map<String, Object>> getData() throws FileNotFoundException {
        Log.i("abc", "getData: ");
        ArrayList<Map<String, Object>> listitema = new ArrayList<Map<String, Object>>();
        ScoreDaoImpl scoreDaoImpl = new ScoreDaoImpl(this);
        List<Score> list = scoreDaoImpl.getInDifficulty(difficulty1);
        Log.i("abc", list.size()+"");
        Map<String, Object> map ;
        Log.i("abc", "getData: ");
        for (int i = 0; i < list.size(); i++) {
            map = new HashMap<String, Object>();
            map.put("rank",list.get(i).getRank()+"");
            map.put("name",list.get(i).getName());
            map.put("score",list.get(i).getScore()+"");
            map.put("time",list.get(i).getTime());
            Log.i("data", map.toString());
            listitema.add(map);
        }
        return listitema;
    }

}
