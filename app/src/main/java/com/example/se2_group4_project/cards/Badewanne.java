package com.example.se2_group4_project.cards;

import androidx.appcompat.app.AppCompatActivity;

import com.example.se2_group4_project.Gameboard;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
