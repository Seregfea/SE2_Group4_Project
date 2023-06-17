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

    private int schnapspralinen;


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

    public boolean isAvailable(ArrayList<Integer> rolledDice){
        boolean isAvailable = false;
        int usedCount = count;

        if(rolledDice.size()<2){
            return false;
        }
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

    public boolean isStealable(ArrayList<Integer> rolledDice){
        int usedCount = stealCard;
        if(rolledDice.size()<3){
            return false;
        }

        for (int i = 0; i < rolledDice.size(); i++){
            if (rolledDice.get(i) == number) {
                usedCount--;
            }
        }
        return usedCount <= 0;
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

    public boolean isCouchMatschig() {
        return couchMatschig;
    }

    public void setCouchMatschig(boolean couchMatschig) {
        this.couchMatschig = couchMatschig;
    }

    public boolean isBadewanneDreckig() {
        return badewanneDreckig;
    }

    public void setBadewanneDreckig(boolean badewanneDreckig) {
        this.badewanneDreckig = badewanneDreckig;
    }

    public boolean isGeschirrDreckig() {
        return geschirrDreckig;
    }

    public void setGeschirrDreckig(boolean geschirrDreckig) {
        this.geschirrDreckig = geschirrDreckig;
    }

    public int getSchnapspralinen() {
        return schnapspralinen;
    }

    public void setSchnapspralinen(int schnapspralinen) {
        this.schnapspralinen = schnapspralinen;
    }
}
