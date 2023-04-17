package org.example.Server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerListener extends Listener {

    @Override
    public void connected(Connection connection){
       System.out.println(connection.getRemoteAddressTCP() + " is connected to the Server");

    }
    @Override
    public void disconnected(Connection connection){
        System.out.println(connection.getRemoteAddressTCP() + " has disconnected from the Server");
    }
    @Override
    public void received(Connection connection, Object object){

    }
}
