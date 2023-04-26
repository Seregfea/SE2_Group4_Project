package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.se2_group4_project.databinding.ActivityServerBinding;
import com.example.se2_group4_project.jsonComunication.IpAdresse;
import com.example.se2_group4_project.jsonComunication.Server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

public class ServerActivity extends AppCompatActivity {

    Server server;
    ActivityServerBinding activityServerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityServerBinding = ActivityServerBinding.inflate(getLayoutInflater());
        View view = activityServerBinding.getRoot();
        setContentView(view);

        gettingIp();

        setListenerBit();
    }

    private void startBitServer(){
        this.server = new Server(1234);
        new Thread(this.server).start();

        activityServerBinding.message.setText("");

    }

    private void gettingIp(){
        Thread thread = new Thread(){
            public void run(){
                try {
                    Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
                    ArrayList<IpAdresse> adresses = new ArrayList<>();

                    while( n.hasMoreElements()) {
                        NetworkInterface e = n.nextElement();


                        Enumeration<InetAddress> a = e.getInetAddresses();
                        while (a.hasMoreElements()) {
                            InetAddress addr = a.nextElement();
                            String add = addr.getHostAddress();
                            if (add.length() < 17) {
                                activityServerBinding.IPnumber.setText(add);
                                if(!e.getDisplayName().isBlank()){
                                    IpAdresse address = new IpAdresse(add,e.getDisplayName()+"");
                                    adresses.add(address);
                                }
                            }
                        }
                    }

                    String ip = "";
                    String name = "";

                    for (int i=0; i < adresses.size();i++){

                        if(adresses.get(i).getConnectionName().contains("rmnet4")){
                            Log.d("check loop", "in loop rmnet4");
                            ip = adresses.get(i).getIpAdresse();
                            name = adresses.get(i).getConnectionName();
                        }
                        if(adresses.get(i).getConnectionName().contains("wlan")){
                            Log.d("check loop", "in loop wlan");
                            ip = adresses.get(i).getIpAdresse();
                            name = adresses.get(i).getConnectionName();
                            i = adresses.size();
                        }
                    }

                    activityServerBinding.IPnumber.setText(ip);
                    activityServerBinding.ServerName.setText(name);
                    Log.d("binding", "set text");

                } catch ( SocketException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        thread.start();

    }
    private void setListenerBit(){
        activityServerBinding.serverButton.setOnClickListener(view -> startBitServer());

    }
}