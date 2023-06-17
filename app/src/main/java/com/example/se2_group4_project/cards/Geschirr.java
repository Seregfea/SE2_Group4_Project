package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Geschirr {

    private int countRoll = 1;
    private boolean rollAgain = false;

    private int following;

    public Geschirr(JSONObject geschirr) throws JSONException {
        this.following = geschirr.getInt("following");
    }

    public boolean isAvailable(ArrayList<Integer> rolledDice) {
        if (rolledDice.size() < following) {
            return false;
        }

        for (int i = following-1; i < rolledDice.size(); i++) {
            int current = rolledDice.get(i);
            int prev1 = rolledDice.get(i - 1);
            int prev2 = rolledDice.get(i - 2);

            if (current < 1 || prev1 < 1 || prev2 < 1){
                return false;
            }
            if (current > 5 || prev1 > 5 || prev2 > 5){
                return false;
            }
            if (current == prev1 + 1 && current == prev2 + 2) {
                return true;
            }
        }

        return false;
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

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }
}
