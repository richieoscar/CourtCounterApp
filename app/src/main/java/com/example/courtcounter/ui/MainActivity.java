package com.example.courtcounter.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.courtcounter.R;
import com.example.courtcounter.model.GamesScoreKeeper;
import com.example.courtcounter.vm.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button teamAThreePoints;
    private Button teamBThreePoints;
    private Button teamATwoPoints;
    private Button teamBTwoPoints;
    private Button teamAFreeThrow;
    private Button teamBFreeThrow;
    private TextView teamAScore;
    private TextView teamBScore;
    private TextView teamAName;
    private TextView teamBName;
    private MainViewModel viewModel;
    private Button reset;
    private TextView gameDate;
    private GamesScoreKeeper gamesScoreKeeper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        handleIntent();
        clickOptions();
        displayGameDate();
        displayScore();


    }

    private void handleIntent() {
        viewModel.teamAName = getIntent().getStringExtra("teamAName");
        viewModel.teamBName = getIntent().getStringExtra("teamBName");
        String teamANamee = viewModel.teamAName;
        String teamBNamee = viewModel.teamBName;
        teamAName.setText(teamANamee);
        teamBName.setText(teamBNamee);
    }


    private void bindViews() {
        teamAThreePoints = findViewById(R.id.button_team_a_three_points);
        teamBThreePoints = findViewById(R.id.button_team_b_three_points);
        teamATwoPoints = findViewById(R.id.button_team_a_two_points);
        teamBTwoPoints = findViewById(R.id.button_team_b_two_points);
        teamAFreeThrow = findViewById(R.id.button_team_a_free_throw);
        teamBFreeThrow = findViewById(R.id.button_team_b_free_throw);
        teamAScore = findViewById(R.id.textView_team_a_score);
        teamBScore = findViewById(R.id.textView_team_b_score);
        teamAName = findViewById(R.id.textView_team_a_name);
        teamBName = findViewById(R.id.textView_team_b_name);
        reset = findViewById(R.id.button_reset);
        gameDate = findViewById(R.id.textView_game_date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_save_score:
                saveScore();
                break;
            case R.id.item_exit:
                saveOnExit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (gamesScoreKeeper == null) {
            saveOnExit();
        } else {
            super.onBackPressed();

        }
    }

    private void saveOnExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.save_game_score);
        builder.setTitle(R.string.exit_game);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveScore();
                finish();
            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.create();
        builder.show();
    }


    private GamesScoreKeeper saveScore() {
        if (gamesScoreKeeper == null) {
            gamesScoreKeeper = new GamesScoreKeeper();
            gamesScoreKeeper.setGameId(viewModel.generateId());
            gamesScoreKeeper.setTeamAName(teamAName.getText().toString());
            gamesScoreKeeper.setTeamBName(teamBName.getText().toString());

            int scoreA = Integer.parseInt(teamAScore.getText().toString());
            int scoreB = Integer.parseInt(teamBScore.getText().toString());
            gamesScoreKeeper.setTeamAScore(scoreA);
            gamesScoreKeeper.setTeamBScore(scoreB);
            gamesScoreKeeper.setGameDate(gameDate.getText().toString());

            viewModel.saveGameScore(gamesScoreKeeper);
            Toast.makeText(this, getString(R.string.game_score_saved), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.game_already_saved), Toast.LENGTH_SHORT).show();
        }
        return gamesScoreKeeper;
    }


    private void displayGameDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d");
        String gameDay = dateFormat.format(date);
        gameDate.setText(gameDay.toUpperCase() + "  " + getString(R.string.play_offs).toUpperCase());


    }

    //buttons to increment score board
    private void clickOptions() {
        ArrayList<Button> pointButtons = new ArrayList<>();
        pointButtons.add(teamAThreePoints);
        pointButtons.add(teamBThreePoints);
        pointButtons.add(teamATwoPoints);
        pointButtons.add(teamBTwoPoints);
        pointButtons.add(teamAFreeThrow);
        pointButtons.add(teamBFreeThrow);
        pointButtons.add(reset);

        for (Button button : pointButtons) {
            int id = button.getId();
            switch (id) {
                case R.id.button_team_a_three_points:
                    button.setOnClickListener(v -> {
                        viewModel.sumA = viewModel.sumA + viewModel.scoreForTeamA + viewModel.THREE_POINTS;
                        teamAScore.setText(String.valueOf(viewModel.sumA));

                    });
                    break;
                case R.id.button_team_b_three_points:
                    button.setOnClickListener(v -> {
                        viewModel.sumB = viewModel.sumB + viewModel.scoreForTeamB + viewModel.THREE_POINTS;
                        teamBScore.setText(String.valueOf(viewModel.sumB));

                    });
                    break;
                case R.id.button_team_a_two_points:
                    button.setOnClickListener(v -> {
                        viewModel.sumA = viewModel.sumA + viewModel.scoreForTeamA + viewModel.TWO_POINTS;
                        teamAScore.setText(String.valueOf(viewModel.sumA));
                    });
                    break;
                case R.id.button_team_b_two_points:
                    button.setOnClickListener(v -> {
                        viewModel.sumB = viewModel.sumB + viewModel.scoreForTeamB + viewModel.TWO_POINTS;
                        teamBScore.setText(String.valueOf(viewModel.sumB));
                    });
                    break;
                case R.id.button_team_a_free_throw:
                    button.setOnClickListener(v -> {
                        viewModel.sumA = viewModel.sumA + viewModel.scoreForTeamA + viewModel.FREE_THROW;
                        teamAScore.setText(String.valueOf(viewModel.sumA));
                    });
                    break;
                case R.id.button_team_b_free_throw:
                    button.setOnClickListener(v -> {
                        viewModel.sumB = viewModel.sumB + viewModel.scoreForTeamB + viewModel.FREE_THROW;
                        teamBScore.setText(String.valueOf(viewModel.sumB));
                    });
                    break;

                case R.id.button_reset:
                    button.setOnClickListener(v -> {
                        reset();

                    });
                    break;
                default:
                    viewModel.sumA = 0;
                    teamAScore.setText(String.valueOf(viewModel.sumA));
                    teamBScore.setText(String.valueOf(viewModel.sumB));
                    break;
            }

        }
    }

    /*
    Displays score on screen
     */
    private void displayScore() {
        teamAScore.setText(String.valueOf(viewModel.sumA));
        teamBScore.setText(String.valueOf(viewModel.sumB));
    }

    //resets score
    private void reset() {
        int resetA = viewModel.sumA = 0;
        int resetB = viewModel.sumB = 0;
        teamAScore.setText(String.valueOf(resetA));
        teamBScore.setText(String.valueOf(resetB));
        gamesScoreKeeper = null;

    }


}