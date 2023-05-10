package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class Badewanne {

    boolean kanguru = false;
    int number;
    int count;

    public Badewanne(JSONObject badewanne) throws JSONException {
        this.number = badewanne.getInt("number");
        this.count = badewanne.getInt("count");
    }

    public boolean checkBadewanne(){

        return isKanguru();
    }

    public boolean isAvailable(int[] dice){
        for(int i=0; i < dice.length; i++){
            if(i+1 == number && dice[i] >= count){
                return true;
            }
        }
        return false;
    }

    public boolean isKanguru() {
        return kanguru;
    }

    public void setKanguru(boolean kanguru) {
        this.kanguru = kanguru;
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
