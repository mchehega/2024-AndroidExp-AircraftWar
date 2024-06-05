package com.example.aircarftwar2024.scorerank;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Score implements Serializable
{
    private int rank;
    private String name;
    private int score;
    private String time;
    private Difficulty difficulty;

    

    public Score(String name, int score,Difficulty difficulty)
    {
        this.name = name;
        this.score = score;
        this.difficulty=difficulty;

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("MM-dd  HH:mm");
        time = df.format(date);

//        Calendar calendar = Calendar.getInstance();
//        time = ""+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"  "+
//                calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
    }

    

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    

    public int getScore()
    {
        return score;
    }



    public int getRank()
    {
        return rank;
    }



    public String getName()
    {
        return name;
    }



    public void setName(String name)
    {
        this.name = name;
    }



    public void setScore(int score)
    {
        this.score = score;
    }



    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getTime() {
        return time;
    }





    @Override
    public String toString()
    {
        return String.format("第%d名:%s,%d\t,%s,难度：%s", rank, name, score,time,difficulty);
    }


}
