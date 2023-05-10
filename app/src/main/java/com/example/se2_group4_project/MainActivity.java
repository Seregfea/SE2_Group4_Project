package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se2_group4_project.databinding.ActivityMainBinding;
import com.example.se2_group4_project.databinding.ActivityServerBinding;
import com.example.se2_group4_project.screens.FindGameActivity;
import com.example.se2_group4_project.screens.OptionsActivity;
import com.example.se2_group4_project.screens.PlayGameActivity;
import com.example.se2_group4_project.screens.SelectRoomActivity;
import com.example.se2_group4_project.screens.ServerActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        Button btnPlay = findViewById(R.id.button_play);
        Button btnFindGame = findViewById(R.id.button_find_game);
        Button btnOptions = findViewById(R.id.button_options);
        Button btnSelectRoom = findViewById(R.id.button_select_room);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Gameboard.class);
                startActivity(intent);
            }
        });

        btnFindGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindGameActivity.class);
                startActivity(intent);
            }
        });

        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                startActivity(intent);
            }
        });

        activityMainBinding.buttonServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServerActivity.class);
                startActivity(intent);
            }
        });

        btnSelectRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectRoomActivity.class);
                startActivity(intent);
            }
        });
    }
}