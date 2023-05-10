package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {
    private int id;
    private String name;
    private int number;
    private int count;
    private int stealCard;
    private String itemBenefit;
    private String cardFront;
    private boolean isFront;

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
    public boolean isAvailable(int[] rolledDice){
        for (int i = 0; i < rolledDice.length; i++){
            if (rolledDice[i] >= count && i+1 == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isStealable(int[] rolledDice){
        for(int i = 0; i < rolledDice.length; i++){
            if (rolledDice[i] >= stealCard && i+1 == number) {
                return true;
            }
        }
        return false;
    }


    // Getters/Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getItemBenefit() {
        return itemBenefit;
    }

    public void setItemBenefit(String itemBenefit) {
        this.itemBenefit = itemBenefit;
    }

    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }

    public boolean isFront() {
        return isFront;
    }

    public void setFront(boolean front) {
        isFront = front;
    }
}
