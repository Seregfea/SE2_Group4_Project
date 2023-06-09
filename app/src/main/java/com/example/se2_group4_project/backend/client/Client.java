package com.example.se2_group4_project.backend.client;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.example.se2_group4_project.backend.callbacks.ClientCallbacks;
import com.example.se2_group4_project.backend.database.entities.Player;
import com.example.se2_group4_project.callbacks.GameboardCallbacks;

import com.example.se2_group4_project.cards.Card;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import java.util.ArrayList;
import java.util.Objects;

public class Client extends Thread implements ClientCallbacks {



    Socket client;
    int port;
    String ipAdresse;
    DataOutputStream serverMessage;
    DataInputStream clientInput;
    String messageInput;

    GameboardCallbacks gameboardCallbacks;
    ObjectMapper mapper;
    Handler handlerUIGameboard;
    Handler clientHandler;
    HandlerThread clientHandlerThread;
    int playerNumber;

    public Client(String ipAdresse, int port, GameboardCallbacks gameboardCallbacks, Handler handlerUIGameboard){
        this.ipAdresse =  ipAdresse;
        this.port = port;
        this.gameboardCallbacks = gameboardCallbacks;
        this.handlerUIGameboard = handlerUIGameboard;
    }
    @Override
    public void run() {
        mapper = new ObjectMapper();
        this.clientHandlerThread = new HandlerThread("client-handler");
        this.clientHandlerThread.start();

        handlerUIGameboard.post(() -> {
            gameboardCallbacks.getClientHändlerCallbacks(this, new Handler(this.clientHandlerThread.getLooper()));
        });

        try {
            Log.d("ip adresse client new socket", ipAdresse);
            client = new Socket(ipAdresse,port);
            Log.d("client connected", " "+client.isConnected());
            clientInput = new DataInputStream(client.getInputStream());
            serverMessage = new DataOutputStream(client.getOutputStream());

            while (client.isConnected()){

                messageInput = clientInput.readUTF();
                Log.d("input client", messageInput);

                if (Objects.equals(messageInput, "0") || Objects.equals(messageInput, "1") || Objects.equals(messageInput, "2") || Objects.equals(messageInput, "3")){
                    handlerUIGameboard.post(() -> gameboardCallbacks.createPlayer(Integer.parseInt(messageInput)));
                    messageSend(messageInput);
                    playerNumber = Integer.parseInt(messageInput);
                    Log.d("player number client", playerNumber +"");
                    messageInput = null;
                }
                int [] messageArray = messageDecode(messageInput);
                assert messageArray != null;
                chooseIdentifierFunction(messageArray[0]);
            }

            clientHandlerThread.quit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void messageSend(String messageInput) throws IOException {
        serverMessage.writeUTF(messageInput);
    }

    private String messageCode(String messageInput){
        return null;
    }

    private int [] messageDecode(String messageInput){
        return null;
    }

    private void chooseIdentifierFunction(int identifier){
        switch (identifier){
            case 0:
                handlerUIGameboard.post(() -> {
                    try {
                        gameboardCallbacks.cheatFunction(messageInput);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 1:
                // TODO
                // some dice functions

            case 2:
                //TODO
                //cards update

            default:
                Log.d("ChooseFunction", "Choose Function failed!");
                break;
        }
    }


    private Player jsonToObject(String object){

        Player player;
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

    public ClientCallbacks getClientCallbacks(){
        return this;
    }

    //////////////////////////////////// Callbacks ///////////////////////////////////

    @Override
    public void clientToPlayer(ArrayList<Integer> enemyDice) throws IOException {
        messageSend(objectToJson(enemyDice));
    }

    @Override
    public void endTurn(int pralinen) throws IOException {
        messageSend(messageCode("2 " + objectToJson(pralinen)));
    }

    @Override
    public void createPlayer(int playerNumber) {

    }

    @Override
    public void diceToEnemy(ArrayList<Integer> enemyDice, String cheatIdentifier) throws IOException {
        messageSend(messageCode(objectToJson(enemyDice)));
        messageSend(messageCode(cheatIdentifier));
    }

    @Override
    public void cheatFunction(String cheatStart) throws IOException {
        messageSend(messageCode(cheatStart));
    }

    @Override
    public void cheatPopUpActivity() {
        clientHandler.post(new Runnable() {
            @Override
            public void run() {
                gameboardCallbacks.cheatPopUpActivity();
            }
        });
    }


}
