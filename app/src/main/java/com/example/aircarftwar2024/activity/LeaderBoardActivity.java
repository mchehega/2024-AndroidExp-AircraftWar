package com.example.aircarftwar2024.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircarftwar2024.R;
import com.example.aircarftwar2024.game.EasyGame;
import com.example.aircarftwar2024.game.HardGame;
import com.example.aircarftwar2024.game.MediumGame;
import com.example.aircarftwar2024.scorerank.Difficulty;
import com.example.aircarftwar2024.scorerank.Score;
import com.example.aircarftwar2024.scorerank.ScoreDaoImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderBoardActivity extends AppCompatActivity {

    Context context = this;
    private String gameType;
    private Difficulty difficulty1 = Difficulty.easy;
    //生成适配器的Item和动态数组对应的元素
    private SimpleAdapter listItemAdapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
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
        ActivityManager.getActivityManager().addActivity(this);
        TextView textView = (TextView)findViewById(R.id.textView2);
        ListView list = (ListView) findViewById(R.id.ListView01);
        Button button = (Button) findViewById(R.id.button);
        List<Map<String, Object>> datalist;
        try {
            datalist = getData();
            listItemAdapter = new SimpleAdapter(
                    this,
                    datalist,
                    R.layout.listitem,
                    new String[]{"rank","name","score","time"},
                    new int[]{R.id.rank,R.id.name,R.id.score,R.id.time});
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        list.setAdapter(listItemAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    deleteData(datalist, i);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager.getActivityManager().finishActivity();
                Activity main = ActivityManager.getActivityManager().currentActivity();
                Intent intent = new Intent(LeaderBoardActivity.this, main.getClass());
                startActivity(intent);
            }
        });
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

    private void deleteData(List<Map<String, Object>> list, int which) throws IOException {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否要删除").setTitle("删除分数");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                list.remove(which);
                try {
                    ScoreDaoImpl scoreDaoImpl = new ScoreDaoImpl(context);
                    List<Score> list1 = scoreDaoImpl.getInDifficulty(difficulty1);
                    scoreDaoImpl.deleteScore(list1.get(which));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                listItemAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        return;
    }
}
