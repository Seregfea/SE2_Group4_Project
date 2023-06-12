package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Item {
    private int number;
    private int count;
    private int stealCard;
    private boolean couchMatschig = false;
    private boolean badewanneDreckig = false;
    private boolean geschirrDreckig = false;


    public Item(JSONObject item) throws JSONException {
        this.number = item.getInt("number");
        this.count = item.getInt("count");
        this.stealCard = item.getInt("stealCard");

        switch (item.getString("itemBenefit")){
            case "Couch matschig":
                this.couchMatschig = true;
                break;
            case "Badewanne dreckig":
                this.badewanneDreckig = true;
                break;
            case "Geschirr dreckig":
                this.geschirrDreckig = true;
                break;
            default:
                System.out.println("Keine item Benefits");
        }
    }

    // Methods
    public boolean isAvailable(ArrayList<Integer> rolledDice){
        for (int i = 0; i < rolledDice.size(); i++){
            if (rolledDice.get(i) >= count && i+1 == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isStealable(ArrayList<Integer> rolledDice){
        for(int i = 0; i < rolledDice.size(); i++){
            if (rolledDice.get(i) >= stealCard && i+1 == number) {
                return true;
            }
        }
        return false;
    }


    // Getters/Setters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStealCard(){
        return stealCard;
    }

    public void setStealCard(int stealCard){
        this.stealCard = stealCard;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
