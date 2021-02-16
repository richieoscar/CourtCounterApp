package com.example.courtcounter.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.courtcounter.model.GamesScoreKeeper;

import java.util.List;

@Dao
public interface GameScoreKeeperDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveGame(GamesScoreKeeper saveGame);

    @Query("SELECT * FROM scoreboard ")
    List<GamesScoreKeeper> getSavedGames();

    @Delete
    void deleteGame(GamesScoreKeeper gameScore);

    @Query("DELETE FROM scoreboard")
    void clear();
}
