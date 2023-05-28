package com.example.se2_group4_project.screens;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.se2_group4_project.MainActivity;
import com.example.se2_group4_project.R;
import com.example.se2_group4_project.SoundManager;

import java.util.HashMap;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Button btnBack = findViewById(R.id.button_back);
        Button btnBackMusic = findViewById(R.id.buttonBackgroundMusic);
        SeekBar seekBarVolume = findViewById(R.id.seekBarVolume);
        Switch switchMute = findViewById(R.id.mute);
        final String[] songTitles = {"Mysterious", "Slow and Childish"};
        final HashMap<String, Integer> songMap = new HashMap<>();
        songMap.put("Mysterious", R.raw.mysterious);
        songMap.put("Slow and Childish", R.raw.slowandchildish);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                startActivity(intent);
            }
        });

        btnBackMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MusicActivity.this)
                        .setTitle("Wähle einen Song")
                        .setItems(songTitles, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedSong = songTitles[which];
                                int resId = songMap.get(selectedSong);

                                // Save the selected song's resource ID to SharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("musicChoice", resId);
                                editor.apply();

                                // Then you can play the selected song
                                SoundManager.start(MusicActivity.this,resId);
                            }
                        })
                        .show();
            }
        });




        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Hier können Sie die Lautstärke entsprechend dem Fortschritt der SeekBar einstellen.
                float volume = progress / 100f;
                // mediaPlayer.setVolume(volume, volume); // Sie müssen den MediaPlayer hier zugänglich machen.
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Wird aufgerufen, wenn der Benutzer beginnt, die SeekBar zu bewegen.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Wird aufgerufen, wenn der Benutzer aufhört, die SeekBar zu bewegen.
            }
        });
    }
}