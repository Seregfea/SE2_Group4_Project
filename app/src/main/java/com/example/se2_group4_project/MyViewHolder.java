package com.example.se2_group4_project;

import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    LinearLayout playerCardsLayout;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        playerCardsLayout = itemView.findViewById(R.id.UserCardsLayout);
    }
}
