package com.example.se2_group4_project.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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
    int layoutID;
    int playerID;

    public MyRecyclerviewAdabter(Context context, PlayerController playerController, int layoutID, int player) {
        Log.d("recycler array",""+playerController);
        Log.d("recycler arraylist", ""+playerController.getPlayerInitialCards());
        this.context = context;
        this.items = new ArrayList<>();
        this.playerID = player;
        this.layoutID = layoutID;
        this.playerController = playerController;
        switch (playerID) {
            case 0:
                this.items = this.playerController.getPlayerInitialCards();
                break;
            case 1:
                this.items = this.playerController.getPlayerOneCards();
                break;
            case 2:
                this.items = this.playerController.getPlayerTwoCards();
                break;
            case 3:
                this.items = this.playerController.getPlayerThreeCards();
                break;
            default:
                break;
        }
       //getCardsArray(this.playerID);
        Log.d("palyer recycler", this.playerController.toString());
    }


    public void removeCardsArray(Card card){
        switch (this.playerID) {
            case 0:
                this.items.remove(card);
                this.playerController.setPlayerInitialCards(this.items);
                break;
            case 1:
                this.items.remove(card);
                this.playerController.setPlayerOneCards(this.items);
                break;
            case 2:
                this.items.remove(card);
                this.playerController.setPlayerTwoCards(this.items);
                break;
            case 3:
                this.items.remove(card);
                this.playerController.setPlayerThreeCards(this.items);
                break;
            default:
                break;
        }
    }
    public void addCardsArray(Card card){
        switch (this.playerID) {
            case 0:
                this.items.add(card);
                this.playerController.setPlayerInitialCards(this.items);
                break;
            case 1:
                this.items.add(card);
                this.playerController.setPlayerOneCards(this.items);
                break;
            case 2:
                this.items.add(card);
                this.playerController.setPlayerTwoCards(this.items);
                break;
            case 3:
                this.items.add(card);
                this.playerController.setPlayerThreeCards(this.items);
                break;
            default:
                break;
        }
    }
    public PlayerController getPlayer(){
        return this.playerController;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("viewholder cards",viewType+"" );
        return new MyViewHolder(LayoutInflater.from(context).inflate(this.layoutID,parent,false));
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
        return this.items.size();
    }




}
