package com.example.se2_group4_project.backend.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo")
public class To_do {

    @ColumnInfo(name = "todoID")
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "abilitysID")
    int abilitysID;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "diceNeeded")
    int diceNeeded;

    public To_do(String name, int diceNeeded,int abilitysID ) {
        this.name = name;
        this.diceNeeded = diceNeeded;
        this.abilitysID = abilitysID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDiceNeeded() {
        return diceNeeded;
    }

    public int getAbilitysID() {
        return abilitysID;
    }
}
