package com.example.courtcounter.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "scoreboard")
public class GamesScoreKeeper {

    @NonNull
    @PrimaryKey
    private int gameId;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public int getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    @NonNull
    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(@NonNull String teamAName) {
        this.teamAName = teamAName;
    }

    @NonNull
    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(@NonNull String teamBName) {
        this.teamBName = teamBName;
    }

    @NonNull
    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(@NonNull String gameDate) {
        this.gameDate = gameDate;
    }

    public GamesScoreKeeper(int gameId, int teamAScore, int teamBSore, @NonNull String teamAName, @NonNull String teamBName, @NonNull String gameDate) {
        this.gameId = gameId;
        this.teamAScore = teamAScore;
        this.teamBScore = teamBSore;
        this.teamAName = teamAName;
        this.teamBName = teamBName;
        this.gameDate = gameDate;
    }

    public GamesScoreKeeper() {

    }

    @NonNull
    private int teamAScore;

    @NonNull
    private int teamBScore;

    @NonNull
    private String teamAName;

    @NonNull
    private String teamBName;

    @NonNull
    private String gameDate;

    @Override
    public String toString() {
        return "GamesScoreKeeper{" +
                "gameId=" + gameId +
                ", teamAScore=" + teamAScore +
                ", teamBScore=" + teamBScore +
                ", teamAName='" + teamAName + '\'' +
                ", teamBName='" + teamBName + '\'' +
                ", gameDate='" + gameDate + '\'' +
                '}';
    }
}
