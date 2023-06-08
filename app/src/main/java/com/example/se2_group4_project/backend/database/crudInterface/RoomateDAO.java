package com.example.se2_group4_project.backend.database.crudInterface;

import com.example.se2_group4_project.backend.database.entities.Roomate;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RoomateDAO {
    @Insert
    public void addAbility(Roomate roomate);
    @Update
    public void updateAbility(Roomate roomate);
    @Delete
    public void deleteAbility(Roomate roomate);

    /////////////////////////////////// get ///////////////////////////////////////////

    @Query("select playerID from roomate where roomMateID==:roomMateID")
    public int getPlayerID(int roomMateID);

    @Query("select abilityID from roomate where roomMateID==:roomMateID")
    public int getAbilityID(int roomMateID);

    @Query("select name from roomate where roomMateID==:roomMateID")
    public String getName(int roomMateID);

    @Query("select trouble from roomate where roomMateID==:roomMateID")
    public int getTrouble(int roomMateID);

    @Query("select awake from roomate where roomMateID==:roomMateID")
    public int getAwake(int roomMateID);

    @Query("select dicePlace from roomate where roomMateID==:roomMateID")
    public int getDicePlace(int roomMateID);

    @Query("select diceNeeded from roomate where roomMateID==:roomMateID")
    public int getDiceNeeded(int roomMateID);

    @Query("select diceCount from roomate where roomMateID==:roomMateID")
    public int getDiceCount(int roomMateID);

    //////////////////////////////////// set ///////////////////////////////////////////

    @Query("update roomate set awake =:awake where roomMateID==:roomMateID")
    public int setClean(int awake, int roomMateID);

    @Query("update roomate set dicePlace =:dicePlace where roomMateID==:roomMateID")
    public int setDicePlace(int roomMateID, int dicePlace);

    @Query("update roomate set diceCount =:diceCount where roomMateID==:roomMateID")
    public int setDiceCount(int roomMateID, int diceCount);
}
