package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.se2_group4_project.client.Client;
import com.example.se2_group4_project.dices.DicePopUpActivity;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.CardDrawer;

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

        // Client connects to server
        Client client = new Client();
        Bundle extra = getIntent().getExtras();
        String ip = extra.getString("ip");
        Toast.makeText(this, "Connected with" + ip, Toast.LENGTH_SHORT).show();
        //client.startConnection(ip);

        CardDrawer c = new CardDrawer(this.getApplicationContext());
        try {
            c.generateInitialCards();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        addCardsToLinearLayout(R.id.CardsLayoutLeft, c.getPlayerBlueStack());
        addCardsToLinearLayout(R.id.CardsLayoutTop, c.getPlayerGreenStack());
        addCardsToLinearLayout(R.id.CardsLayoutRight, c.getPlayerOrangeStack());
        addCardsToLinearLayout(R.id.UserCardsLayout, c.getPlayerTealStack());
        addCardsToLinearLayout(R.id.roommateDifficultLayout, c.getRoommateDifficultStack());
        addCardsToLinearLayout(R.id.roommateEasyLayout, c.getRoommateEasyStack());
        addCardsToLinearLayout(R.id.witzigLayout, c.getWitzigStack());
        addCardsToLinearLayout(R.id.witzigWitzigLayout, c.getWitzigWitzigStack());
        addCardsToLinearLayout(R.id.troublemakerLayout, c.getTroublemakerStack());
        addCardsToLinearLayout(R.id.ItemCardsLayout, c.getItemsStack());
    }

    public void addCardsToLinearLayout(int linearLayoutId, ArrayList<Card> cards) {
        LinearLayout linearLayout = findViewById(linearLayoutId);
        for (Card card : cards) {
            ImageView iView = new ImageView(linearLayout.getContext());
            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getApplicationContext().getPackageName());
            iView.setImageResource(imageRessourceID);
            iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
            linearLayout.addView(iView);
        }
    }
}
