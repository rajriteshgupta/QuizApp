package com.example.myquiz;

public class LeaderboardViewModel {

    private int rank;
    private String name;
    private int score;
    LeaderboardViewModel(int rank,String name,int score)
    {
        this.rank=rank;
        this.name=name;
        this.score=score;
    }

    public int getRank() {
        return rank;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setScore(int score) {
        this.score = score;
    }
}