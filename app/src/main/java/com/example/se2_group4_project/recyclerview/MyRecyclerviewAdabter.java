package com.example.se2_group4_project.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.se2_group4_project.R;
import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.player.PlayerController;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerviewAdabter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    ArrayList<Card> items;
    PlayerController playerController;
    int LayoutID;

    public MyRecyclerviewAdabter(Context context, PlayerController playerController, int layoutID) {
        this.context = context;
        this.items = playerController.getPlayerUpdatedCards();
        this.LayoutID = layoutID;
        this.playerController = playerController;
        Log.d("player recycler", playerController.getPlayerUpdatedCards().toString());
        Log.d("player recycler2", this.items.toString());
    }

    public MyRecyclerviewAdabter(Context context, ArrayList<Card> items, int layoutID) {
        this.context = context;
        this.items = items;
        this.LayoutID = layoutID;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("viewholder", parent.toString());
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
        return 0;
    }
    public Integer getItemCardsCount(){return this.items.size();}
    public Integer getItemPlayerCardsCount(){return this.playerController.getPlayerUpdatedCards().size();}
    public void addItemCard(Card card){
        this.items.add(card);
    }
    public void addPlayerCard(Card card) {
        this.playerController.addPlayerUpdatedCard(card);
        this.items = this.playerController.getPlayerUpdatedCards();
    }
    public void removeItemCard(Card card){ this.items.remove(card);}
    public void removePlayerCard(Card card){
        this.playerController.removePlayerUpdatedCard(card);
        this.items = this.playerController.getPlayerUpdatedCards();
    }

}
