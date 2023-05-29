package com.example.se2_group4_project;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundManager {
    public static MediaPlayer gameMusic;
    static float volume = 1.0f;
    public static boolean keepMusicGoing = false;

    public static void start(Context context, int musicId) {
        if (gameMusic == null || !gameMusic.isPlaying()) {
            gameMusic = MediaPlayer.create(context, musicId);
            gameMusic.setLooping(true);
            gameMusic.setVolume(volume,volume);
            gameMusic.start();
        }
    }

    public static void stop() {
        if (gameMusic != null) {
            gameMusic.stop();
            gameMusic.release();
            gameMusic = null;
        }
    }

    public static void changeMusic(Context context, int newMusicId) {
        stop();
        start(context, newMusicId);
    }


    public static void setVolume(float volume) {
        if(gameMusic != null) {
            gameMusic.setVolume(volume,volume);
        }
    }
}
