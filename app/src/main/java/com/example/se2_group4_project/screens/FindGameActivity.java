package com.example.se2_group4_project.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.se2_group4_project.Gameboard;
import com.example.se2_group4_project.MainActivity;
import com.example.se2_group4_project.R;

public class FindGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game);

        Button btnBack = findViewById(R.id.button_back);
        Button btnFindGame = findViewById(R.id.button_game);
        EditText ip = findViewById(R.id.editTextTextPersonName);

        btnFindGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ip.getText().toString().equals("")) {
                    Toast.makeText(FindGameActivity.this, "Please enter IP-adress", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), Gameboard.class);
                    startActivity(intent);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}