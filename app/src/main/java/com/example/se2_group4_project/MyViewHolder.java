package com.example.se2_group4_project;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    RelativeLayout playerCardsLayout;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        playerCardsLayout = itemView.findViewById(R.id.UserCardsLayout);
    }
}
