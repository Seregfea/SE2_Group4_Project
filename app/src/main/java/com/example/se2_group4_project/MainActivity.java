package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bntPlay = findViewById(R.id.button_play);
        Button btnFindGame = findViewById(R.id.button_find_game);
        Button btnOptions = findViewById(R.id.button_options);
        Button btnSelectRoom = findViewById(R.id.button_select_room);

        btnFindGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SpielSucheActivity.class);
            }
        });
    }
}