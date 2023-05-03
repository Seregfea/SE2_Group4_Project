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
        CardDrawer c = new CardDrawer(this.getApplicationContext());
        try {
            c.generateInitialCards();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Card> player1 = c.getPlayerBlueStack();
        for (Card card: player1) {
            LinearLayout playerBlue = findViewById(R.id.CardsLayoutLeft);
            ImageView iView = new ImageView(playerBlue.getContext());
            final int imageRessourceID =
                    getResources()
                            .getIdentifier(
                                    card.getCardFront(), "drawable", this.getBaseContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setVisibility(View.VISIBLE);
            //LinearLayout.LayoutParams params = new LinearLayout
              //  .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
           iView.setLayoutParams(new LinearLayout.LayoutParams(50,50));
            playerBlue.addView(iView);
            break;
        }

    }
}