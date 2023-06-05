package com.example.se2_group4_project.backend.callbacks;

import java.net.Socket;

public interface ServerCallbacks {
    void messageCheat (String message);
    void onMessageSend(String send);
    void messageNextPlayer (String message, Integer player);
}
