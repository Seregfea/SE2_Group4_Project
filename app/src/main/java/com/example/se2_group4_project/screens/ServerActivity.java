package com.example.se2_group4_project.screens;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;

import com.example.se2_group4_project.backend.callbacks.DatabaseCallbacks;
import com.example.se2_group4_project.backend.callbacks.ServerCallbacks;
import com.example.se2_group4_project.backend.client.Client;
import com.example.se2_group4_project.backend.database.WGDatabase;
import com.example.se2_group4_project.backend.database.entities.Player;
import com.example.se2_group4_project.databinding.ActivityServerBinding;
import com.example.se2_group4_project.handler.ServerUIHandler;
import com.example.se2_group4_project.backend.server.IpAdresse;
import com.example.se2_group4_project.backend.server.Server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class ServerActivity extends AppCompatActivity implements ServerCallbacks, DatabaseCallbacks {

    private Server server;

    private ActivityServerBinding activityServerBinding;
    private HandlerThread handlerThread;
    private ServerUIHandler handler;
    private Handler handlerUI;
    private RoomDatabase.Callback myCallback;
    private WGDatabase wgDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityServerBinding = ActivityServerBinding.inflate(getLayoutInflater());
        View view = activityServerBinding.getRoot();
        setContentView(view);

        handlerThread = new HandlerThread("Server Handler");
        handlerThread.start();

        handler = new ServerUIHandler(handlerThread.getLooper());

        setListenerBit();
        createDB();
    }

    ///////////////////////////////////////////// server ///////////////////////////////////

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
                                activityServerBinding.ServerIPnumber.setText(add);
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

                    activityServerBinding.ServerIPnumber.setText(ip);
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
        activityServerBinding.serverButton.setOnClickListener(v -> startBitServer());

    }

    private void startBitServer(){
        gettingIp();

        activityServerBinding.Servermessage.setText("start the server");
        Log.d("serverstart1", "server startet");
        this.server = new Server( 1234, this.handler, this, wgDatabase);
        new Thread(this.server).start();
        Log.d("serverstart2", "server startet");

        Client client = new Client("localhost", 1234, this);
        Log.d("client start", "client startet");
        new Thread(client).start();
    }

    @Override
    public void onMessageSend(String send) {
        Log.d("client connected", send);
        activityServerBinding.Servermessage.setText("from database" + send);
    }

    @Override
    public void onMessageRecieve(String recieve) {
       Log.d("Server got message", recieve);
       //activityServerBinding.Servermessage.setText(recieve);
    }

    /////////////////////////////////////////////// DB ///////////////////////////////////

    private void createDB(){
        this.myCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };
        wgDatabase = Room.databaseBuilder(this, WGDatabase.class,"WGdatabase").addCallback(myCallback).build();
    }



    @Override
    public void addPLayer(Player player) {

    }

    @Override
    public void getPlayer(){
       List <Player> player = wgDatabase.getPlayer().getAllPlayers();
       Player player1 = player.get(0);
       String message = player1.getName();
       activityServerBinding.Servermessage.setText(message);

    }
}