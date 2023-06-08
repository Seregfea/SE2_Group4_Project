package com.example.se2_group4_project.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se2_group4_project.MainActivity;
import com.example.se2_group4_project.R;
import com.example.se2_group4_project.SoundManager;
import com.example.se2_group4_project.databinding.ActivityMusicBinding;
import com.example.se2_group4_project.databinding.ActivityOptionsBinding;

public class OptionsActivity extends AppCompatActivity {
    ActivityOptionsBinding activityOptionsBinding;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOptionsBinding =  ActivityOptionsBinding.inflate(getLayoutInflater());
        View view = activityOptionsBinding.getRoot();
        setContentView(view);

        activityOptionsBinding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        activityOptionsBinding.buttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MusicActivity.class);
                startActivity(intent);
            }
        });
    }
}