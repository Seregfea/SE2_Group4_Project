package com.example.se2_group4_project.database;

import com.example.se2_group4_project.database.crudInterface.PlayerDAO;
import com.example.se2_group4_project.database.entities.Abilitys;
import com.example.se2_group4_project.database.entities.ChocolateItems;
import com.example.se2_group4_project.database.entities.Furnishings;
import com.example.se2_group4_project.database.entities.Player;
import com.example.se2_group4_project.database.entities.Roomate;
import com.example.se2_group4_project.database.entities.To_do;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Abilitys.class, ChocolateItems.class, Furnishings.class, Player.class, Roomate.class, To_do.class}, version = 1)
public abstract class WGDatabase extends RoomDatabase{
        public abstract PlayerDAO getPlayer();
}
