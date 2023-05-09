package com.example.se2_group4_project;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.se2_group4_project.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    //////////////////////////////////////////

    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        setListener();


    }

    private void setListener(){
        activityMainBinding.serverBtn.setOnClickListener(view -> switchToSrverActivity());

        activityMainBinding.clientBtn.setOnClickListener(view -> switchToClientActivity());
    }

    private void switchToSrverActivity(){
        Intent switchactivity = new Intent(this, ServerActivity.class);
        startActivity(switchactivity);
    }

    private void switchToClientActivity(){
        Intent switchactivity = new Intent(this, ClientActivity.class);
        startActivity(switchactivity);
    }


}