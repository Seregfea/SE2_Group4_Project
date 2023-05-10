package com.example.se2_group4_project.server;

import android.os.Handler;
import android.util.Log;

import com.example.se2_group4_project.CRUDoperations;
import com.example.se2_group4_project.callbacks.DatabaseCallbacks;
import com.example.se2_group4_project.callbacks.ServerCallbacks;
import com.example.se2_group4_project.database.WGDatabase;
import com.example.se2_group4_project.database.entities.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ServerClientResponse extends Thread implements DatabaseCallbacks {

    final private Socket client;
    final private Handler mainThread;
    final private ServerCallbacks callbacks;
    private ObjectMapper mapper;
    private CRUDoperations cruDoperations;
    private WGDatabase wgDatabase;

    ServerClientResponse(Socket client, Handler mainThread, ServerCallbacks callbacks, WGDatabase wgDatabase){
        this.client = client;
        this.mainThread = mainThread;
        this.callbacks = callbacks;
        this.wgDatabase = wgDatabase;
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
                if(count == 0){
                    messageInput = clientInput.readUTF();
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
