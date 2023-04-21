package com.example.se2_group4_project.client.listener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.se2_group4_project.server.response.Test;

public class Clientlistener extends Listener {

    @Override
    public void connected(Connection connection){
        Log.debug(connection.getRemoteAddressTCP().getHostString() + " / Client is connected!!!");
    }

    @Override
    public void disconnected(Connection connection){
        Log.debug(connection.getRemoteAddressTCP().getHostString() + " / Client is disconnected!!!");
    }

    public void received(Connection connection, Object object){
            if (object instanceof Test){
                int print = ((Test) object).testnumber;
                Log.debug("recieved number: "+print);
            }
    }
}
