package com.example.se2_group4_project.backend.callbacks;


import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

public interface ClientCallbacks {
    void createPlayer(int playerNumber);
    void diceToEnemy(ArrayList<Integer> enemyDice, String cheatIdentifier) throws IOException;
    void cheatPopUpActivity();
    void cheatFunction(String cheatStart) throws IOException;

    void clientToPlayer(ArrayList<Integer> enemyDice) throws IOException;
}

