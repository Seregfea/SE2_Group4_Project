package com.example.se2_group4_project.backend.callbacks;

import java.io.IOException;
import java.util.ArrayList;

public interface ClientCallbacks {
    void createPlayer(int playerNumber);
    void diceToEnemy(ArrayList<Integer> enemyDice) throws IOException;
}
