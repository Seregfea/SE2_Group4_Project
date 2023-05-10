package com.example.se2_group4_project.database.crudInterface;

import com.example.se2_group4_project.database.entities.Player;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlayerDAO {

    @Insert
    public void addPlayer(Player player);
    @Update
    public void updatePlayer(Player player);
    @Delete
    public void deletePlayer(Player player);

    ////////////////////////////// get Information ///////////////////////////////

    @Query("select * from player")
    public List<Player> getAllPlayers();

    @Query("select * from player where playerID==:playerID")
    public Player getPlayer(int playerID);

    @Query("select diceCount from player where playerID==:playerID")
    public int getDiceCOunt(int playerID);

    @Query("select chocolateCount from player where playerID==:playerID")
    public int getChocolateCount(int playerID);

    @Query("select dicePlace from player where playerID==:playerID")
    public int getDicePlace(int playerID);

    @Query("select awake from player where playerID==:playerID")
    public int getAwake(int playerID);

    @Query("select diceNeeded from player where playerID==:playerID")
    public int getDiceNeeded(int playerID);

    ////////////////////////////// set Information ///////////////////////////////

    @Query("update player set diceCount =:diceCount  where playerID==:playerID")
    public int setDiceCOunt(int playerID, int diceCount);

    @Query("update player set chocolateCount =:chocolateCount  where playerID==:playerID")
    public int setChocolateCount(int playerID, int chocolateCount);

    @Query("update player set dicePlace =:dicePlace  where playerID==:playerID")
    public int setDicePlace(int playerID, int dicePlace);

    @Query("update player set awake =:awake  where playerID==:playerID")
    public int setAwake(int playerID, int awake);

    @Query("update player set diceNeeded =:diceNeeded  where playerID==:playerID")
    public int setDiceNeeded(int playerID, int diceNeeded);

}
