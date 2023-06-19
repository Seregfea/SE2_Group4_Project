package com.example.se2_group4_project.cards;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WitzigWitzigToDos {

    private int schnapspralinen;
    //private ArrayList<String> toDoPenalty;
    private boolean bathtub = false;
    private boolean couch = false;
    private boolean tableware = false;
    private boolean sleep = false;
    private boolean awake = false;
    private boolean tablewareAll = false;
    private String number;
    private String number2;
    private String number3;
    private String number4;
    private int count;
    private int count2;
    private int count3;
    private int count4;
    private Set<String> filledFields;

    private String toDoPenalty;

    public WitzigWitzigToDos(JSONObject witzigWitzigCards) throws JSONException {

        this.schnapspralinen = witzigWitzigCards.getInt("schnapspralinen");

        this.filledFields = new HashSet<String>();

        if (witzigWitzigCards.has("number")) {
            this.number = witzigWitzigCards.getString("number");
            this.filledFields.add("number");
        }
        if (witzigWitzigCards.has("number2")) {
            this.number2 = witzigWitzigCards.getString("number2");
            this.filledFields.add("number2");
        }
        if (witzigWitzigCards.has("number3")) {
            this.number3 = witzigWitzigCards.getString("number3");
            this.filledFields.add("number3");
        }
        if (witzigWitzigCards.has("number4")) {
            this.number4 = witzigWitzigCards.getString("number4");
            this.filledFields.add("number4");
        }
        if (witzigWitzigCards.has("count")) {
            this.count = witzigWitzigCards.getInt("count");
            this.filledFields.add("count");
        }
        if (witzigWitzigCards.has("count2")) {
            this.count2 = witzigWitzigCards.getInt("count2");
            this.filledFields.add("count2");
        }
        if (witzigWitzigCards.has("count3")) {
            this.count3 = witzigWitzigCards.getInt("count3");
            this.filledFields.add("count3");
        }
        if (witzigWitzigCards.has("count4")) {
            this.count4 = witzigWitzigCards.getInt("count4");
            this.filledFields.add("count4");
        }

        try {

                switch ("toDoPenalty") {
                    case "BATHTUB":
                        this.bathtub = true;
                        this.toDoPenalty = "badewanne dreckig";
                        break;
                    case "COUCH":
                        this.couch = true;
                        this.toDoPenalty = "couch dreckig";
                        break;
                    case "TABLEWARE":
                        this.tableware = true;
                        this.toDoPenalty = "geschirr dreckig";
                        break;
                    case "SLEEP":
                        this.sleep = true;
                        this.toDoPenalty = "ich schlafe";
                        break;
                    case "AWAKE":
                        this.awake = true;
                        this.toDoPenalty = "alle schlafen";
                        break;
                    default:
                        System.out.println("No to-do penalty assignments to do");
                        break;
                }
            //}

        } catch (Exception e) {

        }
    }

    public boolean isAvailable(ArrayList<Integer> rolledDice) {
        ArrayList<Integer> diceCopy = new ArrayList(rolledDice);

        if (filledFields.contains("number") && filledFields.contains("count")) {
            if (checkNumberCount(rolledDice)) {
                removeNumber(diceCopy, number, count);
            }
            return checkNumberCount(rolledDice);

        } else if (filledFields.contains("count")) {
            return checkCount(rolledDice);
        }
        if (filledFields.contains("number2") && filledFields.contains("count2")) {
            if (checkNumber2Count2(diceCopy)) {
                removeNumber(diceCopy, number2, count2);
            }
            return checkNumber2Count2(diceCopy);
        } else if (filledFields.contains("count2")) {
            return checkCount2(diceCopy);
        }
        if (filledFields.contains("number3") && filledFields.contains("count3")) {
            if (checkNumber3Count3(diceCopy)) {
                removeNumber(diceCopy, number3, count3);
            }
            return checkNumber3Count3(diceCopy);
        } else if (filledFields.contains("count3")) {
            return checkCount3(diceCopy);
        }
        if (filledFields.contains("number4") && filledFields.contains("count4")) {
            if (checkNumber4Count4(diceCopy)) {
                removeNumber(diceCopy, number4, count4);
            }
            return checkNumber4Count4(diceCopy);

        } else if (filledFields.contains("count4")) {
            return checkCount4(diceCopy);
        } else {
            return false;
        }
    }

    public boolean checkNumberCount(ArrayList<Integer> rolledDice) {
        int num = Integer.parseInt(number);
        int numberCount = 0;
        for (int i = 0; i < rolledDice.size(); i++) {
            int diceValue = rolledDice.get(i);
            if (diceValue == num) {
                numberCount++;
            }
        }
        return numberCount >= count;
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
    public boolean checkNumber2Count2(ArrayList<Integer> rolledDice){
        int num2 = Integer.parseInt(number2);
        int number2Count = 0;
        for (int i = 0; i < rolledDice.size(); i++) {
            int diceValue = rolledDice.get(i);
            if (diceValue == num2) {
                number2Count++;
            }
        }
        return number2Count >= count2;
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
    public boolean checkNumber3Count3(ArrayList<Integer> rolledDice){
        int num3 = Integer.parseInt(number3);
        int number3Count = 0;
        for (int i = 0; i < rolledDice.size(); i++) {
            int diceValue = rolledDice.get(i);
            if (diceValue == num3) {
                number3Count++;
            }
        }
        return number3Count >= count3;
    }
    public boolean checkCount3(ArrayList<Integer> rolledDice) {
            int[] numberArray = new int[rolledDice.size()];
            for (int i = 0; i < rolledDice.size(); i++) {
                int diceValue = rolledDice.get(i);
                if (diceValue >= 1 && diceValue <= 5) {
                    numberArray[diceValue - 1]++;
                }
            }
            for (int i = 0; i < 5; i++) {
                if (numberArray[i] == count3) {
                    return true;
                }
            }
            return false;
        }
        public boolean checkNumber4Count4(ArrayList<Integer> rolledDice){
            int num4 = Integer.parseInt(number4);
            int number4Count = 0;
            for (int i = 0; i < rolledDice.size(); i++) {
                int diceValue = rolledDice.get(i);
                if (diceValue == num4) {
                    number4Count++;
                }
            }
            return number4Count >= count4;
        }
        public boolean checkCount4(ArrayList<Integer> rolledDice){
            int[] numberArray = new int[rolledDice.size()];
            for(int i = 0; i<rolledDice.size(); i++) {
                int diceValue = rolledDice.get(i);
                if(diceValue >= 1 && diceValue <= 5){
                    numberArray[diceValue-1]++;
                }
            }
            for (int i = 0; i < 5; i++) {
                if(numberArray[i] == count4){
                    return true;
                }
            }
            return false;
        }

    public void removeNumber(ArrayList<Integer>diceArray, String number, int count) {
        int removedCount = 0;
        for (int i = 0; i < diceArray.size(); i++) {
            if (diceArray.get(i) == Integer.parseInt(number)) {
                diceArray.remove(i);
                removedCount++;
                i--; // Decrease the index since the array size has changed
                if (removedCount == count) {
                    break;
                }
            }
        }
    }



    // Getters and Setters

    public int getSchnapspralinen() { return schnapspralinen; }
    public String getToDoPenalty() { return toDoPenalty; }
    public boolean getBathtub() { return bathtub; }
    public boolean getCouch() { return couch; }
    public boolean getTableware() { return tableware; }
    public boolean getTablewareAll() { return tablewareAll; }
    public boolean getSleep() { return sleep; }
    public boolean getAwake() { return awake; }
    public String getNumber() { return this.number; }
    public String getNumber2() { return number2; }
    public String getNumber3() { return number3; }
    public String getNumber4() { return number4; }
    public int getCount() { return count; }
    public int getCount2() { return count2; }
    public int getCount3() { return count3; }
    public int getCount4() { return count4; }



    public void setSchnapspralinen(int schnapspralinen) { this.schnapspralinen = schnapspralinen; }
    public void setToDoPenalty(String toDoPenalty) { this.toDoPenalty = toDoPenalty; }
    public void setBathtub(boolean bathtub) { this.bathtub = bathtub;}
    public void setCouch(boolean couch) { this.couch = couch;}
    public void setTableware(boolean tableware) { this.tableware = tableware;}
    public void setTablewareAll(boolean tablewareAll) { this.tablewareAll = tablewareAll;}
    public void setSleep(boolean sleep) { this.sleep = sleep;}
    public void setAwake(boolean awake) { this.awake = awake;}
    public void setNumber(String number) { this.number = this.number; }
    public void setNumber2(String number2) { this.number2 = number2; }
    public void setNumber3(String number3) { this.number3 = number3; }
    public void setNumber4(String number4) { this.number4 = number4;}
    public void setCount(int count) { this.count = count; }
    public void setCount2(int count2) { this.count2 = count2; }
    public void setCount3(int count3) { this.count3 = count3; }
    public void setCount4(int count4) { this.count4 = count4; }

    public String getPenalty() {
        return toDoPenalty;
    }

    public void setPenalty(String penalty) {
        this.toDoPenalty = penalty;
    }
}
