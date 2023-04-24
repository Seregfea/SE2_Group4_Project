package com.example.se2_group4_project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se2_group4_project.client.ClientKryo;
import com.example.se2_group4_project.server.ServerKryo;
import com.example.se2_group4_project.server.response.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {
    // 192.168.8.10 meine ip Artunjak

    ServerKryo server;
    ClientKryo client;
    ExecutorService service;
    TextView ipView;
    Button serverBtn;
    Button clientBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientBtn = findViewById(R.id.clientBtn);
        serverBtn = findViewById(R.id.serverBtn);

        ipView = findViewById(R.id.IpAdresse);
        showIP();

        setListener();


    }

    private void setListener(){
        clientBtn.setOnClickListener(view -> executeService(0));

        serverBtn.setOnClickListener(view -> executeService(1));
    }

    private void executeService(int i){
        service = Executors.newSingleThreadExecutor();

        if(i == 1){
            service.execute(this::executeServer);
            Toast toast = Toast.makeText(this,  "  server started!!!",Toast.LENGTH_LONG);
            toast.show();
            Log.d("exec server", "server exec");
        }else {
            service.execute(this::executeClient);
            Toast toast = Toast.makeText(this,  "  client started!!!",Toast.LENGTH_LONG);
            toast.show();
            Log.d("exec client", "client exec");
        }

    }

    private void executeClient(){
        client = new ClientKryo();
        try {
            client.clientSrt(50000,"192.168.8.103", this);
        } catch (IOException e) {
            Log.e("client","client failed: " + e);
            throw new RuntimeException(e);
        }
        client.registerClass(Test.class);
    }

    private void executeServer(){
        server = new ServerKryo();

        try {
            server.serverStart(50000);
            Toast toast = Toast.makeText(this,  "  server started!!!",Toast.LENGTH_LONG);
            toast.show();

        } catch (IOException e) {
            Toast toast =  Toast.makeText(this, e + "  server Failed!!!",Toast.LENGTH_LONG);
            toast.show();
            throw new RuntimeException(e);
        }
    }

    private void  showIP(){
        WifiManager wm = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wm.getConnectionInfo();
        String ip = Formatter.formatIpAddress(wifiInfo.getIpAddress());

        ipView.setText(ip);
    }
}