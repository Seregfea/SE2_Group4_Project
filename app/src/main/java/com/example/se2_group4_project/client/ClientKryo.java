package com.example.se2_group4_project.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.example.se2_group4_project.client.listener.Clientlistener;
import com.example.se2_group4_project.server.response.Test;

import java.io.IOException;

public class ClientKryo {

    Client client;
    Kryo kryo;

    public void clientSrt(int tcp) throws IOException {
        client = new Client();
        client.start();

        client.connect(5000,"127.0.0.1", 25444);

        kryo = client.getKryo();
        client.addListener(new Clientlistener());

        kryo.register(Test.class);

    }

    public void registerClass(Class newclass){
        kryo.register(newclass);
    }
}
