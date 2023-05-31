package com.example.se2_group4_project.backend.callbacks;

import com.example.se2_group4_project.backend.database.entities.Player;

public interface DatabaseCallbacks {
    public void addPLayer(Player player);
    public void getPlayer();
}