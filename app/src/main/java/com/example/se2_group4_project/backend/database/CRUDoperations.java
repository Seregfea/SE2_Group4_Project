package com.example.se2_group4_project.backend.database;

import android.util.Log;

import com.example.se2_group4_project.backend.callbacks.DatabaseCallbacks;
import com.example.se2_group4_project.backend.database.WGDatabase;
import com.example.se2_group4_project.backend.database.entities.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class CRUDoperations extends Thread{

    DatabaseCallbacks databaseCallbacks;
    Object object;
    private WGDatabase wgDatabase;
    private ObjectMapper mapper;


    public CRUDoperations(Object object,DatabaseCallbacks databaseCallbacks, WGDatabase wgDatabase) {
        this.object = object;
        this.databaseCallbacks = databaseCallbacks;
        this.wgDatabase = wgDatabase;
    }

    @Override
    public void run() {
        mapper = new ObjectMapper();
        Log.d("database inserted", object.toString());

        if(object.getClass().isAssignableFrom(Player.class)){
            Log.d("compare object", "object == class");
        }
        Log.d("database is instance", " true");
        String message = objectToString(object);
        Player player = jsonToObject(message);
        databaseCallbacks.addPLayer(player);
        Log.d("database inserted", player.name);
        wgDatabase.getPlayer().addPlayer(player);

        List<Player> list = wgDatabase.getPlayer().getAllPlayers();
        Log.d("database get", list.get(0).name);
        databaseCallbacks.addPLayer(list.get(0));
    }

    private Player jsonToObject(String object){

        Player player = null;
        try {
            player = mapper.readValue(object, Player.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return player;
    }

    private String objectToString(Object object){
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
