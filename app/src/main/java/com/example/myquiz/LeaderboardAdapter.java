package com.example.myquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder> {

    private List<LeaderboardViewModel> leaderboardViewModelList;
    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1, tv2, tv3;

        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tt1);
            tv2 = (TextView) itemView.findViewById(R.id.tt2);
            tv3 = (TextView) itemView.findViewById(R.id.tt3);
        }
    }
    public LeaderboardAdapter(List<LeaderboardViewModel> leaderboardViewModelList)
    {
        this.leaderboardViewModelList=leaderboardViewModelList;
    }



    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_item_layout,parent,false);
        LeaderboardViewHolder leaderboardViewHolder = new LeaderboardViewHolder(itemView);
        return leaderboardViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        LeaderboardViewModel leaderboardViewModel = leaderboardViewModelList.get(position);
        holder.tv1.setText(""+(position+4));
        holder.tv2.setText(leaderboardViewModel.getName());
        holder.tv3.setText(""+leaderboardViewModel.getScore());
    }

    @Override
    public int getItemCount() {
        return leaderboardViewModelList.size();
    }


}