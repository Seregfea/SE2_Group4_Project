package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RoommateEasy {
    private int id;
    private String name;
    private CardType cardType;
    private String cardFront;
    private String cardBack;
    private boolean isFront;
    private int count;
    private int number;
    private int additionalDice = 1;


    private boolean awake = false;
    public RoommateEasy(){
        super();
    }

    public RoommateEasy(JSONObject roommateEasy) throws JSONException {
        this.id = roommateEasy.getInt("id");
        this.number = roommateEasy.getInt("number");
        this.count = roommateEasy.getInt("count");
    }

    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }
    public int getAdditionalDice() {
        return additionalDice;
    }

    public void setAdditionalDice(int additionalDice) {
        this.additionalDice = additionalDice;
    }

    public boolean isAwake() {
        return awake;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    // if put to sleep or awake, returns updated additional dice
    public int setAwake(boolean awake) {
        this.awake = awake;

        if (isAwake()) {
            setAdditionalDice(1);
        } else {
            setAdditionalDice(-1);
        }
        return getAdditionalDice();
    }

    public boolean isAvailable(ArrayList<Integer> rolledDice) {
        if (rolledDice.size() < 2) {
            return false;
        }
        int counter = 0;

        for (int i = 0; i < rolledDice.size(); i++) {
            if (rolledDice.get(i) == number) {
                counter++;
            }
            if (counter == count) {
                return true;
            }
        }
        return false;
    }

}
