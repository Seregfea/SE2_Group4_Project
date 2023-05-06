package com.example.se2_group4_project;

import static java.security.AccessController.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.se2_group4_project.dices.DicePopUpActivity;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.CardDrawer;
import com.example.se2_group4_project.client.Client;
import com.example.se2_group4_project.gameboard_layouts.CardsLayoutLeft;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Gameboard extends AppCompatActivity {
    private DicePopUpActivity dicePopUpActivity;
    private LinearLayout availableDiceLayout;
    private Button btnRollDice;
    // hardcoded
    // später: methode aus player-klasse
    private int availableDices = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        dicePopUpActivity = new DicePopUpActivity(this);
        btnRollDice = findViewById(R.id.btnRollDice);
        availableDiceLayout = findViewById(R.id.availableDiceContainer);

        for (int i = 0; i < availableDices; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.dice);
            availableDiceLayout.addView(imageView);
        }

        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dicePopUpActivity.showAtLocation(view, Gravity.CENTER, 0, 0);
                dicePopUpActivity.rollDice();
            }
        });

        CardDrawer c = new CardDrawer(this.getApplicationContext());
        try {
            c.generateInitialCards();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //wuerde gerne Methoden darausmachen und dann callen
        //Methode player1,2,3,4

        ArrayList<Card> player1 = c.getPlayerBlueStack();
        for (Card card : player1) {
            LinearLayout playerBlue = findViewById(R.id.CardsLayoutLeft);
            ImageView iView = new ImageView(playerBlue.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            playerBlue.addView(iView);
        }

        ArrayList<Card> player2 = c.getPlayerGreenStack();
        for (Card card : player2) {
            LinearLayout playerGreen = findViewById(R.id.CardsLayoutTop);
            ImageView iView = new ImageView(playerGreen.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            playerGreen.addView(iView);
        }

        ArrayList<Card> player3 = c.getPlayerOrangeStack();
        for (Card card : player3) {
            LinearLayout playerOrange = findViewById(R.id.CardsLayoutRight);
            ImageView iView = new ImageView(playerOrange.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            playerOrange.addView(iView);
        }

        ArrayList<Card> player4 = c.getPlayerTealStack();
        for (Card card : player4) {
            LinearLayout playerTeal = findViewById(R.id.UserCardsLayout);
            ImageView iView = new ImageView(playerTeal.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            playerTeal.addView(iView);
        }

        ArrayList<Card> roommateDifficultStack = c.getRoommateDifficultStack();
        for (Card card : roommateDifficultStack) {
            LinearLayout roommateDifficult = findViewById(R.id.roommateDifficultLayout);
            ImageView iView = new ImageView(roommateDifficult.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            roommateDifficult.addView(iView);
        }

        ArrayList<Card> roommateEasyStack = c.getRoommateEasyStack();
        for (Card card : roommateEasyStack) {
            LinearLayout roommateEasy = findViewById(R.id.roommateEasyLayout);
            ImageView iView = new ImageView(roommateEasy.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            roommateEasy.addView(iView);
        }

        ArrayList<Card> witzigStack = c.getWitzigStack();
        for (Card card : witzigStack) {
            LinearLayout witzig = findViewById(R.id.witzigLayout);
            ImageView iView = new ImageView(witzig.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            witzig.addView(iView);
        }

        ArrayList<Card> witzigWitzigStack = c.getWitzigWitzigStack();
        for (Card card : witzigWitzigStack) {
            LinearLayout witzigWitzig = findViewById(R.id.witzigWitzigLayout);
            ImageView iView = new ImageView(witzigWitzig.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            witzigWitzig.addView(iView);
        }

        ArrayList<Card> troublemakerStack = c.getTroublemakerStack();
        for (Card card : troublemakerStack) {
            LinearLayout troublemaker = findViewById(R.id.troublemakerLayout);
            ImageView iView = new ImageView(troublemaker.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            troublemaker.addView(iView);
        }

        ArrayList<Card> itemStack = c.getItemsStack();
        for (Card card : itemStack) {
            LinearLayout items = findViewById(R.id.ItemCardsLayout);
            ImageView iView = new ImageView(items.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            items.addView(iView);
        }

    }
}
