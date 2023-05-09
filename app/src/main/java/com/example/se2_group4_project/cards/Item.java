package com.example.se2_group4_project.cards;

public class Item {
    private int id;
    private String name;
    private int number;
    private int count;
    private String itemBenefit;
    private String cardFront;
    private boolean isFront;

    public Item(int id, String name, int number, int count, String itemBenefit, String cardFront, boolean isFront) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.count = count;
        this.itemBenefit = itemBenefit;
        this.cardFront = cardFront;
        this.isFront = isFront;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getItemBenefit() {
        return itemBenefit;
    }

    public void setItemBenefit(String itemBenefit) {
        this.itemBenefit = itemBenefit;
    }

    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }

    public boolean isFront() {
        return isFront;
    }

    public void setFront(boolean front) {
        isFront = front;
    }
}
