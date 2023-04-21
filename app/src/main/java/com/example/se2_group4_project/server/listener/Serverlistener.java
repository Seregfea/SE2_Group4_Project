package com.example.se2_group4_project.server.listener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.example.se2_group4_project.server.response.Test;

public class Serverlistener extends Listener {

    @Override
    public void connected(Connection connection){
        Log.debug(connection.getRemoteAddressTCP().getHostString() + "  is connected!!!");
    }

    @Override
    public void disconnected(Connection connection){
        Log.debug(connection.getRemoteAddressTCP().getHostString() + "  is disconnected!!!");
    }

    public void received(Connection connection, Object object){
            if (object instanceof Test){
                Test test = new Test();
                test.testnumber += 5;
                connection.sendTCP(test);
            }
    }
}
