package com.example.se2_group4_project.cards;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WitzigWitzigToDos {

    private int schnapspralinen;
    private ArrayList<String> toDoPenalty;
    private boolean bathtub = false;
    private boolean couch = false;
    private boolean tableware = false;
    private boolean sleep = false;
    private boolean awake = false;
    private boolean tablewareAll = false;
    private ArrayList<NeededDice> neededDice;
    private boolean isAvailable = false;

    public WitzigWitzigToDos(JSONObject witzigWitzigCards) throws JSONException {

        this.schnapspralinen = witzigWitzigCards.getInt("schnapspralinen");

        this.toDoPenalty = new ArrayList<>();

        JSONArray jsonToDoPenalties = witzigWitzigCards.getJSONArray("toDoPenalty");

        for (int i = 0; i < jsonToDoPenalties.length(); i++) {
            String penalty = jsonToDoPenalties.getString(i);
            switch (penalty) {
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
                case "TABLEWAREALL":
                    this.tablewareAll = true;
                    break;
                default:
                    System.out.println("No to-do penalty assignments to do");
                    break;
            }
            this.toDoPenalty.add(penalty);
        }

        this.neededDice = new ArrayList<>();

        JSONArray neededDiceArray = witzigWitzigCards.getJSONArray("neededDice");

        for (int i = 0; i < neededDiceArray.length(); i++) {
        JSONObject jsonNeededDice = neededDiceArray.getJSONObject(i);
        NeededDice neededDiceObject = new NeededDice();

        if (jsonNeededDice.has("number")) {
            neededDiceObject.setNumber(jsonNeededDice.getString("number"));
        }

        if (jsonNeededDice.has("count")) {
            neededDiceObject.setCount(jsonNeededDice.getInt("count"));
        }

        if (jsonNeededDice.has("min_sum")) {
            neededDiceObject.setMin_sum(jsonNeededDice.getInt("min_sum"));
        }

        if (jsonNeededDice.has("following")) {
            neededDiceObject.setFollowing(jsonNeededDice.getInt("following"));
        }

        this.neededDice.add(neededDiceObject);
    }
}


    // methode to check if the card is available for the current rolledDices
    // runs though neededDice array of the card to check if rolledDice matches card conditions
    public boolean isAvailable(int[] rolledDice) {
        for (NeededDice neededDiceObject : neededDice) {
            if (neededDiceObject.getNumber() != null && neededDiceObject.getCount() != 0) {
                int number = Integer.parseInt(neededDiceObject.getNumber());
                if (countInArray(rolledDice, number) == neededDiceObject.getCount()) {
                    isAvailable = true;
                    return true;
                }
            } else if (neededDiceObject.getCount() != 0 && neededDiceObject.getNumber() == null) {
                for (int i = 1; i <= 6; i++) {
                    if (countInArray(rolledDice, i) == neededDiceObject.getCount()) {
                        isAvailable = true;
                        return true;
                    }
                }
            } else if (neededDiceObject.getMin_sum() != 0) {
                if (sumOfArray(rolledDice) == neededDiceObject.getMin_sum()) {
                    isAvailable = true;
                    return true;
                }
            } else if (neededDiceObject.getNumber() != null && neededDiceObject.getFollowing() != 0) {
                int number = Integer.parseInt(neededDiceObject.getNumber());
                if (checkFollowing(rolledDice, number, neededDiceObject.getFollowing())) {
                    isAvailable = true;
                    return true;
                }
            }
        }
        return false;
    }

    public int countInArray(int[] arr, int num) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                count++;
            }
        }
        return count;
    }

    public int sumOfArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public boolean checkFollowing(int[]arr, int number, int following) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                count++;
                if (count == following) {
                    return true;
                }
                number++; // check next number in sequence
            }
        }
        return false;
    }


    // Getters and Setters

    public ArrayList<NeededDice> getNeededDice() {
        return neededDice;
    }
    public ArrayList<String> getToDoPenalty() {
        return toDoPenalty;
    }
    public int getSchnapspralinen() {
        return schnapspralinen;
    }
    public boolean getBathtub() { return bathtub; }
    public boolean getCouch() { return couch; }
    public boolean getTableware() { return tableware; }
    public boolean getSleep() { return sleep; }
    public boolean getAwake() { return awake; }
    public boolean getIsAvailable() { return isAvailable; }

    public void setNeededDice(ArrayList<NeededDice> neededDice) {
        this.neededDice = neededDice;
    }

    public void setToDoPenalty(ArrayList<String> toDoPenalty) {
        this.toDoPenalty = toDoPenalty;
    }

    public void setSchnapspralinen(int schnapspralinen) {
        this.schnapspralinen = schnapspralinen;
    }
    public void setBathtub(boolean bathtub) { this.bathtub = bathtub;}
    public void setCouch(boolean couch) { this.couch = couch;}
    public void setTableware(boolean tableware) { this.tableware = tableware;}
    public void setSleep(boolean sleep) { this.sleep = sleep;}
    public void setAwake(boolean awake) { this.awake = awake;}
    public void setIsAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

}

