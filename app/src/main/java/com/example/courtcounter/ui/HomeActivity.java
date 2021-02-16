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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bindViews();
        start();

    }

    private void bindViews() {
        teamAName = findViewById(R.id.editTextText_teamA);
        teamBName = findViewById(R.id.editText_teamB);
        startGame = findViewById(R.id.button_start);
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
            }

        });
    }
}