package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class Couch {

    private int diceSpace = 1;
    private boolean increaseDiceSpace = false;
    private int number;
    private int count;

    public Couch(JSONObject couch) throws JSONException {
        this.number = couch.getInt("number");
        this.count = couch.getInt("count");
    }

    public boolean isAvailable(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            if (i + 1 == number && dice[i] >= count) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiceSpace() {
        return increaseDiceSpace;
    }

    public int getDiceSpace() {
        return diceSpace;
    }

    public void setDiceSpace(int diceSpace) {
        this.diceSpace = diceSpace;
    }

    public boolean isIncreaseDiceSpace() {
        return increaseDiceSpace;
    }

    public void setIncreaseDiceSpace(boolean increaseDiceSpace) {
        this.increaseDiceSpace = increaseDiceSpace;

        if(increaseDiceSpace){
            setDiceSpace(4);
        }
        else{
            setDiceSpace(1);
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

