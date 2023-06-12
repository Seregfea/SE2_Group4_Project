package com.example.se2_group4_project.cards;

import android.widget.ImageView;

import java.util.ArrayList;

public class Card {

    //initialisere Karten

    //Attribute der Karten
    private int id;
    private String name;
    //private ArrayList<NeededDice> neededDice;
    private int count;
    private int number;
    private int count2;
    private int number2;
    private int count3;
    private int number3;
    private int count4;
    private int number4;
    private int minSum;

    private int stealCard;
    private int following;

   // private ArrayList<CardType> toDo;
    private int schnapspralinen;
    private CardType cardType;
    private String cardFront;
    private String cardBack;
    private boolean isFront;
    private int neededSchnapspralinen;
    private int imageViewFrontID;

    private int imageViewBackID;

    public Card(CardType cardType, int id, String name, int number, int count, int number2, int count2, int number3, int count3,
                int number4, int count4, int minSum, int stealCard, ArrayList<CardType> toDo,
                int schnapspralinen, String cardFront, String cardBack, int neededSchnapspralinen, boolean isFront) {
        this.cardType = cardType;
        this.id = id;
        this.name = name;
        this.number = number;
        this.count = count;
        this.number2 = number2;
        this.count2 = count2;
        this.number3 = number3;
        this.count3 = count3;
        this.number4 = number4;
        this.count4 = count4;
        this.minSum = minSum;
        this.stealCard = stealCard;
       // this.toDo = toDo;
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

//    public ArrayList<CardType> getToDo() {
//        return toDo;
//    }
//
//    public void setToDo(ArrayList<CardType> toDo) {
//        this.toDo = toDo;
//    }

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
        return imageViewFrontID;
    }

    public void setImageViewID(int imageViewID) {
        this.imageViewFrontID = imageViewID;
    }

    public void setFront(boolean front) {
        isFront = front;
    }

    public int getCount() {
        return count;
    }

    public int getNumber() {
        return number;
    }

    public int getStealCard() {
        return stealCard;
    }

    public int getFollowing() {
        return following;
    }

    public int getCount2() {
        return count2;
    }

    public int getNumber2() {
        return number2;
    }

    public int getCount3() {
        return count3;
    }

    public int getNumber3() {
        return number3;
    }

    public int getCount4() {
        return count4;
    }

    public int getNumber4() {
        return number4;
    }

    public int getMinSum() {
        return minSum;
    }
}

