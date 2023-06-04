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
    @SuppressLint("InflateParams")
    public CheatPopUpActivity(Context context){
        super(context);
        Log.d("Sensor", "I am your Popup!");
        cheatPopUpView = LayoutInflater.from(context).inflate(R.layout.cheat_popup, null);
        setContentView(cheatPopUpView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


}
