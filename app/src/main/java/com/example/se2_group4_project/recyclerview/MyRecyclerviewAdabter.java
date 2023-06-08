package com.example.se2_group4_project.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.se2_group4_project.R;
import com.example.se2_group4_project.cards.Card;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewAdabter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    List<Card> items;
    int LayoutID;


    public MyRecyclerviewAdabter(Context context, List<Card> items, int layoutID) {
        this.context = context;
        this.items = items;
        this.LayoutID = layoutID;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(this.LayoutID,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
        String drawable = "@drawable/"+items.get(position).getCardFront();
        Log.d("drawable holder", drawable);
        int drawableid = context.getResources().getIdentifier(drawable,null, context.getPackageName());
        Log.d("drawable id", drawableid+"");
        holder.imageView.setImageResource(drawableid);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Card card){
        this.items.add(card);
    }
    public void removeItem(){

    }

}
