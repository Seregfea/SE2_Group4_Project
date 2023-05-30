package com.example.se2_group4_project;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    // RelativeLayout playerCardsLayout;
    ListView playerCardsList;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        playerCardsList = itemView.findViewById(R.id.UserCardsLayout);
    }
}
