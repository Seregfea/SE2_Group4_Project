package com.example.se2_group4_project.player;

import com.example.se2_group4_project.cards.Badewanne;
import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.Couch;
import com.example.se2_group4_project.cards.Geschirr;
import com.example.se2_group4_project.cards.Item;
import com.example.se2_group4_project.cards.RoommateDifficult;
import com.example.se2_group4_project.cards.RoommateEasy;
import com.example.se2_group4_project.cards.Schaukelstuhl;
import com.example.se2_group4_project.cards.Troublemaker;
import com.example.se2_group4_project.cards.WitzigToDos;
import com.example.se2_group4_project.database.entities.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerController {
    private int diceCount = 4;

    private ArrayList<Integer> diceValuesUsable;
    private ArrayList<Integer> diceValuesNotUsable;

    private ArrayList<Arrays> cardTypes;

    private ArrayList<Item> itemCards;

    Couch couch;
    Geschirr geschirr;
    Badewanne badewanne;

    public PlayerController(Couch couch, Geschirr geschirr, Badewanne badewanne) {
        this.couch = couch;
        this.geschirr = geschirr;
        this.badewanne = badewanne;
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
}
