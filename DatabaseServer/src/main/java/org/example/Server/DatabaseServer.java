package org.example.Server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class DatabaseServer {



    public static void main(String[] args){
        Server server = new Server();
        server.start();
        try {
            server.bind(25444);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        server.addListener(new ServerListener());

        Kryo kryo = server.getKryo();
    }

}
