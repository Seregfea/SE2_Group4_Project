package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se2_group4_project.databinding.ActivityMainBinding;
import com.example.se2_group4_project.gameboard_adjustments.SoundManager;
import com.example.se2_group4_project.screens.FindGameActivity;
import com.example.se2_group4_project.screens.OptionsActivity;
import com.example.se2_group4_project.screens.SelectRoomActivity;
import com.example.se2_group4_project.screens.ServerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int musicChoice = sharedPreferences.getInt("musicChoice", R.raw.mysterious); // default_music ist ein Platzhalter für die Standardmusik
        SoundManager.keepMusicGoing = true;
        SoundManager.start(this, musicChoice);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (!SoundManager.keepMusicGoing) {
            SoundManager.stop();
        }
        SoundManager.keepMusicGoing = false;
    }

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



        btnPlay.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(), Gameboard.class);
            intent.putExtra("testmodus", 1);
            startActivity(intent);
        });

        btnFindGame.setOnClickListener(view12 -> {
            Intent intent = new Intent(getApplicationContext(), FindGameActivity.class);
            startActivity(intent);
        });

        btnOptions.setOnClickListener(view13 -> {
            Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
            startActivity(intent);
        });

        activityMainBinding.buttonServer.setOnClickListener(view14 -> {
            Intent intent = new Intent(getApplicationContext(), ServerActivity.class);
            startActivity(intent);
        });

        btnSelectRoom.setOnClickListener(view15 -> {
            Intent intent = new Intent(getApplicationContext(), SelectRoomActivity.class);
            startActivity(intent);
        });
    }
}

