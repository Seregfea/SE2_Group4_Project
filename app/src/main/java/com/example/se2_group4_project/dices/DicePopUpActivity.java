package com.example.se2_group4_project.dices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.se2_group4_project.R;
import com.example.se2_group4_project.callbacks.DiceCallbacks;

import java.util.ArrayList;
import java.util.List;

public class DicePopUpActivity extends PopupWindow implements DiceCallbacks {

    private Handler handler;
    private View dicePopUpView;
    private MediaPlayer mediaPlayer;
    private LinearLayout diceLayout;
    private final int diceSize = 80;
    private int numberOfDice = 0;
    private int delayTime = 20;
    private int rollAnimation = 40;

    private List<Integer> playerDice;
    private List<Integer> enemyDice;

    public List<Integer> getPlayerDice() {
        return playerDice;
    }

    public void setPlayerDice(List<Integer> playerDice) {
        this.playerDice = playerDice;
    }

    public List<Integer> getEnemyDice() {
        return enemyDice;
    }

    public void setEnemyDice(List<Integer> enemyDice) {
        this.enemyDice = enemyDice;
    }

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

        handler = new Handler(context.getMainLooper());
        diceLayout = dicePopUpView.findViewById(R.id.diceLayout);
        mediaPlayer = MediaPlayer.create(context, R.raw.roll);
    }

    public void rollDice() throws InterruptedException {
        //hardcoded
        numberOfDice = 4;
        diceLayout.removeAllViews();

        for (int i = 0; i < numberOfDice; i++) {
            createDiceLayout(3);
        }
        rollDiceAnimation();
    }

    @SuppressLint("StaticFieldLeak")
    public void rollDiceAnimation() throws InterruptedException {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
        Thread thread = new Thread(){
            public void run(){
                List<Integer> diceValues = new ArrayList<>();

                for (int j = 0; j < rollAnimation; j++) {
                    diceValues = Dice.rollDice(numberOfDice);
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
                Log.d("thread dice", diceValues.toString());
                diceResults(diceValues, diceValues);
            }
        };
        thread.start();
        //handler.post(thread);
        Thread.sleep(5000);
        testDice();
/*
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

 */
    }

    public void testDice() {
        Log.d("dice callback", this.playerDice.toString());
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

    @Override
    public void diceResults(List<Integer> playerDice, List<Integer> enemyDice) {
        this.playerDice = playerDice;
        this.enemyDice = enemyDice;
    }
}
