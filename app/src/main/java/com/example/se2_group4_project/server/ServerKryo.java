package com.example.se2_group4_project.server;


import android.util.Log;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.example.se2_group4_project.server.listener.Serverlistener;
import com.example.se2_group4_project.server.response.Test;

import java.io.IOException;
import java.util.Arrays;

public class ServerKryo {

    Server server;
    Kryo kryo;

    public void serverStart(int tcp) throws IOException {
        server = new Server();
        server.start();

        server.bind(tcp);

        kryo = server.getKryo();
        server.addListener(new Serverlistener());
        kryo.register(Test.class);
        Log.d("server" ,"server started: "+ Arrays.toString(server.getConnections()));

    }
}
