package com.example.se2_group4_project.backend.client;

import android.util.Log;

import com.example.se2_group4_project.backend.callbacks.ServerUICallbacks;
import com.example.se2_group4_project.backend.database.entities.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {


    Socket client;
    int port;
    String ipAdresse;
    DataOutputStream serverMessage;
    DataInputStream clientInput;
    String messageInput;
    ServerUICallbacks callbacks;
    ObjectMapper mapper;

    public Client(String ipAdresse, int port, ServerUICallbacks callbacks){
        this.ipAdresse =  ipAdresse;
        this.port = port;
        this.callbacks = callbacks;
    }
    @Override
    public void run() {
        mapper = new ObjectMapper();

        try {
            Log.d("ip adresse", ipAdresse);
            client = new Socket(ipAdresse,port);
            clientInput = new DataInputStream(client.getInputStream());
            serverMessage = new DataOutputStream(client.getOutputStream());
            int count = 0;
            Player player = new Player("test",1,1,1,1,1);
            player.setChoice(10);
            String message = objectToJson(player);
            Log.d("player send", message);
            while (client.isConnected()){

                messageInput = clientInput.readUTF();

                if(count == 0){
                    serverMessage.writeUTF(message);
                    count++;
                }

                messageInput = clientInput.readUTF();

                Log.d("message",messageInput);
                String finalMessageInput = messageInput;
                callbacks.onMessageRecieve(finalMessageInput);
                String finalMessageOutput = "we recieved!!!";
                serverMessage.writeUTF(finalMessageOutput);

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


}
