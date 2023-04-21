package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import com.esotericsoftware.minlog.Log;
import com.example.se2_group4_project.server.ServerKryo;
import com.example.se2_group4_project.server.response.Test;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServerKryo server = new ServerKryo();
        try {
            server.serverStart(25444);
        } catch (IOException e) {
            Log.error("server failed");
            throw new RuntimeException(e);
        }

        server.registerClass(Test.class);

    }
}