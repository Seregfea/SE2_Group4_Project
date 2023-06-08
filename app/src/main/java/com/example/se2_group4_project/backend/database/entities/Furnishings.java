package com.example.se2_group4_project.backend.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "furnishings")
public class Furnishings {

    @ColumnInfo(name = "furnishingsID")
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "playerID")
    int playerID;
    @ColumnInfo(name = "abilityID")
    int abilityID;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "clean")
    int clean;
    @ColumnInfo(name = "dicePlace")
    int dicePlace;
    @ColumnInfo(name = "diceNeeded")
    int diceNeeded;

    public Furnishings(int playerID, int abilityID, String name, int clean, int dicePlace, int diceNeeded) {
        this.playerID = playerID;
        this.abilityID = abilityID;
        this.name = name;
        this.clean = clean;
        this.dicePlace = dicePlace;
        this.diceNeeded = diceNeeded;
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

    public int getClean() {
        return clean;
    }

    public int getDicePlace() {
        return dicePlace;
    }

    public int getDiceNeeded() {
        return diceNeeded;
    }

    public void setClean(int clean) {
        this.clean = clean;
    }

    public void setDicePlace(int dicePlace) {
        this.dicePlace = dicePlace;
    }

    public void setDiceNeeded(int diceNeeded) {
        this.diceNeeded = diceNeeded;
    }
}
