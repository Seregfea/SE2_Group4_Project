package com.example.se2_group4_project;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}
