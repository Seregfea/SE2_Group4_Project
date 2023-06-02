package com.example.se2_group4_project.backend.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chocolateItems")
public class ChocolateItems {
    @ColumnInfo(name = "chocolateID")
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "abilitysID")
    int abilitysID;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "diceNeeded")
    int diceNeeded;
    @ColumnInfo(name = "chocolateCount")
    int chocolateCount;

    public ChocolateItems(int abilitysID, String name, int diceNeeded, int chocolateCount) {
        this.abilitysID = abilitysID;
        this.name = name;
        this.diceNeeded = diceNeeded;
        this.chocolateCount = chocolateCount;
    }

    public int getId() {
        return id;
    }

    public int getAbilitysID() {
        return abilitysID;
    }

    public String getName() {
        return name;
    }

    public int getDiceNeeded() {
        return diceNeeded;
    }

    public int getChocolateCount() {
        return chocolateCount;
    }
}
