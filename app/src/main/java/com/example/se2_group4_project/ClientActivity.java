package com.example.se2_group4_project;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;


import com.example.se2_group4_project.Callbacks.ClientCallbacks;
import com.example.se2_group4_project.client.Client;
import com.example.se2_group4_project.databinding.ActivityClientBinding;


public class ClientActivity extends AppCompatActivity implements ClientCallbacks {

    private ActivityClientBinding activityServerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityServerBinding = ActivityClientBinding.inflate(getLayoutInflater());
        setContentView(activityServerBinding.getRoot());

        setListenerBit();


    }

    private void setListenerBit(){
        activityServerBinding.clientButton.setOnClickListener(v -> startBitClient());
    }

    private void  startBitClient(){
        activityServerBinding.Clientmessage.setText("start the client");
        String port = activityServerBinding.portNumber.getText().toString();
        String ip = activityServerBinding.ClientIPnumber.getText().toString();
        Client client = new Client(ip, Integer.getInteger(port), this);
        new Thread(client).start();
        Log.d("client start", "client startet");
    }

    @Override
    public void onMessageSend(String send) {

    }

    @Override
    public void onMessageRecieve(String recieve) {
        Log.d("got message client", recieve);
        activityServerBinding.Clientmessage.setText(recieve);
    }
}

