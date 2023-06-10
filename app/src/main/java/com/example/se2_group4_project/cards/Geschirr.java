package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class Geschirr {

   private int countRoll = 1;
  private boolean rollAgain = false;
    int number;
    int count;
    public Geschirr(JSONObject geschirr) throws JSONException {
        this.number = geschirr.getInt("number");
        this.count = geschirr.getInt("count");
    }


    public boolean isAvailable(int[] dice){
        for(int i=0; i < 3; i++){
         if(dice[i] != 0 && dice[i+1] != 0 && dice[i+2] != 0){
             return true;
         }
        }
        return false;
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
