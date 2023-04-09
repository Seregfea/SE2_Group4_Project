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
    private LinearLayout diceLayout;
    private TextView tvResult;
    private final int diceSize = 150;
    private int numberOfDice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputDice = findViewById(R.id.inputDice);
        btnRollDice = findViewById(R.id.btnRollDice);
        diceLayout = findViewById(R.id.diceLayout);
        tvResult = findViewById(R.id.textViewResult);

        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceLayout.removeAllViews();

                String input = inputDice.getText().toString();
                if (!input.isEmpty()) {
                    numberOfDice = Integer.parseInt(input);
                }

                List<Integer> results = Dice.rollDice(numberOfDice);
                String resultText = "Results: ";
                for (int i = 0; i < results.size(); i++) {
                    if (i != 0) {
                        resultText += ", ";
                    }
                    resultText += results.get(i);
                    ImageView dieImage = new ImageView(getApplicationContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    layoutParams.setMargins(5, 0, 5, 0);
                    dieImage.setLayoutParams(layoutParams);
                    dieImage.getLayoutParams().height = diceSize;
                    dieImage.getLayoutParams().width = diceSize;
                    dieImage.setImageResource(getDrawableId(results.get(i)));
                    diceLayout.addView(dieImage);
                }
                tvResult.setText(resultText);
            }
        });
    }

    private int getDrawableId(int result) {
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