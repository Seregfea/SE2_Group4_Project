package com.example.se2_group4_project.player;

public class Player {
    private int id;
    private String name;
    private static int diceCount = 4;
    private int chocolateCount;

    public Player(int id, String name, int diceCount, int chocolateCount) {
        this.id = id;
        this.name = name;
        this.diceCount = diceCount;
        this.chocolateCount = chocolateCount;
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

    public static int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public int getChocolateCount() {
        return chocolateCount;
    }

    public void setChocolateCount(int chocolateCount) {
        this.chocolateCount = chocolateCount;
    }
}
