package com.example.se2_group4_project.database.crudInterface;

import com.example.se2_group4_project.database.entities.To_do;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface To_doDAO {
    @Insert
    public void addAbility(To_do to_do);
    @Update
    public void updateAbility(To_do to_do);
    @Delete
    public void deleteAbility(To_do to_do);

    /////////////////////////////////// get ///////////////////////////////////////////

    @Query("select abilitysID from todo where todoID==:todoID")
    public int getAbilitysID(int todoID);

    @Query("select diceNeeded from roomate where roomMateID==:roomMateID")
    public int getDiceNeeded(int roomMateID);

    @Query("select name from roomate where roomMateID==:roomMateID")
    public String getName(int roomMateID);
}
