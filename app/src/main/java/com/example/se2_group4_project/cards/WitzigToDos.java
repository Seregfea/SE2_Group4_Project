package com.example.se2_group4_project.cards;

import android.util.ArraySet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WitzigToDos {

    private int id;
    private String name;
    private CardType cardType;
    private String number;
    private int count;
    private ArrayList<String> toDoPenalty;
    private int schnapspralinen;
    private String cardFront;
    private String cardBack;
    private boolean isFront;
    private boolean bathtub = false;
    private boolean couch = false;
    private boolean tableware = false;
    private boolean sleep = false;
    private boolean awake = false;

    private String number2;
    private int count2;
    private int min_sum;
    private int following;
    private ArrayList<String> filledFields;

    private String penalty;

    public WitzigToDos(){
        super();
    }

    public WitzigToDos(JSONObject witzigCard) throws JSONException {

        this.schnapspralinen = witzigCard.getInt("schnapspralinen");

        this.filledFields = new ArrayList<String>();

        if (witzigCard.has("number")) {
            this.number = witzigCard.getString("number");
            filledFields.add("number");
        }
        if (witzigCard.has("number2")) {
            this.number2 = witzigCard.getString("number2");
            filledFields.add("number2");
        }
        if (witzigCard.has("count")) {
            this.count = witzigCard.getInt("count");
            filledFields.add("count");
        }
        if (witzigCard.has("count2")) {
            this.count2 = witzigCard.getInt("count2");
            filledFields.add("count2");
        }
        if (witzigCard.has("min_sum")) {
            this.min_sum = witzigCard.getInt("min_sum");
            filledFields.add("min_sum");
        }
        if (witzigCard.has("following")) {
            this.following = witzigCard.getInt("following");
            filledFields.add("following");
        }


        /*JSONArray toDope = witzigCard.getJSONArray("toDoPenalty");
        try {
            Integer k = (Integer) toDope.get(0);

            for (int i = 0; i < toDope.length(); i++) {
                switch (toDope.getString(i)) {
                    case "BATHTUB":
                        this.bathtub = true;
                        this.penalty = "badewanne dreckig";
                        break;
                    case "COUCH":
                        this.couch = true;
                        this.penalty = "couch dreckig";
                        break;
                    case "TABLEWARE":
                        this.tableware = true;
                        this.penalty = "geschirr dreckig";
                        break;
                    case "SLEEP":
                        this.sleep = true;
                        this.penalty = "ich schlafe";
                        break;
                    case "AWAKE":
                        this.awake = true;
                        this.penalty = "alle schlafen";
                        break;
                    default:
                        System.out.println("No to-do penalty assignments to do");
                        break;
                }
            }

        } catch (JSONException e) {

        }*/
    }


    public boolean isAvailable(ArrayList<Integer> rolledDice) {

        if (filledFields.contains("number") && filledFields.contains("count")) {
            if (checkNumberCount(rolledDice)) {
                return true;
            }

        } else if (filledFields.contains("count")) {
            if (checkCount(rolledDice)) {
                return true;
            }
        }
        if (filledFields.contains("number") && filledFields.contains("following")) {
            if (checkFollowing(rolledDice)) {
                return true;
            }
        }
        if (filledFields.contains("number2") && filledFields.contains("count2")) {
            if (checkNumber2Count2(rolledDice)) {
                return true;
            }
        } else if (filledFields.contains("count2")) {
            if (checkCount2(rolledDice)) {
                return true;
            }
        }
        if (filledFields.contains("min_sum")) {
            if (checkMinSum(rolledDice)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNumberCount(ArrayList<Integer> rolledDice) {
        int num = Integer.parseInt(number);
        int numberCount = 0;
        for (int i = 0; i < rolledDice.size(); i++) {
            int diceValue = rolledDice.get(i);
            if (diceValue == num) {
                numberCount++;
                if (numberCount == count) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCount(ArrayList<Integer> rolledDice){
        int[] numberArray = new int[5];
        for(int i = 0; i<rolledDice.size(); i++) {
            int diceValue = rolledDice.get(i);
            if(diceValue >= 1 && diceValue <= 5){
                numberArray[diceValue-1]++;
            }
        }
        for (int i = 0; i < 5; i++) {
            if(numberArray[i] == count){
                return true;
            }
        }
        return false;
    }

    public boolean checkFollowing(ArrayList<Integer> rolledDice) {
        int intNumber = Integer.parseInt(number);
        for (int i = 0; i < rolledDice.size(); i++) {
            int diceValue = rolledDice.get(i);
            if (diceValue == intNumber) {
                int countOfFollowingNumbers = 1;
                for (int j = 0; j < rolledDice.size(); j++) {
                    if (rolledDice.get(j) == intNumber + countOfFollowingNumbers) {
                        countOfFollowingNumbers++;
                        if (countOfFollowingNumbers == following) {
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkNumber2Count2(ArrayList<Integer> rolledDice){
        int num2 = Integer.parseInt(number2);
        int number2Count = 0;
        for (int i = 0; i < rolledDice.size(); i++) {
            int diceValue = rolledDice.get(i);
            if (diceValue == num2) {
                number2Count++;
                if (number2Count == count2) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean checkCount2(ArrayList<Integer> rolledDice){
        int[] numberArray = new int[5];
        for(int i = 0; i<rolledDice.size(); i++) {
            int diceValue = rolledDice.get(i);
            if(diceValue >= 1 && diceValue <= 5){
                numberArray[diceValue-1]++;
            }
        }
        for (int i = 0; i < 5; i++) {
            if(numberArray[i] == count2){
                return true;
            }
        }
        return false;
    }

    public boolean checkMinSum(ArrayList<Integer>rolledDice) {
        int sum = 0;
        for (int i = 0; i < rolledDice.size(); i++) {
            int diceValue = rolledDice.get(i);
            sum = sum + diceValue;
        }
        if (sum >= min_sum) {
            return true;
        }
        return false;
    }




    // Getters and Setters
    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }

    public int getSchnapspralinen() { return schnapspralinen; }
    public ArrayList<String> getToDoPenalty() { return toDoPenalty; }
    public boolean getBathtub() { return bathtub; }
    public boolean getCouch() { return couch; }
    public boolean getTableware() { return tableware; }
    public boolean getSleep() { return sleep; }
    public boolean getAwake() { return awake; }
    public String getNumber() { return this.number; }
    public String getNumber2() { return number2; }
    public int getCount() { return count; }
    public int getCount2() { return count2; }
    public int getMin_sum() { return min_sum; }
    public int getFollowing() { return following; }
    public ArrayList<String> getFilledFields() {
        return filledFields;
    }


    public void setSchnapspralinen(int schnapspralinen) { this.schnapspralinen = schnapspralinen; }
    public void setToDoPenalty(ArrayList<String> toDoPenalty) { this.toDoPenalty = toDoPenalty; }
    public void setBathtub(boolean bathtub) { this.bathtub = bathtub;}
    public void setCouch(boolean couch) { this.couch = couch;}
    public void setTableware(boolean tableware) { this.tableware = tableware;}
    public void setSleep(boolean sleep) { this.sleep = sleep;}
    public void setAwake(boolean awake) { this.awake = awake;}
    public void setNumber(String number) { this.number = this.number; }
    public void setNumber2(String number2) { this.number2 = number2; }
    public void setCount(int count) { this.count = count; }
    public void setCount2(int count2) { this.count2 = count2; }
    public void setMin_sum(int min_sum) { this.min_sum = min_sum;}
    public void setFollowing(int following) { this.following = following; }
    public void setFilledFields(ArrayList<String> filledFields) {
        this.filledFields = filledFields;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }


}