package com.example.se2_group4_project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se2_group4_project.client.ClientKryo;
import com.example.se2_group4_project.databinding.ActivityMainBinding;
import com.example.se2_group4_project.jsonComunication.Server;
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
    //////////////////////////////////////////

    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        setListener();


    }


    //////////////////////////////////// krynoet //////////////////
    private void setListener(){
        activityMainBinding.serverBtn.setOnClickListener(view -> switchActivity());

        activityMainBinding.clientBtn.setOnClickListener(view -> executeService(0));
    }

    private void switchActivity(){
        Intent switchactivity = new Intent(this, ServerActivity.class);
        startActivity(switchactivity);
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
            client.clientSrt(50000,"192.168.1.2", this);
        } catch (IOException e) {
            Log.e("client","client failed: " + e);
            throw new RuntimeException(e);
        }
        client.registerClass(Test.class);
    }

    private void executeServer(){
        server = new ServerKryo();
        try {
            server.serverStart(50000, activityMainBinding);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void  showIP(){
        WifiManager wm = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wm.getConnectionInfo();
        String ip = Formatter.formatIpAddress(wifiInfo.getIpAddress());

        activityMainBinding.IpAdresse.setText(ip);
    }
}