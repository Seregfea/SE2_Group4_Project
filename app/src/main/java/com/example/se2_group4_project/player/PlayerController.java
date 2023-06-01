package com.example.se2_group4_project.player;

import com.example.se2_group4_project.cards.Badewanne;
import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.Geschirr;
import com.example.se2_group4_project.cards.Item;
import com.example.se2_group4_project.cards.RoommateDifficult;
import com.example.se2_group4_project.cards.RoommateEasy;
import com.example.se2_group4_project.cards.Schaukelstuhl;
import com.example.se2_group4_project.cards.Troublemaker;
import com.example.se2_group4_project.cards.WitzigToDos;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerController {
    private final int playerID;
    private final ArrayList<Card> playerInitialCards;
    private int diceCount = 4;
    private ArrayList<Integer> diceValuesUsable;
    private ArrayList<Integer> diceValuesNotUsable;
    private ArrayList<Arrays> cardTypes;
    private ArrayList<Item> itemCards;


    public PlayerController(int playerID, ArrayList<Card> playerInitialCards) {
        this.playerID = playerID;
        this.playerInitialCards = playerInitialCards;
    }

    // 2 Functions

    // 1 Fun - in die Liste einsetzt
    public void cardsAdd(Object object){
        if(object.getClass().isAssignableFrom(Item.class)){
            
        }

        if (object.getClass().isAssignableFrom(Badewanne.class)) {

        }

        if(object.getClass().isAssignableFrom(Geschirr.class)) {

        }

        if(object.getClass().isAssignableFrom(RoommateDifficult.class)) {

        }

        if(object.getClass().isAssignableFrom(RoommateEasy.class)){

        }

        if(object.getClass().isAssignableFrom(Schaukelstuhl.class)){

        }

        if(object.getClass().isAssignableFrom(Troublemaker.class)){

        }

        if(object.getClass().isAssignableFrom(WitzigToDos.class)){

        }
    }
    public void cardsRemove() {

    }

    // Listen mit Würfeln den Client weiterschickt
    // Array Listen von Interger
    // Array Listen mit Wuerfeln speichernp

    // Max gibt die Werte von Dices.
    public void saveDiceValuesUsable(ArrayList<Integer> diceValuesUsable){
        this.diceValuesUsable = diceValuesUsable;
    }

    public void saveDiceValuesNotUsable(ArrayList<Integer> diceValuesNotUsable){
        this.diceValuesNotUsable = diceValuesNotUsable;
    }

    public int getPlayerID() {
        return playerID;
    }

    public ArrayList<Card> getPlayerInitialCards() {
        return playerInitialCards;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public ArrayList<Integer> getDiceValuesUsable() {
        return diceValuesUsable;
    }

    public void setDiceValuesUsable(ArrayList<Integer> diceValuesUsable) {
        this.diceValuesUsable = diceValuesUsable;
    }

    public ArrayList<Integer> getDiceValuesNotUsable() {
        return diceValuesNotUsable;
    }

    public void setDiceValuesNotUsable(ArrayList<Integer> diceValuesNotUsable) {
        this.diceValuesNotUsable = diceValuesNotUsable;
    }
}
