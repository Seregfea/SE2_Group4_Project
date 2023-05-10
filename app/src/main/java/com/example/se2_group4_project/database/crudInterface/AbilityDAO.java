package com.example.se2_group4_project.database.crudInterface;

import com.example.se2_group4_project.database.entities.Abilitys;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AbilityDAO {

    @Insert
    public void addAbility(Abilitys abilitys);
    @Update
    public void updateAbility(Abilitys abilitys);
    @Delete
    public void deleteAbility(Abilitys abilitys);

    @Query("select description from abilitys where abilityID==:abilityID")
    public String getDiceCOunt(int abilityID);
}
