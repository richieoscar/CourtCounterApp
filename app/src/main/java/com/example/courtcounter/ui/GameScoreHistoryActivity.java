package com.example.courtcounter.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courtcounter.R;
import com.example.courtcounter.model.GameScoreHistoryAdapter;
import com.example.courtcounter.model.GamesScoreKeeper;
import com.example.courtcounter.vm.GameScoreHistoryViewModel;

import java.util.List;

public class GameScoreHistoryActivity extends AppCompatActivity {

    List<GamesScoreKeeper> savedGames;
    RecyclerView recyclerView;
    GameScoreHistoryAdapter adapter;
    GameScoreHistoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_score_history);
        recyclerView = findViewById(R.id.soreRv);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("History");
        ab.setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(GameScoreHistoryViewModel.class);
        displaySavedGames();


    }

    private void displaySavedGames() {
        adapter = new GameScoreHistoryAdapter(viewModel.getSavedGameScore());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_clear) {
            viewModel.clearGameScoreHistory();
            recyclerView.setVisibility(View.INVISIBLE);

            Toast.makeText(this, "All games deleted", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);


    }
}