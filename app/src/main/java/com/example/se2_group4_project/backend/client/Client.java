package com.example.se2_group4_project.backend.client;

import android.os.Handler;
import android.util.Log;

import com.example.se2_group4_project.backend.callbacks.ClientCallbacks;
import com.example.se2_group4_project.backend.callbacks.ServerUICallbacks;
import com.example.se2_group4_project.backend.database.entities.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class Client extends Thread {


    Socket client;
    int port;
    String ipAdresse;
    DataOutputStream serverMessage;
    DataInputStream clientInput;
    String messageInput;
    ClientCallbacks callbacks;
    ObjectMapper mapper;
    Handler handler;
    int playerNumber;

    public Client(String ipAdresse, int port, ClientCallbacks callbacks, Handler handler){
        this.ipAdresse =  ipAdresse;
        this.port = port;
        this.callbacks = callbacks;
        this.handler = handler;
    }
    @Override
    public void run() {
        mapper = new ObjectMapper();

        try {
            Log.d("ip adresse", ipAdresse);
            client = new Socket(ipAdresse,port);
            clientInput = new DataInputStream(client.getInputStream());
            serverMessage = new DataOutputStream(client.getOutputStream());

            while (client.isConnected()){

                messageInput = clientInput.readUTF();

                if(Objects.equals(messageInput, "0") || Objects.equals(messageInput, "1") || Objects.equals(messageInput, "2") || Objects.equals(messageInput, "3")){
                    handler.post(() -> callbacks.playerNumber(Integer.parseInt(messageInput)));
                    serverMessage.writeUTF(messageInput);
                    playerNumber = Integer.parseInt(messageInput);
                    Log.d("player number client", playerNumber +"");
                    messageInput = null;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private String objectToJson(Object object){
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public int getPlayerNumber() {
        return playerNumber;
    }
}
