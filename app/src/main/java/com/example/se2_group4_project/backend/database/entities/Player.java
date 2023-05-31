package com.example.se2_group4_project.backend.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "player")
public class Player {

    @ColumnInfo(name = "playerID")
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "diceCount")
    public int diceCount;
    @ColumnInfo(name = "chocolateCount")
    public int chocolateCount;
    @ColumnInfo(name = "dicePlace")
    public int dicePlace;
    @ColumnInfo(name = "awake")
    public int awake;
    @ColumnInfo(name = "diceNeeded")
    public int diceNeeded;
    @Ignore
    public int choice;

    @Ignore
    public Player(){}

    public Player(String name, int diceCount, int chocolateCount, int dicePlace, int awake, int diceNeeded){
        this.name = name;
        this.diceCount = diceCount;
        this.chocolateCount = chocolateCount;
        this.dicePlace = dicePlace;
        this.awake = awake;
        this.diceNeeded = diceNeeded;
    }

    /////////////////////////////////////// get ///////////////////////////////
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public int getChocolateCount() {
        return chocolateCount;
    }

    public int getDicePlace() {
        return dicePlace;
    }

    public int getAwake() {
        return awake;
    }

    public int getDiceNeeded() {
        return diceNeeded;
    }

    public int getChoice() {return choice;}

    /////////////////////////////////////// set ///////////////////////////////

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public void setChocolateCount(int chocolateCount) {
        this.chocolateCount = chocolateCount;
    }

    public void setDicePlace(int dicePlace) {
        this.dicePlace = dicePlace;
    }

    public void setAwake(int awake) {
        this.awake = awake;
    }

    public void setDiceNeeded(int diceNeeded) {
        this.diceNeeded = diceNeeded;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
}
