package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se2_group4_project.screens.FindGameActivity;
import com.example.se2_group4_project.screens.OptionsActivity;
import com.example.se2_group4_project.screens.PlayGameActivity;
import com.example.se2_group4_project.screens.SelectRoomActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay = findViewById(R.id.button_play);
        Button btnFindGame = findViewById(R.id.button_find_game);
        Button btnOptions = findViewById(R.id.button_options);
        Button btnSelectRoom = findViewById(R.id.button_select_room);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayGameActivity.class);
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

        btnSelectRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectRoomActivity.class);
                startActivity(intent);
            }
        });
    }
}