package com.example.se2_group4_project.backend.client;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.example.se2_group4_project.backend.callbacks.ClientCallbacks;
import com.example.se2_group4_project.callbacks.GameboardCallbacks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import java.util.ArrayList;

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
    int playerSendedNumber;
    String messageIdentifier;
    String SPACE = " ";
    String ENEMY = "4";
    HandlerThread handlerThread;

    public Client(String ipAdresse, int port, GameboardCallbacks gameboardCallbacks, Handler handlerUIGameboard) {
        this.ipAdresse = ipAdresse;
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
            client = new Socket(ipAdresse, port);
            Log.d("client connected", " " + client.isConnected());
            clientInput = new DataInputStream(client.getInputStream());
            serverMessage = new DataOutputStream(client.getOutputStream());

            while (client.isConnected()) {

                this.messageInput = clientInput.readUTF();
                Log.d("input client", messageInput);

                messageDecode();
                chooseIdentifierFunction(this.messageIdentifier);
            }

            clientHandlerThread.quit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void messageSend(String messageInput) throws IOException {
        serverMessage.writeUTF(messageInput);
    }

    private String messageCode(String messageInput) {
        return this.playerNumber + this.SPACE + messageInput + this.SPACE;
    }

    private void messageDecode() {
        Log.d("client before split", messageInput);
        String[] commands = this.messageInput.split(this.SPACE);
        Log.d("client player", commands[0]);
        Log.d("client identifier", commands[1]);
        Log.d("client message", commands[2]);
        Log.d("client to who", commands[3]);

        this.messageIdentifier = commands[1];
        this.messageInput = commands[2];
        this.playerSendedNumber = Integer.parseInt(commands[0]);
    }

    private void chooseIdentifierFunction(String identifier) throws IOException {
        switch (identifier) {
            case "0":
                handlerUIGameboard.post(() -> {
                    try {
                        gameboardCallbacks.cheatFunction(messageInput);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case "1":
                handlerUIGameboard.post(() -> {
                    gameboardCallbacks.sendedEnemyDice(jsonToArraylist(this.messageInput));
                });
                break;

            case "2":
                //TODO
                //cards update

            case "3":
                startPlayer();
                break;
            case "6":
                handlerUIGameboard.post(() -> {
                    gameboardCallbacks.diceFirstAccept(Integer.parseInt(messageInput));
                });
                break;

            default:
                Log.d("ChooseFunction", "Choose Function failed!");
                break;
        }
    }


    private ArrayList<Integer> jsonToArraylist(String object) {
        ArrayList<Integer> listArray;
        try {
            listArray = mapper.readValue(object, ArrayList.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Log.d("JSON to array", listArray.toString());
        Log.d("JSON to array", listArray.size()+"");
        return listArray;
    }

    private String objectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void startPlayer() throws IOException {
        playerNumber = this.playerSendedNumber;
        handlerUIGameboard.post(() -> gameboardCallbacks.createPlayer(playerNumber));
        Log.d("player number client", playerNumber + "");
        messageInput = "";
    }

    public ClientCallbacks getClientCallbacks() {
        return this;
    }


    //////////////////////////////////// Callbacks ///////////////////////////////////

    @Override
    public void clientToPlayer(ArrayList<Integer> enemyDice) throws IOException {
        messageSend(objectToJson(enemyDice));
    }

    @Override
    public void endTurn(int pralinen) throws IOException {
        messageSend(messageCode("2" + this.SPACE + objectToJson(pralinen)));
    }

    @Override
    public void acceptDice(int yes) throws IOException {
        messageSend(messageCode("4" + this.SPACE + yes + this.SPACE + this.ENEMY));
    }

    @Override
    public void createPlayer(int playerNumber) {

    }

    @Override
    public void diceToEnemy(ArrayList<Integer> enemyDice, String cheatIdentifier) throws IOException {
        messageSend(messageCode(cheatIdentifier + SPACE + objectToJson(enemyDice)) + SPACE + ENEMY);
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
