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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.se2_group4_project.R;
import com.example.se2_group4_project.callbacks.GameboardCallbacks;

import java.util.ArrayList;

public class DicePopUpActivity extends PopupWindow {
    private final Handler handler;
    private final View dicePopUpView;
    private final MediaPlayer mediaPlayer;
    private final LinearLayout diceLayout;
    private final Button submitClickedDices;
    private final EditText changeDiceValue;
    private final GameboardCallbacks gbCallbacks;
    private int numberOfDice = 0;
    private final int delayTime = 20;
    private final int rollAnimation = 40;
    private ArrayList<Integer> playerDices;
    private ArrayList<Integer> enemyDices;
    private ArrayList<Integer> selectedDices;
    private ArrayList<Integer> unselectedDices = new ArrayList<>();

    public ArrayList<Integer> getPlayerDices() {
        return playerDices;
    }

    public void setPlayerDices(ArrayList<Integer> playerDices) {
        this.playerDices = playerDices;
    }

    public ArrayList<Integer> getEnemyDices() {
        return enemyDices;
    }

    public void setEnemyDices(ArrayList<Integer> enemyDices) {
        this.enemyDices = enemyDices;
    }

    @SuppressLint("InflateParams")
    public DicePopUpActivity(Context context, GameboardCallbacks gameboardCallbacks, Handler handler) {
        super(context);
        dicePopUpView = LayoutInflater.from(context).inflate(R.layout.activity_dice, null);

        this.gbCallbacks = gameboardCallbacks;
        this.handler = handler;

        setContentView(dicePopUpView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mediaPlayer = MediaPlayer.create(context, R.raw.roll);
        diceLayout = dicePopUpView.findViewById(R.id.diceLayout);
        submitClickedDices = dicePopUpView.findViewById(R.id.btnSubmitClickedDices);
        changeDiceValue = dicePopUpView.findViewById(R.id.et_changeDiceValue);

        submitClickedDices.setOnClickListener(view -> {
            int diceValueNew = 0;
            boolean isDiceValueChanged = false;

            if (changeDiceValue.getText().toString().length() != 0) {
                diceValueNew = Integer.parseInt(changeDiceValue.getText().toString());
                isDiceValueChanged = true;
            }

            // falls kein Würfel verändert wird direktes Schließen des pop ups
            if (!isDiceValueChanged) {
                dismiss();
                testDice();
                diceResults(selectedDices, unselectedDices);
            } else {
                if (diceValueNew < 1 || diceValueNew > 5) {
                    Log.d("invalid new dice value", "" + diceValueNew);
                    Toast.makeText(context, "Der neue Würfelwert muss zwischen 1 und 5 liegen!", Toast.LENGTH_LONG).show();
                    changeDiceValue.setText("");
                } else {
                    Log.d("valid new dice value", "" + diceValueNew);

                    // Känguru aus selected dices entfernen und den neuen Wert adden
                    selectedDices.remove(Integer.valueOf(6));
                    selectedDices.add(diceValueNew);
                    Log.d("selected dices", selectedDices.toString());

                    dismiss();
                    testDice();
                    diceResults(selectedDices, unselectedDices);
                    changeDiceValue.setText("");
                    changeDiceValue.setEnabled(false);
                }
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

        Thread thread = new Thread(() -> {
            ArrayList<Integer> diceValues = new ArrayList<>();

            for (int j = 0; j < rollAnimation; j++) {
                diceValues = Dice.rollDice(numberOfDice);
                unselectedDices = diceValues;
                selectedDices = new ArrayList<>();

                for (int i = 0; i < diceValues.size(); i++) {
                    final ImageView diceImage = (ImageView) diceLayout.getChildAt(i);
                    final int diceValue = diceValues.get(i);

                    diceImage.setOnClickListener(view -> {
                        submitClickedDices.setEnabled(true);

                        // überprüfen ob imageView ausgewählt, falls nicht wird es auf ausgewählt gesetzt
                        if (diceImage.getBackground() instanceof ColorDrawable) {
                            ColorDrawable drawable = (ColorDrawable) diceImage.getBackground();

                            // bereits ausgewählt -> wieder abwählen und aus selected entfernen
                            // falls nicht wird es ausgewählt und der Wert wird aus unselected entfernt
                            if (drawable.getColor() == Color.GREEN) {
                                diceImage.setBackgroundColor(Color.TRANSPARENT);

                                if (selectedDices.size() != 0) {
                                    selectedDices.remove(Integer.valueOf(diceValue));
                                }
                                unselectedDices.add(diceValue);
                            } else {
                                diceImage.setBackgroundColor(Color.GREEN);

                                if (unselectedDices.size() != 0) {
                                    unselectedDices.remove(Integer.valueOf(diceValue));
                                }
                                selectedDices.add(diceValue);
                            }

                        } else {
                            diceImage.setBackgroundColor(Color.GREEN);

                            if (unselectedDices.size() != 0) {
                                unselectedDices.remove(Integer.valueOf(diceValue));
                            }
                            selectedDices.add(diceValue);
                        }
                        // überprüfen ob ein Känguru ausgewählt ist und das Textfeld entsprechend bearbeitbar machen
                        if (diceValue == 6 && selectedDices.contains(diceValue)) {
                            changeDiceValue.setEnabled(true);
                            Log.d("change dice value called", "enabled");
                        }
                        else if (diceValue == 6 && !selectedDices.contains(diceValue)) {
                            changeDiceValue.setEnabled(false);
                            Log.d("change dice value called", "disabled");
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
            Log.d("unselected dices", unselectedDices.toString());
            diceResults(selectedDices, unselectedDices);
        });

        thread.start();
        // wartet bis zum Ende des Threads
        thread.join();
        testDice();
    }

    public void createDiceLayout(int diceValue) {
        ImageView diceImage = new ImageView(dicePopUpView.getContext().getApplicationContext());
        int diceSize = 80;
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

    private void finishDiceRolling() {
        handler.post(() -> gbCallbacks.diceValues(playerDices, enemyDices));
    }

    public void diceResults(ArrayList<Integer> playerDices, ArrayList<Integer> enemyDices) {
        this.playerDices = playerDices;
        this.enemyDices = enemyDices;

        if (gbCallbacks != null) {
            gbCallbacks.diceResults(playerDices, enemyDices);
        }
    }

    public void testDice() {
        Log.d("dice callback - playerDice", this.playerDices.toString());
        Log.d("dice callback - enemyDice", this.enemyDices.toString());
    }

}
