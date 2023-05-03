package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.se2_group4_project.dices.DicePopUpActivity;
import com.example.se2_group4_project.gameboard_layouts.CardsLayoutLeft;

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


        LinearLayout linearLayout = findViewById(R.id.UserCardsLayout);
        CardsLayoutLeft left = new CardsLayoutLeft(linearLayout);

        ImageView drei = new ImageView(this);
        drei.setImageResource(R.drawable.bad_dreckig_hellblau);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60, LinearLayout.LayoutParams.MATCH_PARENT);
        drei.setLayoutParams(params);

// create the second ImageView and set its width to 60dp
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.bad_dreckig_hellblau);
        params = new LinearLayout.LayoutParams(60, LinearLayout.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);

// create the third ImageView and set its width to 60dp
        ImageView zwei = new ImageView(this);
        zwei.setImageResource(R.drawable.couch_dreckig_orange);
        params = new LinearLayout.LayoutParams(60, LinearLayout.LayoutParams.MATCH_PARENT);
        zwei.setLayoutParams(params);

// add the ImageViews to the layout
        left.addImage(drei);
        left.addImage(imageView);
        left.addImage(zwei);
    }
}



        /*CardDrawer cardDrawer = new CardDrawer();
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


         */

