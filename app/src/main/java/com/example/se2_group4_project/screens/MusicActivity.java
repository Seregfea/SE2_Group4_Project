package com.example.se2_group4_project.screens;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.se2_group4_project.R;
import com.example.se2_group4_project.gameboard_adjustments.SoundManager;
import com.example.se2_group4_project.SoundManager;
import com.example.se2_group4_project.databinding.ActivityMusicBinding;

import java.util.HashMap;

public class MusicActivity extends AppCompatActivity {

    ActivityMusicBinding activityMusicBinding;
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
        activityMusicBinding =  ActivityMusicBinding.inflate(getLayoutInflater());
        View view = activityMusicBinding.getRoot();
        setContentView(view);

        final String[] songTitles = {"Mysterious", "Slow and Childish", "Orchestra Epic", "Swing"};
        final HashMap<String, Integer> songMap = new HashMap<>();
        songMap.put("Mysterious", R.raw.mysterious);
        songMap.put("Slow and Childish", R.raw.slowandchildish);
        songMap.put("Orchestra Epic", R.raw.orchestra_epic);
        songMap.put("Swing", R.raw.swing);
        String currentMusic;


        activityMusicBinding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                startActivity(intent);
            }
        });

        activityMusicBinding.buttonBackgroundMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MusicActivity.this)
                        .setTitle("Wähle einen Song")
                        .setItems(songTitles, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedSong = songTitles[which];
                                int resId = songMap.get(selectedSong);

                                // SharedPreferences speichert Benutzereinstellungen für die ganze Laufzeit der App
                                // mit dem Editor wird SharedPreferences bearbeitet und die resId gespeichert
                                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("musicChoice", resId);
                                editor.apply();

                                SoundManager.changeMusic(MusicActivity.this,resId);

                            }
                        })
                        .show();
            }
        });

        activityMusicBinding.seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100f;
                SoundManager.setVolume(volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        activityMusicBinding.mute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(SoundManager.gameMusic != null) {
                        SoundManager.gameMusic.setVolume(0,0);
                    }
                } else {
                    if(SoundManager.gameMusic != null) {
                        SoundManager.gameMusic.setVolume(1,1);
                    }
                }
            }
        });
    }
}