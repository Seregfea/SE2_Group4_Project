package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Me {

    private int number;
    private int count;
    private int amountDiceMe = 4;
    private boolean diceSpaceMe = true;

    public Me(JSONObject couch) throws JSONException {
        this.number = couch.getInt("number");
        this.count = couch.getInt("count");
    }

    public boolean isAvailable(ArrayList<Integer> rolledDice){
        boolean isAvailable = false;
        int usedCount = count;

        for (int i = 0; i < rolledDice.size(); i++){
            if (rolledDice.get(i) == number) {
                usedCount--;
            }
        }
        if(usedCount <= 0){
            isAvailable = true;
        }
        return isAvailable;
    }

    public int getAmountDiceMe() {
        return amountDiceMe;
    }

    public void setAmountDiceMe(int amountDiceMe) {
        this.amountDiceMe = amountDiceMe;
    }

    public boolean isDiceSpaceMe() {
        return diceSpaceMe;

    }

    public void setDiceSpaceMe(boolean diceSpaceMe) {
        this.diceSpaceMe = diceSpaceMe;

        if(diceSpaceMe){
            setAmountDiceMe(4);
        }
        else{
            setAmountDiceMe(1);
        }
    }
}
