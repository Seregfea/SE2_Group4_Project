package com.example.se2_group4_project.client;

import android.util.Log;

import com.example.se2_group4_project.Callbacks.ClientCallbacks;

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
    ClientCallbacks callbacks;

    public Client(String ipAdresse, int port, ClientCallbacks callbacks){
        this.ipAdresse =  ipAdresse;
        this.port = port;
        this.callbacks = callbacks;
    }
    @Override
    public void run() {

        try {
            client = new Socket(ipAdresse,port);
            clientInput = new DataInputStream(client.getInputStream());
            serverMessage = new DataOutputStream(client.getOutputStream());

            while (client.isConnected()){
                serverMessage.writeUTF("sended to server: " + client.getLocalAddress());

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
}
