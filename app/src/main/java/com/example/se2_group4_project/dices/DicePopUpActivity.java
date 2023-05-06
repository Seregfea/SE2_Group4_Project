package com.example.se2_group4_project.dices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.se2_group4_project.R;
import com.example.se2_group4_project.player.Player;

import java.util.List;

public class DicePopUpActivity extends PopupWindow {
    private View dicePopUpView;
    private MediaPlayer mediaPlayer;
    private LinearLayout diceLayout;
    private final int diceSize = 80;
    private int numberOfDice = 0;
    private int delayTime = 20;
    private int rollAnimation = 40;


    @SuppressLint("InflateParams")
    public DicePopUpActivity(Context context) {
        super(context);
        dicePopUpView = LayoutInflater.from(context).inflate(R.layout.activity_dice, null);
        setContentView(dicePopUpView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        diceLayout = dicePopUpView.findViewById(R.id.diceLayout);
        mediaPlayer = MediaPlayer.create(context, R.raw.roll);
    }

    public void rollDice() {
        numberOfDice = Player.getDiceCount();
        diceLayout.removeAllViews();

        for (int i = 0; i < numberOfDice; i++) {
            createDiceLayout(3);
        }
        rollDiceAnimation();
    }

    @SuppressLint("StaticFieldLeak")
    public void rollDiceAnimation() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }

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

    public void createDiceLayout(int diceValue) {
        ImageView diceImage = new ImageView(dicePopUpView.getContext().getApplicationContext());
        diceImage.setLayoutParams(new LinearLayout.LayoutParams(diceSize, diceSize));
        diceImage.setPadding(3, 3, 3, 3);
        refreshDiceOutput(diceImage, diceValue);
        diceLayout.addView(diceImage);
    }

    public void refreshDiceOutput(ImageView diceImage, int diceValue) {
        diceImage.setImageResource(getDiceImage(diceValue));
    }

    public int getDiceImage(int result) {
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
