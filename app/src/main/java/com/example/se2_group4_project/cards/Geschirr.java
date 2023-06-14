package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Geschirr {

    private int countRoll = 1;
    private boolean rollAgain = false;
    int number;
    int count;

    public Geschirr(JSONObject geschirr) throws JSONException {
        this.number = geschirr.getInt("number");
        this.count = geschirr.getInt("count");
    }

    public boolean isAvailable(ArrayList<Integer> rolledDice) {
        if (rolledDice.size() < 3) {
            return false; // Cannot form a consecutive row with less than 3 numbers
        }

        // Collections.sort(rolledDice); // Sort the array in ascending order

        for (int i = 2; i < rolledDice.size(); i++) {
            int current = rolledDice.get(i);
            int prev1 = rolledDice.get(i - 1);
            int prev2 = rolledDice.get(i - 2);

            if (current == prev1 + 1 && current == prev2 + 2) {
                return true; // Found a consecutive row
            }
        }

        return false; // No consecutive row found
    }



    public boolean checkRollAgain(){
        return rollAgain;
    }

    public int getCountRoll() {
        return countRoll;
    }

    public void setCountRoll(int countRoll) {
        this.countRoll = countRoll;
    }

    public boolean isRollAgain() {
        return rollAgain;
    }

    public void setRollAgain(boolean rollAgain) {
        this.rollAgain = rollAgain;

        if(rollAgain){
            setCountRoll(2);
        }
        else{
            setCountRoll(1);
        }
    }
}
