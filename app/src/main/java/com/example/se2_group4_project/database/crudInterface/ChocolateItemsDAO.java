package com.example.se2_group4_project.database.crudInterface;

import com.example.se2_group4_project.database.entities.Abilitys;
import com.example.se2_group4_project.database.entities.ChocolateItems;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ChocolateItemsDAO {
    @Insert
    public void addAbility(ChocolateItems chocolateItems);
    @Update
    public void updateAbility(ChocolateItems chocolateItems);
    @Delete
    public void deleteAbility(ChocolateItems chocolateItems);

    @Query("select abilitysID from chocolateItems where chocolateID==:chocolateID")
    public int getAbilityID(int chocolateID);

    @Query("select name from chocolateItems where chocolateID==:chocolateID")
    public String getName(int chocolateID);

    @Query("select diceNeeded from chocolateItems where chocolateID==:chocolateID")
    public String getDiceNeeded(int chocolateID);

    @Query("select chocolateCount from chocolateItems where chocolateID==:chocolateID")
    public String getChocolateCount(int chocolateID);
}
