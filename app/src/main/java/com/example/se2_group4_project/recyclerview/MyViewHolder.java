package com.example.se2_group4_project.recyclerview;

import android.view.View;
import android.widget.ImageView;

import com.example.se2_group4_project.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview1);

    }
}
