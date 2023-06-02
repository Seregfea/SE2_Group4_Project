package com.example.se2_group4_project.backend.database.crudInterface;

import com.example.se2_group4_project.backend.database.entities.Furnishings;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FunishingsDAO {

    @Insert
    public void addAbility(Furnishings furnishings);
    @Update
    public void updateAbility(Furnishings furnishings);
    @Delete
    public void deleteAbility(Furnishings furnishings);

    /////////////////////////////////// get ///////////////////////////////////////////

    @Query("select playerID from furnishings where furnishingsID==:furnishingsID")
    public int getPlayerID(int furnishingsID);

    @Query("select abilityID from furnishings where furnishingsID==:furnishingsID")
    public int getAbilityID(int furnishingsID);

    @Query("select name from furnishings where furnishingsID==:furnishingsID")
    public String getName(int furnishingsID);

    @Query("select clean from furnishings where furnishingsID==:furnishingsID")
    public int getClean(int furnishingsID);

    @Query("select dicePlace from furnishings where furnishingsID==:furnishingsID")
    public int getDicePlace(int furnishingsID);

    @Query("select diceNeeded from furnishings where furnishingsID==:furnishingsID")
    public int getDiceNeeded(int furnishingsID);


    //////////////////////////////////// set ///////////////////////////////////////////

    @Query("update furnishings set clean =:clean where playerID==:playerID and name==:name")
    public int setClean(int playerID, int clean, String name);

    @Query("update furnishings set dicePlace =:dicePlace where playerID==:playerID and name==:name")
    public int setDicePlace(int playerID, int dicePlace, String name);
}
