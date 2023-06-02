package com.example.se2_group4_project.backend.callbacks;



public interface ServerUICallbacks {

    void onMessageSend(String send);
    void onMessageRecieve(String recieve);
    void messageToAll(String message);
}

