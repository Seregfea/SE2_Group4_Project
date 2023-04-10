package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText inputDice;
    private Button btnRollDice;
    private Button btnSubmit;
    private LinearLayout diceLayout;
    private TextView tvResult;
    private final int diceSize = 150;
    private int numberOfDice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputDice = findViewById(R.id.inputDice);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnRollDice = findViewById(R.id.btnRollDice);
        diceLayout = findViewById(R.id.diceLayout);
        tvResult = findViewById(R.id.textViewResult);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceLayout.removeAllViews();
                String input = inputDice.getText().toString();
                if (!input.isEmpty()) {
                    numberOfDice = Integer.parseInt(input);
                }
                for (int i = 0; i < numberOfDice; i++) {
                    createDiceLayout(3);
                }
            }
        });

        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> diceValues = Dice.rollDice(numberOfDice);
                String resultText = "Results: ";
                ImageView diceImage;
                for (int i = 0; i < diceValues.size(); i++) {
                    if (i != 0) {
                        resultText += ", ";
                    }
                    resultText += diceValues.get(i);
                    diceImage = (ImageView) diceLayout.getChildAt(i);
                    refreshDiceOutput(diceImage, diceValues.get(i));
                }
                tvResult.setText(resultText);
            }
        });
    }

    private void createDiceLayout(int diceValue) {
        ImageView diceImage = new ImageView(getApplicationContext());
        diceImage.setLayoutParams(new LinearLayout.LayoutParams(diceSize, diceSize));
        diceImage.setPadding(5,0, 5,0);
        refreshDiceOutput(diceImage, diceValue);
        diceLayout.addView(diceImage);
    }

    private void refreshDiceOutput(ImageView diceImage, int diceValue) {
        diceImage.setImageResource(getDiceImage(diceValue));
    }

    private int getDiceImage(int result) {
        switch (result) {
            default:
                return R.drawable.d1;
            case 2:
                return R.drawable.d2;
            case 3:
                return R.drawable.d3;
            case 4:
                return R.drawable.d4;
            case 5:
                return R.drawable.d5;
            case 6:
                return R.drawable.d6;
        }
    }
}