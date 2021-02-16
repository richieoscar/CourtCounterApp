package com.example.courtcounter.db;

import android.app.Application;

import com.example.courtcounter.model.GamesScoreKeeper;

import java.util.List;

public class GameScoreKeeperRepository {

    GameScoreDatabase gameScoreDatabase;
    GameScoreKeeperDao dao;
    List<GamesScoreKeeper> mSavedGames;


    public GameScoreKeeperRepository(Application application) {
        gameScoreDatabase = GameScoreDatabase.getDataBaseInstance(application);
        dao = gameScoreDatabase.gameScoreKeeperDao();
    }

    public void saveGameScore(GamesScoreKeeper gameScore) {
        gameScoreDatabase.writeToDataBase.execute(() -> {
            dao.saveGame(gameScore);

        });

    }

    public List<GamesScoreKeeper> getSavedGameScores() {
        return mSavedGames = dao.getSavedGames();
    }

    public void deleteGameScore(GamesScoreKeeper gameScore) {

        gameScoreDatabase.writeToDataBase.execute(() -> {
            dao.deleteGame(gameScore);
        });
    }

    public void deleteGameHistory() {
        dao.clear();
    }

}
