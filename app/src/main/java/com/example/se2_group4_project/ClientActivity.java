package com.example.se2_group4_project;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;


import com.example.se2_group4_project.callbacks.ClientCallbacks;
import com.example.se2_group4_project.databinding.ActivityClientBinding;


public class ClientActivity extends AppCompatActivity implements ClientCallbacks {

    private ActivityClientBinding activityClientBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityClientBinding = ActivityClientBinding.inflate(getLayoutInflater());
        setContentView(activityClientBinding.getRoot());

        //setListenerBit();


    }

    private void setListenerBit(){
        activityClientBinding.clientButton.setOnClickListener(v -> startBitClient());
    }

    private void  startBitClient(){
        activityClientBinding.Clientmessage.setText("start the client");
        String port = activityClientBinding.portNumber.getText().toString();
        String ip = activityClientBinding.ClientIPnumber.getText().toString();
        Log.d("client start", "client startet: " + ip);
       // Client client = new Client(ip, Integer.parseInt(port), this);
        Log.d("client start", "client startet");
        //new Thread(client).start();

    }

    @Override
    public void onMessageSend(String send) {

    }

    @Override
    public void onMessageRecieve(String recieve) {
        Log.d("got message client", recieve);
        //activityClientBinding.Clientmessage.setText(recieve);
    }
}

