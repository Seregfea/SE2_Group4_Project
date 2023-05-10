package com.example.se2_group4_project.cards;

import java.util.ArrayList;

public class Card {

    //initialisere Karten

    //Attribute der Karten
    private int id;
    private String name;
    private ArrayList<NeededDice> neededDice;
    private ArrayList<CardType> toDo;
    private int schnapspralinen;
    private CardType cardType;
    private String cardFront;
    private String cardBack;
    private boolean isFront;
    private int neededSchnapspralinen;
    private int imageViewID;

    public Card(CardType cardType, int id, String name, ArrayList<NeededDice> neededDice,
                ArrayList<CardType> toDo, int schnapspralinen, String cardFront, String cardBack, int neededSchnapspralinen, boolean isFront) {
        this.cardType = cardType;
        this.id = id;
        this.name = name;
        this.neededDice = neededDice;
        this.toDo = toDo;
        this.schnapspralinen = schnapspralinen;
        this.cardFront = cardFront;
        this.cardBack = cardBack;
        this.neededSchnapspralinen = neededSchnapspralinen;
        this.isFront = isFront;
    }
    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

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

    public ArrayList<NeededDice> getNeededDice() {
        return neededDice;
    }

    public void setNeededDice(ArrayList<NeededDice> neededDice) {
        this.neededDice = neededDice;
    }

    public ArrayList<CardType> getToDo() {
        return toDo;
    }

    public void setToDo(ArrayList<CardType> toDo) {
        this.toDo = toDo;
    }

    public int getSchnapspralinen() {
        return schnapspralinen;
    }

    public void setSchnapspralinen(int schnapspralinen) {
        this.schnapspralinen = schnapspralinen;
    }

    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }

    public String getCardBack() {
        return cardBack;
    }

    public void setCardBack(String cardBack) {
        this.cardBack = cardBack;
    }

    public int getNeededSchnapspralinen() {
        return neededSchnapspralinen;
    }

    public void setNeededSchnapspralinen(int neededSchnapspralinen) {
        this.neededSchnapspralinen = neededSchnapspralinen;
    }

    public boolean isFront() {
        return isFront;
    }

    public String getCurrentCardFront() {
        if(isFront) {
            return cardFront;
        } else {
            return cardBack;
        }
    }

    public String getCurrentCardBack() {
        if(isFront) {
            return cardBack;
        } else {
            return cardFront;
        }
    }

    public int getImageViewID() {
        return imageViewID;
    }

    public void setImageViewID(int imageViewID) {
        this.imageViewID = imageViewID;
    }

    public void setFront(boolean front) {
        isFront = front;
    }
}

