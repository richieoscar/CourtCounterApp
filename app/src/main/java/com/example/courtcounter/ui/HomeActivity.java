package com.example.courtcounter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.courtcounter.R;

public class HomeActivity extends AppCompatActivity {

    private EditText teamAName;
    private EditText teamBName;
    private Button startGame;
    private Button gameHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bindViews();
        start();
        openGameScoreHistory();

    }

    private void bindViews() {
        teamAName = findViewById(R.id.editTextText_teamA);
        teamBName = findViewById(R.id.editText_teamB);
        startGame = findViewById(R.id.button_start);
        gameHistory = findViewById(R.id.button_game_home_history);
    }

    private void start() {
        startGame.setOnClickListener(v -> {
            String teamA = teamAName.getText().toString();
            String teamB = teamBName.getText().toString();

            if (teamA.isEmpty() || teamB.isEmpty()) {
                teamAName.setError("Required");
                return;
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("teamAName", teamA);
                intent.putExtra("teamBName", teamB);
                startActivity(intent);
                teamAName.setText("");
                teamBName.setText("");
                teamAName.requestFocus();
            }

        });
    }

    private void openGameScoreHistory() {
        gameHistory.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameScoreHistoryActivity.class);
            startActivity(intent);
        });
    }
}