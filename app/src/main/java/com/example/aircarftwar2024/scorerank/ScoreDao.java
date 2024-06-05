package com.example.aircarftwar2024.scorerank;

import java.io.FileNotFoundException;
import java.util.List;

public interface ScoreDao
{
    public List<Score> getAll();

    public Score getOne(int rank);

    public void add(Score score) throws FileNotFoundException;

    public void deleteRank(int rank) throws FileNotFoundException;

    public void deleteScore(Score score) throws FileNotFoundException;

    public List<Score> getInDifficulty(Difficulty difficulty);

}
