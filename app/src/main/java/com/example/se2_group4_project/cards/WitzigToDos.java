package com.example.se2_group4_project.cards;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WitzigToDos {

    private int schnapspralinen;
    private ArrayList<String> toDoPenalty;
    private boolean bathtub = false;
    private boolean couch = false;
    private boolean tableware = false;
    private boolean sleep = false;
    private boolean awake = false;
    private String number;
    private String number2;
    private int count;
    private int count2;
    private int min_sum;
    private int following;
    private Set<String> filledFields;


    public WitzigToDos(JSONObject witzigCard) throws JSONException {

        this.schnapspralinen = witzigCard.getInt("schnapspralinen");

        this.filledFields = new HashSet<String>();

        if (witzigCard.has("number")) {
            this.number = witzigCard.getString("number");
            this.filledFields.add("number");
        }
        if (witzigCard.has("number2")) {
            this.number2 = witzigCard.getString("number2");
            this.filledFields.add("number2");
        }
        if (witzigCard.has("count")) {
            this.count = witzigCard.getInt("count");
            this.filledFields.add("count");
        }
        if (witzigCard.has("count2")) {
            this.count2 = witzigCard.getInt("count2");
            this.filledFields.add("count2");
        }
        if (witzigCard.has("min_sum")) {
            this.min_sum = witzigCard.getInt("min_sum");
            this.filledFields.add("min_sum");
        }
        if (witzigCard.has("following")) {
            this.following = witzigCard.getInt("following");
            this.filledFields.add("following");
        }


        JSONArray toDoPenaltyArray = witzigCard.getJSONArray("toDoPenalty");

        for (int i = 0; i < toDoPenaltyArray.length(); i++) {
            switch (toDoPenaltyArray.getString(i)) {
                case "BATHTUB":
                    this.bathtub = true;
                    break;
                case "COUCH":
                    this.couch = true;
                    break;
                case "TABLEWARE":
                    this.tableware = true;
                    break;
                case "SLEEP":
                    this.sleep = true;
                    break;
                case "AWAKE":
                    this.awake = true;
                    break;
                default:
                    System.out.println("No to-do penalty assignments to do");
                    break;
            }
        }
    }


    public boolean isAvailable(ArrayList<Integer> rolledDice) {
        boolean checkBoolean = false;
        Set<Integer> usedIndices = new HashSet<Integer>();

        if (filledFields.contains("number") && filledFields.contains("count")) {
            int intNumber = Integer.parseInt(number);
            if (rolledDice.get(intNumber - 1) >= count) {
                checkBoolean = true;
                usedIndices.add(intNumber - 1);
            }
        } else if (filledFields.contains("count")) {
            for (int i = 0; i < rolledDice.size(); i++) {
                if (rolledDice.get(i) >= count && !usedIndices.contains(i)) {
                    checkBoolean = true;
                    usedIndices.add(i);
                    break;
                }
            }
        }
        if (filledFields.contains("number") && filledFields.contains("following")) {
            int intNumber = Integer.parseInt(number);
            int count = 0;
            for (int i = intNumber; i < intNumber + following; i++) {
                if (rolledDice.get(i - 1) > 0 && !usedIndices.contains(i - 1)) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == following) {
                checkBoolean = true;
            }
        }
        if (filledFields.contains("number2") && filledFields.contains("count2")) {
            int intNumber2 = Integer.parseInt(number2);
            if (rolledDice.get(intNumber2 - 1) >= count2 && !usedIndices.contains(intNumber2 - 1)) {
                checkBoolean = true;
                usedIndices.add(intNumber2 - 1);
            }
        } else if (filledFields.contains("count2")) {
            for (int i = 0; i < rolledDice.size(); i++) {
                if (rolledDice.get(i) >= count2 && !usedIndices.contains(i)) {
                    checkBoolean = true;
                    usedIndices.add(i);
                    break;
                }
            }
        }
        if (filledFields.contains("min_sum")) {
            int sum = 0;
            for (int i = 0; i < rolledDice.size(); i++) {
                sum += (i + 1) * rolledDice.get(i);
            }
            if (sum >= min_sum) {
                checkBoolean = true;
            }
        }
        return checkBoolean;
    }



    // Getters and Setters

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


}