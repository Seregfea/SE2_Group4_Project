package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class RoommateEasy {
    private int additionalDice = 0;
    private int number;
    private int count;
    private boolean awake = false;

    public RoommateEasy(JSONObject roommateEasy) throws JSONException {
        this.number = roommateEasy.getInt("number");
        this.count = roommateEasy.getInt("count");
    }

    public int getAdditionalDice() {
        return additionalDice;
    }

    public void setAdditionalDice(int additionalDice) {
        this.additionalDice = additionalDice;
    }

    public boolean isAwake() {
        return awake;
    }

    public void setAwake(boolean awake) {
        this.awake = awake;

        if (awake) {
            setAdditionalDice(1);
        } else {
            setAdditionalDice(0);
        }
    }

    public boolean isAvailable(int[] dice) {
        int counter = 0;

        for (int i = 0; i < dice.length; i++) {
            if (dice[i] == number) {
                counter++;
            }
            if (counter == count) {
                return true;
            }
        }
        return false;
        /*
        for (int i = 0; i < dice.length; i++) {
            if (dice[i] >= count && i+1 == number) {
                return true;
            }
        }
        return false;
         */
    }

}
