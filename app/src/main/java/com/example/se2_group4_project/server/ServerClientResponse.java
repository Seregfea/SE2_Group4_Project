package com.example.se2_group4_project.server;

import android.os.Handler;
import android.util.Log;

import com.example.se2_group4_project.Callbacks.ServerCallbacks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ServerClientResponse extends Thread{

    final private Socket client;
    final private Handler mainThread;
    final private ServerCallbacks callbacks;

    ServerClientResponse(Socket client, Handler mainThread, ServerCallbacks callbacks){
        this.client = client;
        this.mainThread = mainThread;
        this.callbacks = callbacks;
    }

    @Override
    public void run() {
        DataOutputStream serverMessage;
        DataInputStream clientInput;
        String messageInput;
        Handler messageHandler;

        try {
            clientInput = new DataInputStream(client.getInputStream());
            serverMessage = new DataOutputStream(client.getOutputStream());
            messageHandler = new Handler(mainThread.getLooper());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (client.isConnected()){
            try {
               messageInput = clientInput.readUTF();

               Log.d("message",messageInput);
               String finalMessageInput = messageInput;
               messageHandler.post(() -> callbacks.onMessageRecieve(finalMessageInput));
               String finalMessageOutput = "we recieved!!!";
               serverMessage.writeUTF(finalMessageOutput);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String encodeUTF8(ByteBuffer byteBuffer){
        String rawString = "";
        return rawString = String.valueOf(StandardCharsets.UTF_8.decode(byteBuffer));
    }
}
