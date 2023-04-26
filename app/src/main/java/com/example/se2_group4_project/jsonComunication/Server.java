package com.example.se2_group4_project.jsonComunication;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{

    private final int serverPort;
    private InetAddress serverIP;
    private String streamInput;
    private ServerSocket server;
    private Boolean serverrun = true;
    private ArrayList<Socket> clients;
    PrintWriter server_output;

    public Server(int serverPort){
        this.serverPort = serverPort;
        this.serverrun = true;

    }


    @Override
    public void run() {
        try {
            server = new ServerSocket(serverPort);
            Log.d("server runns", "worked " + this.serverIP);

            while (serverrun){
                Log.d("loop","in loop");
                Socket client = new Socket();
                client = server.accept();
                Log.d("client respond", " client respond");
                this.clients.add(client);
                Log.d("client added", "client added: "+client.getLocalAddress());
            }
        } catch (IOException e) {
            Log.e("server error", "server error: "+e);
            throw new RuntimeException(e);
        }
    }

    public InetAddress returnIP(){
        return  this.serverIP;
    }

    public int returnPort(){
        return this.serverPort;
    }

    public String returnInput(){
        return this.streamInput;
    }

    public ArrayList<Socket> getsocketList(){
        return this.clients;
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
            this.server_output = new PrintWriter(client.getOutputStream());
            this.server_output.write(output);
            this.server_output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
