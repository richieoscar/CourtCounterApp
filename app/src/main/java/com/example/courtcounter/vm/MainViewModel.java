package com.example.courtcounter.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.courtcounter.db.GameScoreKeeperRepository;
import com.example.courtcounter.model.GamesScoreKeeper;

import java.util.Random;

public class MainViewModel extends AndroidViewModel {


    public static final int THREE_POINTS = 3;
    public static final int TWO_POINTS = 2;
    public static final int FREE_THROW = 1;

    public int scoreForTeamA;
    public int scoreForTeamB;
    public String teamAName;
    public String teamBName;
    public int sumA;
    public int sumB;
    GameScoreKeeperRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new GameScoreKeeperRepository(application);

    }


    public int generateId() {
        Random random = new Random();
        int gameID = random.nextInt(100);

        return gameID;
    }

    public void saveGameScore(GamesScoreKeeper gamesScore) {
        repository.saveGameScore(gamesScore);
    }

}
