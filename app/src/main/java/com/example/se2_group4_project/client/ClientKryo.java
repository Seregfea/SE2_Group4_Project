package com.example.se2_group4_project.client;

import android.content.Context;
import android.util.Log;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.example.se2_group4_project.client.listener.Clientlistener;
import com.example.se2_group4_project.server.response.Test;

import java.io.IOException;


public class ClientKryo {

    Client client;
    Kryo kryo;

    public void clientSrt(int tcp, String ip, Context context) throws IOException {
        client = new Client();
        client.start();

        client.connect(5000,ip, tcp);

        kryo = client.getKryo();
        client.addListener(new Clientlistener(context));

        kryo.register(Test.class);

        client.sendTCP(new Test());

        Log.d("client", client.getKryo().toString());

    }

    public void registerClass(Class newclass){
        kryo.register(newclass);
    }

}
