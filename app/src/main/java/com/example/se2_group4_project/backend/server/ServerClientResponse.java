package com.example.se2_group4_project.backend.server;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;


import com.example.se2_group4_project.backend.callbacks.ServerCallbacks;

import com.example.se2_group4_project.backend.callbacks.ServerClientCallbacks;
import com.example.se2_group4_project.backend.database.CRUDoperations;
import com.example.se2_group4_project.backend.callbacks.DatabaseCallbacks;
import com.example.se2_group4_project.backend.database.WGDatabase;
import com.example.se2_group4_project.backend.database.entities.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerClientResponse extends Thread implements DatabaseCallbacks, ServerClientCallbacks {

    final private Socket client;
    final private Handler serverThreadHandler;
    final private ServerCallbacks serverCallbacks;
    private CRUDoperations cruDoperations;
    private final WGDatabase wgDatabase;
    private final int playerNumber;
    private String messageInput;
    private String enemy;
    private DataOutputStream serverMessage;
    private DataInputStream clientInput;
    private HandlerThread handlerThread;


    ServerClientResponse(Socket client, Handler serverThreadHandler, ServerCallbacks serverCallbacks, WGDatabase wgDatabase, int playerNumber){
        this.client = client;
        this.serverThreadHandler = serverThreadHandler;
        this.serverCallbacks = serverCallbacks;
        this.wgDatabase = wgDatabase;
        this.playerNumber = playerNumber;

        this.handlerThread = new HandlerThread("ServerClient Handler");
        this.handlerThread.start();
    }

    @Override
    public void run() {

        try {
            this.clientInput = new DataInputStream(client.getInputStream());
            this.serverMessage = new DataOutputStream(client.getOutputStream());

            this.serverMessage.writeUTF(playerNumber+ " " + "3" + " " + "player" + " " + "5");
            Log.d("playerNumber sended", Integer.toString(playerNumber));
            this.serverMessage.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        while (client.isConnected()){
            try {

                this.messageInput = clientInput.readUTF();
                chooseIdentifierFunction(messageDecode(messageInput));

                Log.d("message achieved",messageInput);


                   // runCRUD(player);

                } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void runCRUD(Object object){
        this.cruDoperations = new CRUDoperations(object,this, wgDatabase);
        new Thread(this.cruDoperations).start();
    }

    private String messageDecode(String messageInput){
        String [] commands = messageInput.split(" ");
        Log.d("client player", commands[0]);
        Log.d("client identifier", commands[1]);
        Log.d("client message", commands[2]);
        Log.d("client enemy", commands[3]);
        this.enemy = commands[3];
       return commands[1];
    }
    private void chooseIdentifierFunction(String identifier) throws IOException {
        switch (identifier){
            case "0":
            case "1":
            case "6":
               serverThreadHandler.post(() -> {
                   serverCallbacks.messageToALL(this.messageInput);
               });
               break;
            case "7":
                serverThreadHandler.post(() -> {
                    serverCallbacks.messageAcceptDice(this.playerNumber);
                });
                Log.d("Server Client dice", messageInput);
                break;
            case "4":
            case "5":
                serverThreadHandler.post(() -> {
                    serverCallbacks.messageToOne(this.messageInput,Integer.valueOf(this.enemy));
                });
                break;
            case "10":
                serverThreadHandler.post(() -> {
                    serverCallbacks.onMessageSend(this.enemy);
                });
                break;
            default:
                Log.d("ChooseFunction", "Choose Function Server failed!");
                break;
        }
    }

    public ServerClientCallbacks getCallbacks(){
        return this;
    }

    public Handler getServerClientHandler(){
        return new Handler(this.handlerThread.getLooper());
    }

    ////////////////////////// callbacks ////////////////////////

    @Override
    public void addPLayer(Player player) {
            serverCallbacks.onMessageSend(player.getName());
    }

    @Override
    public void getPlayer() {

    }

    @Override
    public void getMessage(String messageInput) throws IOException {
        this.serverMessage.writeUTF(messageInput);
        this.serverMessage.flush();
    }

    @Override
    public void acceptDice(String message) throws IOException {
        this.serverMessage.writeUTF(this.playerNumber + " " + "6" + " " + message + " " + "5");
    }
}
