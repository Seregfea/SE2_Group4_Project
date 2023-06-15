package com.example.se2_group4_project.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.player.PlayerController;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewAdabter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    List<Card> items;
    PlayerController playerController;
    int LayoutID;


    public MyRecyclerviewAdabter(Context context, List<Card> items, int layoutID) {
        this.context = context;
        this.items = items;
        this.LayoutID = layoutID;
    }

    public MyRecyclerviewAdabter(Context context, PlayerController playerController, int layoutID) {
        this.context = context;
        this.items = playerController.getPlayerInitialCards();
        this.LayoutID = layoutID;
        this.playerController = playerController;
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

    public void addPlayerItem(Card card){
        Log.d("add card recycler", card+"");
        Log.d("add card recycler", this.playerController.getPlayerInitialCards()+"");
        this.playerController.addPlayerInitialCard(card);
        this.items = this.playerController.getPlayerInitialCards();
    }

    public void removePlayerItemItem(Card card){
        this.playerController.removePlayerInitialCard(card);
        this.items = this.playerController.getPlayerInitialCards();
    }
    public void addCardItem(Card card){
        this.items.add(card);
    }
    public void removeCardItem(Card card){
        this.items.remove(card);
    }

}
