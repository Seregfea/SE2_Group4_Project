package com.example.se2_group4_project.backend.callbacks;

public interface ServerCallbacks {
    void messageToALL(String message);
    void onMessageSend(String send);
    void messageToOne(String message, Integer player);
    void messageAcceptDice(Integer player);
}
