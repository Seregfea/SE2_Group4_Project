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
                default:
                    System.out.println("No to-do penalty assignments to do");
                    break;
            }
            this.toDoPenalty.add(penalty);
        }
    }



        public ArrayList<NeededDice> getNeededDice() {
            return neededDice;
        }

        public ArrayList<String> getToDoPenalty() {
            return toDoPenalty;
        }

        public int getSchnapspralinen() {
            return schnapspralinen;
        }


        public void setNeededDice(ArrayList<NeededDice> neededDice) {
            this.neededDice = neededDice;
        }

        public void setToDoPenalty(ArrayList<String> toDoPenalty) {
            this.toDoPenalty = toDoPenalty;
        }

        public void setSchnapspralinen(int schnapspralinen) {
            this.schnapspralinen = schnapspralinen;
        }

    }

