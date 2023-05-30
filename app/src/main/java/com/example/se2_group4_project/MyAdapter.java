package com.example.se2_group4_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.CardDrawer;
import com.example.se2_group4_project.cards.Player;
import com.example.se2_group4_project.cards.PlayerTeal;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    ArrayList<Card> playerCards;

    public MyAdapter(Context context, ArrayList<Card> playerCards) {
        this.context = context;
        this.playerCards = playerCards;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.player_cards_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        for (Card card : playerCards){
//            ImageView imageView = new ImageView(holder.playerCardsLayout.getContext());
//            imageView.setImageResource(playerCards.get(position).getImageViewID());
//            card.setImageViewID(imageView.getId());
//        }
        // Set images ins RecycleView
//        ImageView imageView = new ImageView(holder.playerCardsLayout.getContext());
//        imageView.setImageResource(playerCards.get(position).getImageViewID());

        ListView listView = new ListView(holder.playerCardsList.getContext());
        listView.setSelection(playerCards.get(position).getImageViewID());

        // setText
        // setText
        // setImgeRessource(items.get(position).getImage)
    }

    @Override
    public int getItemCount() {
        return playerCards.size();
    }
}
