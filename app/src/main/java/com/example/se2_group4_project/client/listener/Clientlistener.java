package com.example.se2_group4_project.client.listener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.example.se2_group4_project.server.response.Test;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Clientlistener extends Listener {

    Context context;

    public Clientlistener(Context context){
        this.context = context;
    }
    @Override
    public void connected(Connection connection){
        Log.d("client",connection.getRemoteAddressTCP().getHostString() + " / Client is connected!!!");

    }

    @Override
    public void disconnected(Connection connection){
        Log.d("client",connection.getRemoteAddressTCP().getHostString() + " / Client is disconnected!!!");

    }

    public void received(Connection connection, Object object){
            if (object instanceof Test){
                int print = ((Test) object).testnumber;
                Log.d("client","recieved number: "+print);

            }
    }
}
