package com.example.aircarftwar2024.scorerank;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.util.Log;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.StatementEvent;

public class ScoreDaoImpl implements ScoreDao
{
    private List<Score> list = new ArrayList<>();
    private File file = new File("ranklist.dat");
    private String file1 = "ranklist.dat";
    private int num;
    private Context context;
    public ScoreDaoImpl(Context context) throws FileNotFoundException {
        this.context = context;
//        FileOutputStream fileOutputStream = context.openFileOutput(file1,MODE_PRIVATE);
        read();
    }

    @Override
    public void add(Score score) throws FileNotFoundException {
        num++;
        list.add(score);
        Log.i("abc", "add: ");
        process();
    }

    @Override
    public void deleteRank(int rank) throws FileNotFoundException {
        num--;
        list.remove(rank-1);
        process();
    }

    @Override
    public void deleteScore(Score score) throws FileNotFoundException {
        num--;
        list.remove(score);
        process();
    }

    @Override
    public List<Score> getAll()
    {
        return list;
    }

    @Override
    public Score getOne(int rank)
    {
        return list.get(rank);
    }

    @Override
    public List<Score> getInDifficulty(Difficulty difficulty) {
        Log.i("abc", "getInDifficulty: ");
        Log.i("abc", list.size()+"");

//        List<Score> difList = list.stream()
//                .filter((Score s) -> s.getDifficulty() == difficulty)
//                .collect(Collectors.toList());
        List<Score> difList =new ArrayList<>();
        for (Score score: list  ) {
            System.out.println(score.getDifficulty());
            System.out.println(difficulty);
            if (score.getDifficulty()==difficulty){
                difList.add(score);
            }
        }
        Log.i("abc", difList.size()+"");
        difList.sort(new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2)
            {
                return o2.getScore() - o1.getScore();
            }
        });
        for (int i = 0; i < difList.size(); i++)
        {
            difList.get(i).setRank(i + 1);
        }
        return difList;
    }

    private void read() throws FileNotFoundException {
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = context.openFileInput(file1);
        try
        {
            // 如果文件为空，则无法创建输入流，会抛出EOF异常
            objectInputStream = new ObjectInputStream(fileInputStream);

            num = objectInputStream.readInt();
            
            for (int i = 0; i < num; i++)
            {
                try
                {
                    list.add((Score) objectInputStream.readObject());
                    //Log.i("bcd", "read: ");
                } catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            System.out.println("第一次记录");
            num = 0;
        } finally
        {
            try
            {
                if (objectInputStream != null)
                {
                    objectInputStream.close();
                }

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void write() throws FileNotFoundException {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = context.openFileOutput(file1,MODE_PRIVATE);
        try
        {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeInt(num);
            for (Score score : list)
            {
                objectOutputStream.writeObject(score);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                objectOutputStream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void process() throws FileNotFoundException {
        list.sort(new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2)
            {
                return o2.getScore() - o1.getScore();
            }
        });
        for (int i = 0; i < list.size(); i++)
        {
            list.get(i).setRank(i + 1);
        }
        write();
    }
}
