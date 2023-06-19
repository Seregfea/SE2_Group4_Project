package com.example.se2_group4_project.cards;

import com.example.se2_group4_project.pointDisplay.PointDisplay;

import java.util.ArrayList;




public class Schaukelstuhl {
    int id;
    String name;
    CardType cardType;
    int neededSchnapspralinen;
    String cardFront;
    String cardBack;
    boolean isFront;
    private int chairCount;
    public Schaukelstuhl(){
        super();
    }
    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }
    public void setUpChair(){
        setChairCount(0);
    }
    public int getChairCount() {
        return chairCount;
    }
    public void setChairCount(int chairCount) {
        this.chairCount = chairCount;
    }
}
