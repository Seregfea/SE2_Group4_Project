package com.example.se2_group4_project.screens;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.se2_group4_project.MainActivity;
import com.example.se2_group4_project.R;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Button btnBack = findViewById(R.id.button_back);
        Button btnBackgrMusic = findViewById(R.id.buttonBackgroundMusic);
        SeekBar seekBarVolume = findViewById(R.id.seekBarVolume);
        Switch switchMute = findViewById(R.id.mute);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                startActivity(intent);
            }
        });

        /*btnBackgrMusic.setOnClickListener(new View.OnClickListener) {
            @Override
            public void onClick (View v){
                new AlertDialog.Builder(MusicActivity.this)
                        .setTitle("Wähle einen Song")
                        .setItems(songTitles, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Der 'which' Parameter enthält den Index des ausgewählten Elements.
                                String selectedSong = songTitles[which];

                                // Hier können Sie die Logik zum Ändern der Musik einfügen.
                                // Zum Beispiel könnten Sie eine Methode aufrufen, um die aktuell gespielte Musik zu stoppen und den ausgewählten Song zu starten.

                            }
                        })
                        .show();
            }
        });

         */

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