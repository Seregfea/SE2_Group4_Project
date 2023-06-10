package com.example.se2_group4_project.dices;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.se2_group4_project.R;

import java.util.ArrayList;

public class DicePopUpActivity extends PopupWindow {
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

    public void createDiceLayout(int diceValue) {

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

    }

}
