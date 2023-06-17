package com.example.se2_group4_project.cheating;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.se2_group4_project.R;

public class CheatPopUpActivity extends PopupWindow {
    private View cheatPopUpView;
    private int cheatingPlayer;
    @SuppressLint("InflateParams")
    public CheatPopUpActivity(Context context){
        super(context);
        Log.d("Cheating", "I am your Popup!");
        cheatPopUpView = LayoutInflater.from(context).inflate(R.layout.cheat_popup, null);
        setContentView(cheatPopUpView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public boolean cheatingPlayer(int cheatingPlayer){
        if(cheatingPlayer == getCheatingPlayer()){
            Log.d("Cheating", "You found the Cheater!");
            return true;
        }
        Log.d("Cheating", "Wrong choice!");
        return false;
    }

    public int getCheatingPlayer() {
        return cheatingPlayer;
    }

    public void setCheatingPlayer(int cheatingPlayer) {
        this.cheatingPlayer = cheatingPlayer;
    }
}
