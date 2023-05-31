package com.example.se2_group4_project.backend.database;

import com.example.se2_group4_project.backend.database.entities.Abilitys;
import com.example.se2_group4_project.backend.database.entities.ChocolateItems;
import com.example.se2_group4_project.backend.database.entities.Furnishings;
import com.example.se2_group4_project.backend.database.entities.Player;
import com.example.se2_group4_project.backend.database.crudInterface.AbilityDAO;
import com.example.se2_group4_project.backend.database.crudInterface.ChocolateItemsDAO;
import com.example.se2_group4_project.backend.database.crudInterface.FunishingsDAO;
import com.example.se2_group4_project.backend.database.crudInterface.PlayerDAO;
import com.example.se2_group4_project.backend.database.crudInterface.RoomateDAO;
import com.example.se2_group4_project.backend.database.crudInterface.To_doDAO;
import com.example.se2_group4_project.backend.database.entities.Roomate;
import com.example.se2_group4_project.backend.database.entities.To_do;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Abilitys.class, ChocolateItems.class, Furnishings.class, Player.class, Roomate.class, To_do.class}, version = 1)
public abstract class WGDatabase extends RoomDatabase{
        public abstract PlayerDAO getPlayer();
        public abstract AbilityDAO getAbility();
        public abstract ChocolateItemsDAO getChoco();
        public abstract FunishingsDAO getFunish();
        public abstract RoomateDAO getRoomate();
        public abstract To_doDAO getTodo();
}
