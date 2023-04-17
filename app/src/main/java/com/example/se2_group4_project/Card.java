package com.example.se2_group4_project;

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

    public Card(CardType cardType, int id, String name, ArrayList<NeededDice> neededDice, ArrayList<CardType> toDo, int schnapspralinen) {
        this.cardType = cardType;
        this.id = id;
        this.name = name;
        this.neededDice = neededDice;
        this.toDo = toDo;
        this.schnapspralinen = schnapspralinen;
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


    }

