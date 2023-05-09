package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class Couch {

    private int diceSpaceCocuh = 1;
    private boolean increaseDiceSpaceCouch = false;
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
        return increaseDiceSpaceCouch;
    }

    public int getDiceSpaceCocuh() {
        return diceSpaceCocuh;
    }

    public void setDiceSpaceCocuh(int diceSpaceCocuh) {
        this.diceSpaceCocuh = diceSpaceCocuh;
    }

    public boolean isIncreaseDiceSpaceCouch() {
        return increaseDiceSpaceCouch;
    }

    public void setIncreaseDiceSpaceCouch(boolean increaseDiceSpaceCouch) {
        this.increaseDiceSpaceCouch = increaseDiceSpaceCouch;

        if(increaseDiceSpaceCouch){
            setDiceSpaceCocuh(4);
        }
        else{
            setDiceSpaceCocuh(1);
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

