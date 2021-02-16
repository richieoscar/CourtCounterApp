package com.example.courtcounter.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courtcounter.R;

import java.util.List;

public class GameScoreHistoryAdapter extends RecyclerView.Adapter<GameScoreHistoryAdapter.GameScoreHistoryViewholder> {

    List<GamesScoreKeeper> savedGames;

    public GameScoreHistoryAdapter(List<GamesScoreKeeper> savedGames) {
        this.savedGames = savedGames;
    }

    @NonNull
    @Override
    public GameScoreHistoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_game, parent, false);

        return new GameScoreHistoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameScoreHistoryViewholder holder, int position) {

        GamesScoreKeeper gameScores = savedGames.get(position);
        holder.bind(gameScores);

    }

    @Override
    public int getItemCount() {
        return savedGames.size();
    }

    public class GameScoreHistoryViewholder extends RecyclerView.ViewHolder {
        TextView teamAName;
        TextView teamBName;
        TextView teamAScore;
        TextView teamBScore;
        TextView gameDate;

        public GameScoreHistoryViewholder(@NonNull View itemView) {
            super(itemView);

            teamAName = itemView.findViewById(R.id.textView_teamAName_history);
            teamBName = itemView.findViewById(R.id.textView_teamBName_history);
            teamAScore = itemView.findViewById(R.id.textView_teamA_Score_history);
            teamBScore = itemView.findViewById(R.id.textView_teamB_score_history);
            gameDate = itemView.findViewById(R.id.textView_gameDate_history);
        }


        public void bind(GamesScoreKeeper game) {
            teamAName.setText(game.getTeamAName());
            teamBName.setText(game.getTeamBName());
            teamAScore.setText(String.valueOf(game.getTeamAScore()));
            teamBScore.setText(String.valueOf(game.getTeamBScore()));
            gameDate.setText(game.getGameDate());

        }
    }


}
