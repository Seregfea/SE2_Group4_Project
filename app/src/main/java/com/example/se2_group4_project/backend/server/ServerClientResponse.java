package com.example.se2_group4_project.backend.server;

import android.os.Handler;
import android.util.Log;

import com.example.se2_group4_project.backend.database.CRUDoperations;
import com.example.se2_group4_project.backend.callbacks.DatabaseCallbacks;
import com.example.se2_group4_project.backend.callbacks.ServerUICallbacks;
import com.example.se2_group4_project.backend.database.WGDatabase;
import com.example.se2_group4_project.backend.database.entities.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerClientResponse extends Thread implements DatabaseCallbacks {

    final private Socket client;
    final private Handler mainThread;
    final private ServerUICallbacks callbacks;
    private ObjectMapper mapper;
    private CRUDoperations cruDoperations;
    private WGDatabase wgDatabase;
    private int playerNumber;

    ServerClientResponse(Socket client, Handler mainThread, ServerUICallbacks callbacks, WGDatabase wgDatabase, int playerNumber){
        this.client = client;
        this.mainThread = mainThread;
        this.callbacks = callbacks;
        this.wgDatabase = wgDatabase;
        this.playerNumber = playerNumber;
    }

    @Override
    public void run() {
        mapper = new ObjectMapper();
        DataOutputStream serverMessage;
        DataInputStream clientInput;
        String messageInput;
        Handler messageHandler;

        try {
            clientInput = new DataInputStream(client.getInputStream());
            serverMessage = new DataOutputStream(client.getOutputStream());
            messageHandler = new Handler(mainThread.getLooper());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int count = 0;
        while (client.isConnected()){
            try {
                Log.d("wait before test", count+"");
                messageInput = clientInput.readUTF();
                Log.d("wait test", count+"");
                count++;
                if(count == 0){

                    Log.d("message",messageInput);
                    Player player = jsonToObject(messageInput);
                    Log.d("message name", player.getName());

                    runCRUD(player);

                    String finalMessageInput = player.getName();
                    messageHandler.post(() -> callbacks.onMessageRecieve(finalMessageInput));
                    String finalMessageOutput = "we recieved!!!";
                    serverMessage.writeUTF(finalMessageOutput);
                    count++;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void runCRUD(Object object){
        this.cruDoperations = new CRUDoperations(object,this, wgDatabase);
        new Thread(this.cruDoperations).start();
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

    @Override
    public void addPLayer(Player player) {
            callbacks.onMessageSend(player.getName());
    }

    @Override
    public void getPlayer() {

    }
}
