package com.example.courtcounter.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.courtcounter.db.GameScoreKeeperRepository;
import com.example.courtcounter.model.GamesScoreKeeper;

import java.util.List;

public class GameScoreHistoryViewModel extends AndroidViewModel {
    GameScoreKeeperRepository repository;

    public GameScoreHistoryViewModel(@NonNull Application application) {
        super(application);

        repository = new GameScoreKeeperRepository(application);

    }

    public List<GamesScoreKeeper> getSavedGameScore() {
        return repository.getSavedGameScores();
    }

    public void clearGameScoreHistory() {
        repository.deleteGameHistory();
    }
}
