package com.example.se2_group4_project.backend.server;

import android.os.Handler;
import android.util.Log;

import com.example.se2_group4_project.backend.callbacks.ServerCallbacks;
import com.example.se2_group4_project.backend.callbacks.ServerClientCallbacks;
import com.example.se2_group4_project.backend.callbacks.ServerUICallbacks;
import com.example.se2_group4_project.backend.database.WGDatabase;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Server extends Thread implements ServerCallbacks {

    private final int serverPort;
    private ServerSocket server;
    private Map<Integer, Socket> clients = new HashMap<Integer, Socket>();
    private Map<Integer, ServerClientCallbacks> clientCallbacks = new HashMap();
    private Map<Integer, Handler> clientHandlers = new HashMap<>();
    private Boolean serverrun;
    private final Handler handlerServer;
    private ServerUICallbacks callbacks;
    private WGDatabase wgDatabase;
    private int playingPlayer = 0;

    public Server(int serverPort, Handler handlerServer, ServerUICallbacks callbacks, WGDatabase wgDatabase){
        this.serverPort = serverPort;
        this.serverrun = true;
        this.handlerServer = handlerServer;
        this.callbacks = callbacks;
        this.wgDatabase = wgDatabase;
    }

    public Server(int serverPort, Handler handlerServer){
        this.serverPort = serverPort;
        this.serverrun = true;
        this.handlerServer = handlerServer;
    }


    @Override
    public void run() {
        try {
            server = new ServerSocket(serverPort);
            Log.d("server runns", "worked " + Arrays.toString(server.getInetAddress().getAddress()));
            SocketAddress own = server.getLocalSocketAddress();
            Log.d("server port", own + " server ");
            Log.d("server port",  server.getLocalPort()+ " server ");

            String message = "server started on port: " + serverPort;
            int count = 0;
            while (serverrun){
                Log.d("loop","in loop");
                Socket client = server.accept();
                clients.put(count,client);
                Log.d("client respond", " client respond : " + client.getRemoteSocketAddress());

                ServerClientResponse socketListener = new ServerClientResponse(client, this.handlerServer, this, wgDatabase, count);
                clientCallbacks.put(count,socketListener.getCallbacks());
                clientHandlers.put(count,socketListener.getServerClientHandler());
                socketListener.start();
                count++;
            }
        } catch (IOException e) {
            Log.e("server error", "server error: "+e);
            throw new RuntimeException(e);
        }
    }


    public String returnPlayer(int count){
        switch (count){
            case 0:

        }
        return null;
    }

    public void serverStop(){
        this.serverrun = false;
        try {
            this.server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setServer_output(Socket client, String output){
        try {
            PrintWriter server_output = new PrintWriter(client.getOutputStream());
            server_output.write(output);
            server_output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ///////////////////////// callbacks ///////////////////////
    @Override
    public void messageToALL(String message)  {
        for (int key : clients.keySet()) {
            sendMessage(message,key);
        }
    }

    @Override
    public void onMessageSend(String send) {

    }

    @Override
    public void messageToOne(String message, Integer player) {
        sendMessage(message,player);
    }

    private void sendMessage(String message, Integer player){
        this.clientHandlers.get(player).post(() -> {
            try {
                this.clientCallbacks.get(player).getMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
