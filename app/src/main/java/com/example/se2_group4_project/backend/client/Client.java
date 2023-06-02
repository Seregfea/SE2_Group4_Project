package com.example.se2_group4_project.backend.client;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.example.se2_group4_project.backend.callbacks.ClientCallbacks;
import com.example.se2_group4_project.backend.database.entities.Player;
import com.example.se2_group4_project.callbacks.PlayerCallbacks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class Client extends Thread implements PlayerCallbacks, ClientCallbacks {


    Socket client;
    int port;
    String ipAdresse;
    DataOutputStream serverMessage;
    DataInputStream clientInput;
    String messageInput;
    ClientCallbacks callbacks;
    ObjectMapper mapper;
    Handler handlerUIGameboard;
    Handler clientHandler;
    HandlerThread clientHandlerThread;
    int playerNumber;

    public Handler getClientHandler() {
        return clientHandler;
    }

    public Client(String ipAdresse, int port, ClientCallbacks callbacks, Handler handlerUIGameboard){
        this.ipAdresse =  ipAdresse;
        this.port = port;
        this.callbacks = callbacks;
        this.handlerUIGameboard = handlerUIGameboard;
        this.clientHandlerThread = new HandlerThread("client-handler");
        this.clientHandlerThread.start();
        this.clientHandler = new Handler(this.clientHandler.getLooper());
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

                if (Objects.equals(messageInput, "0") || Objects.equals(messageInput, "1") || Objects.equals(messageInput, "2") || Objects.equals(messageInput, "3")){
                    handlerUIGameboard.post(() -> callbacks.createPlayer(Integer.parseInt(messageInput)));
                    messageSend(messageInput);
                    playerNumber = Integer.parseInt(messageInput);
                    Log.d("player number client", playerNumber +"");
                    messageInput = null;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void messageSend(String messageInput) throws IOException {
        serverMessage.writeUTF(messageInput);
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

    @Override
    public void clientToPlayer(ArrayList<Integer> enemyDice) throws IOException {
        messageSend(objectToJson(enemyDice));
    }

    @Override
    public void createPlayer(int playerNumber) {

    }

    @Override
    public void diceToEnemy(ArrayList<Integer> enemyDice) throws IOException {
        messageSend(objectToJson(enemyDice));
    }

}
