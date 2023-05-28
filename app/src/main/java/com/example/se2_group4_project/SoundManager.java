package com.example.se2_group4_project;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundManager {

    public static MediaPlayer gameMusic;
    public static boolean keepMusicGoing;
    int musicId = R.raw.mysterious;

    public static void start(Context context, int musicId){
        stop();

        // Start music only if not disabled in preferences
        if (keepMusicGoing) {
            gameMusic = MediaPlayer.create(context, musicId);
            gameMusic.setLooping(true);
            gameMusic.start();
        }
    }
    public static void stop() {
        if(gameMusic != null) {
            gameMusic.stop();
            gameMusic.release();
            gameMusic = null;
        }
    }

}
