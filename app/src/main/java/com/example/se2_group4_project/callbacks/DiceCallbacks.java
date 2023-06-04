package com.example.se2_group4_project.callbacks;


import java.util.ArrayList;

public interface DiceCallbacks {
    void diceResults(ArrayList<Integer> playerDice, ArrayList<Integer> enemyDice);
    void diceValues(ArrayList<Integer> playerDices, ArrayList<Integer> enemyDices);

}
