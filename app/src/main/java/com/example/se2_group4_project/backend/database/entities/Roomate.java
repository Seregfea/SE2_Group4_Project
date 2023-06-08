package com.example.se2_group4_project.backend.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Roomate {
    @ColumnInfo(name = "roomMateID")
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "playerID")
    int playerID;
    @ColumnInfo(name = "abilityID")
    int abilityID;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "trouble")
    int trouble;
    @ColumnInfo(name = "awake")
    int awake;
    @ColumnInfo(name = "dicePlace")
    int dicePlace;
    @ColumnInfo(name = "diceNeeded")
    int diceNeeded;
    @ColumnInfo(name = "diceCount")
    int diceCount;

    public Roomate(int playerID, int abilityID, String name, int trouble, int awake, int dicePlace, int diceNeeded, int diceCount) {
        this.playerID = playerID;
        this.abilityID = abilityID;
        this.name = name;
        this.trouble = trouble;
        this.awake = awake;
        this.dicePlace = dicePlace;
        this.diceNeeded = diceNeeded;
        this.diceCount = diceCount;
    }

    public int getId() {
        return id;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getAbilityID() {
        return abilityID;
    }

    public String getName() {
        return name;
    }

    public int getTrouble() {
        return trouble;
    }

    public int getAwake() {
        return awake;
    }

    public int getDicePlace() {
        return dicePlace;
    }

    public int getDiceNeeded() {
        return diceNeeded;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setAwake(int awake) {
        this.awake = awake;
    }

    public void setDicePlace(int dicePlace) {
        this.dicePlace = dicePlace;
    }

    public void setDiceNeeded(int diceNeeded) {
        this.diceNeeded = diceNeeded;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }
}
