package com.example.se2_group4_project.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.se2_group4_project.Gameboard;
import com.example.se2_group4_project.MainActivity;
import com.example.se2_group4_project.R;
import com.example.se2_group4_project.gameboard_adjustments.SoundManager;
import com.example.se2_group4_project.databinding.ActivityFindGameBinding;

public class FindGameActivity extends AppCompatActivity {

    ActivityFindGameBinding activityFindGameBinding;
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
        activityFindGameBinding =  ActivityFindGameBinding.inflate(getLayoutInflater());
        View view = activityFindGameBinding.getRoot();
        setContentView(view);
        activityFindGameBinding.buttonGame.setOnClickListener(view1 -> {
            if (activityFindGameBinding.editTextTextPersonName.getText().toString().equals("")) {
                Toast.makeText(this, "Please enter IP-adress", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(getApplicationContext(), Gameboard.class);
                intent.putExtra("ip", activityFindGameBinding.editTextTextPersonName.getText().toString());
                intent.putExtra("testmodus",0);
                Log.d("start gameboard", ""+activityFindGameBinding.editTextTextPersonName.getText());
                startActivity(intent);
            }
        });

        activityFindGameBinding.buttonBack.setOnClickListener(view12 -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}