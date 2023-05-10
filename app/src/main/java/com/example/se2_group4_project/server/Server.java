package com.example.se2_group4_project.server;

import android.os.Handler;
import android.util.Log;

import com.example.se2_group4_project.callbacks.ServerCallbacks;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;

public class Server extends Thread {

    private final int serverPort;
    private ServerSocket server;
    private Boolean serverrun;
    private final Handler handlerServer;
    private final ServerCallbacks callbacks;

    public Server(int serverPort, Handler handlerServer, ServerCallbacks callbacks){
        this.serverPort = serverPort;
        this.serverrun = true;
        this.handlerServer = handlerServer;
        this.callbacks = callbacks;
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
            handlerServer.post(() -> callbacks.onMessageSend(message));

            while (serverrun){
                Log.d("loop","in loop");
                Socket client = server.accept();
                Log.d("client respond", " client respond : " + client.getRemoteSocketAddress());
                ServerClientResponse socketListener = new ServerClientResponse(client, this.handlerServer, this.callbacks);
                socketListener.start();
            }
        } catch (IOException e) {
            Log.e("server error", "server error: "+e);
            throw new RuntimeException(e);
        }
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
}
