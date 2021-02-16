package com.example.courtcounter.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.courtcounter.model.GamesScoreKeeper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = GamesScoreKeeper.class, version = 2, exportSchema = false)
public abstract class GameScoreDatabase extends RoomDatabase {
    abstract GameScoreKeeperDao gameScoreKeeperDao();

    private static int NUMBER_OF_THREADS = 4;
    private static String dataBaseName = "gameScoreDataBase";
    public final ExecutorService writeToDataBase = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static GameScoreDatabase INSTANCE;

    public static GameScoreDatabase getDataBaseInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (GameScoreDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        GameScoreDatabase.class, dataBaseName)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return INSTANCE;

    }


}
