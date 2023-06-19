package com.example.se2_group4_project.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;










public class Item {
    private int id;
    private String name;
    private CardType cardType;
    private int number;
    private int count;
    private int stealCard;
    private String itemBenefit;
    private String cardFront;
    private boolean isFront;
    private int schnapspralinen;
    @JsonIgnore
    private boolean couchMatschig = false;
    @JsonIgnore
    private boolean badewanneDreckig = false;
    @JsonIgnore
    private boolean geschirrDreckig = false;
    @JsonIgnore
    private String penalty;


    public Item(){
        super();
    }
    public Item(int id,String name, CardType cardType, int number,int count , int stealCard, String itemBenefit, String cardFront,boolean isFront, int schnapspralinen){
        this.id = id;
        this.name = name;
        this.cardType = cardType;
        this.number = number;
        this.count = count;
        this.stealCard = stealCard;
        this.itemBenefit = itemBenefit;
        this.cardFront = cardFront;
        this.isFront = isFront;
        this.schnapspralinen = schnapspralinen;
    }

    public Item(JSONObject item) throws JSONException {
        this.id = item.getInt("id");
        this.name = item.getString("name");
        this.number = item.getInt("number");
        this.count = item.getInt("count");
        this.stealCard = item.getInt("stealCard");
        this.penalty = item.getString("itemBenefit");
        this.schnapspralinen = item.getInt("schnapspralinen");

        switch (item.getString("itemBenefit")){
            case "Couch matschig":
                this.penalty = "couch dreckig";
                break;
            case "Badewanne dreckig":
                this.penalty = "badewanne dreckig";
                break;
            case "Geschirr dreckig":
                this.penalty = "geschirr dreckig";
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
    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }
}
