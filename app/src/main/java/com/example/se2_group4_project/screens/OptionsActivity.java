package com.example.se2_group4_project.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se2_group4_project.MainActivity;
import com.example.se2_group4_project.R;
import com.example.se2_group4_project.SoundManager;

public class OptionsActivity extends AppCompatActivity {

    @Override
    public void onResume() {
        super.onResume();
        SoundManager.keepMusicGoing = true;
        SoundManager.start(this, R.raw.mysterious);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (!SoundManager.keepMusicGoing) {
            SoundManager.stop();
        }
        SoundManager.keepMusicGoing = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Button btnBack = findViewById(R.id.button_back);
        Button btnMusic = findViewById(R.id.buttonMusic);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MusicActivity.class);
                startActivity(intent);
            }
        });
    }
}