package com.example.se2_group4_project.handler;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class ServerHandler extends Handler {

    Handler handlerui;

    public ServerHandler(Handler handlerui){
        this.handlerui = handlerui;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
    }
}
