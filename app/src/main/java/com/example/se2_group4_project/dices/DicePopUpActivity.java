package com.example.se2_group4_project.dices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button submitClickedDices;
    private final int diceSize = 80;
    private int numberOfDice = 0;
    private int delayTime = 20;
    private int rollAnimation = 40;

    private List<Integer> playerDice;
    private List<Integer> enemyDice;
    private List<Integer> selected;
    private List<Integer> unselected = new ArrayList<>();
    private DiceCallbacks diceCallbacks;

    public void setDiceCallbacks(DiceCallbacks callbacks) {
        this.diceCallbacks = callbacks;
    }

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
        mediaPlayer = MediaPlayer.create(context, R.raw.roll);
        diceLayout = dicePopUpView.findViewById(R.id.diceLayout);
        submitClickedDices = dicePopUpView.findViewById(R.id.btnSubmitClickedDices);

        submitClickedDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                testDice();
                diceResults(selected, unselected);
            }
        });
    }

    public void rollDice() throws InterruptedException {
        // hardcoded
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

        Thread thread = new Thread() {
            public void run() {
                List<Integer> diceValues = new ArrayList<>();

                for (int j = 0; j < rollAnimation; j++) {
                    diceValues = Dice.rollDice(numberOfDice);
                    unselected = diceValues;
                    selected = new ArrayList<>();

                    for (int i = 0; i < diceValues.size(); i++) {
                        final ImageView diceImage = (ImageView) diceLayout.getChildAt(i);
                        final int diceValue = diceValues.get(i);
                        diceImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                submitClickedDices.setEnabled(true);

                                // überprüfen ob imageView ausgewählt, falls nicht wird es auf ausgewählt gesetzt
                                if (diceImage.getBackground() instanceof ColorDrawable) {
                                    ColorDrawable drawable = (ColorDrawable) diceImage.getBackground();

                                    // bereits ausgewählt -> wieder abwählen und aus selected entfernen
                                    // falls nicht wird es ausgewählt und der Wert wird aus unselected entfernt
                                    if (drawable.getColor() == Color.GREEN) {
                                        diceImage.setBackgroundColor(Color.TRANSPARENT);

                                        if (selected.size() != 0) {
                                            selected.remove(Integer.valueOf(diceValue));
                                        }
                                        unselected.add(diceValue);
                                    } else {
                                        diceImage.setBackgroundColor(Color.GREEN);

                                        if (unselected.size() != 0) {
                                            unselected.remove(Integer.valueOf(diceValue));
                                        }
                                        selected.add(diceValue);
                                    }

                                } else {
                                    diceImage.setBackgroundColor(Color.GREEN);

                                    if (unselected.size() != 0) {
                                        unselected.remove(Integer.valueOf(diceValue));
                                    }
                                    selected.add(diceValue);
                                }
                            }
                        });
                        refreshDiceOutput(diceImage, diceValues.get(i));
                    }
                    try {
                        Thread.sleep(delayTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                Log.d("thread dice", diceValues.toString());
                Log.d("unselected dices", unselected.toString());
                diceResults(selected, unselected);
            }
        };

        thread.start();
        // wartet bis zum Ende des Threads
        thread.join();
        testDice();
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

        if (diceCallbacks != null) {
            diceCallbacks.diceResults(playerDice, enemyDice);
        }
    }

    public void testDice() {
        Log.d("dice callback - playerDice", this.playerDice.toString());
        Log.d("dice callback - enemyDice", this.enemyDice.toString());
    }

}
