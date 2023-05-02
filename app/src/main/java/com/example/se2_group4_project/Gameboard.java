package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.CardDrawer;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Gameboard extends AppCompatActivity {
    private DicePopUpActivity dicePopUpActivity;
    private Button btnRollDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        dicePopUpActivity = new DicePopUpActivity(this);
        btnRollDice = findViewById(R.id.btnRollDice);

        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dicePopUpActivity.showAtLocation(view, Gravity.CENTER, 0, 0);
                dicePopUpActivity.rollDice();
            }
        });



        CardDrawer cardDrawer = new CardDrawer();
        try {
            cardDrawer.generateInitialCards();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        LinearLayout linearLayout = findViewById(R.id.UserCardsLayout);

        // ich greife auf stack zu und habe index 0 - 4
        // ich nehme die playerBlueStack z.B. und bekomme da meine Images
        // jeder Spieler bekommt Nummer mit einer Farbe random - von dieser Farbe bekommt er Karten
        // Layout passt sich an


    }
}