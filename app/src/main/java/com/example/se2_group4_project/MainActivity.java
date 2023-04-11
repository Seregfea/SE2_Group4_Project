package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText inputDice;
    private Button btnRollDice;
    private Button btnSubmit;
    private LinearLayout diceLayout;
    private MediaPlayer mediaPlayer;
    private final int diceSize = 150;
    private int numberOfDice = 0;
    private int delayTime = 20;
    private int rollAnimation = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputDice = findViewById(R.id.inputDice);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnRollDice = findViewById(R.id.btnRollDice);
        diceLayout = findViewById(R.id.diceLayout);
        mediaPlayer = MediaPlayer.create(this, R.raw.roll);

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
                rollDice();
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                }
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

    private void rollDice() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int j = 0; j < rollAnimation; j++) {
                    List<Integer> diceValues = Dice.rollDice(numberOfDice);
                    ImageView diceImage;
                    for (int i = 0; i < diceValues.size(); i++) {
                        diceImage = (ImageView) diceLayout.getChildAt(i);
                        refreshDiceOutput(diceImage, diceValues.get(i));
                    }
                    try {
                        Thread.sleep(delayTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                return null;
            }
        }.execute();
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