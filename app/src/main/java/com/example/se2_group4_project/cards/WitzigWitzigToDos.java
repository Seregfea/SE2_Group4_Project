package com.example.se2_group4_project.cards;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;




public class WitzigWitzigToDos {
    private int id;
    private String name;
    private CardType cardType;
    private String cardFront;
    private String cardBack;
    private boolean isFront;

    private int schnapspralinen;
    private ArrayList<String> toDoPenalty;
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

    private String penalty;

    public WitzigWitzigToDos(){
        super();
    }
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


        JSONArray toDoPenalty = witzigWitzigCards.getJSONArray("toDoPenalty");

        try {
            Integer k = (Integer) toDoPenalty.get(0);

            for (int i = 0; i < toDoPenalty.length(); i++) {
                switch (toDoPenalty.getString(i)) {
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
        if(filledFields.contains("number2") && filledFields.contains("count2")) {
            int intNumber2 = Integer.parseInt(number2);
            if(rolledDice.get(intNumber2 - 1) >= count2 && !usedIndices.contains(intNumber2-1)) {
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
        if(filledFields.contains("number3") && filledFields.contains("count3")) {
            int intNumber3 = Integer.parseInt(number3);
            if(rolledDice.get(intNumber3 - 1) >= count3 && !usedIndices.contains(intNumber3-1)) {
                checkBoolean = true;
                usedIndices.add(intNumber3 - 1);
            }
        } else if (filledFields.contains("count3")) {
            for (int i = 0; i < rolledDice.size(); i++) {
                if (rolledDice.get(i) >= count3 && !usedIndices.contains(i)) {
                    checkBoolean = true;
                    usedIndices.add(i);
                    break;
                }
            }
        }
        if(filledFields.contains("number4") && filledFields.contains("count4")) {
            int intNumber4 = Integer.parseInt(number4);
            if(rolledDice.get(intNumber4 - 1) >= count4 && !usedIndices.contains(intNumber4-1)) {
                checkBoolean = true;
                usedIndices.add(intNumber4 - 1);
            }
        } else if (filledFields.contains("count4")) {
            for (int i = 0; i < rolledDice.size(); i++) {
                if (rolledDice.get(i) >= count4 && !usedIndices.contains(i)) {
                    checkBoolean = true;
                    usedIndices.add(i);
                    break;
                }
            }
        }
        return checkBoolean;
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
    public void setToDoPenalty(ArrayList<String> toDoPenalty) { this.toDoPenalty = toDoPenalty; }
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
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }
}
