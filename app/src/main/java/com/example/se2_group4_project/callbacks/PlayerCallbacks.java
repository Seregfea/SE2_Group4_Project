package com.example.se2_group4_project.callbacks;

import java.io.IOException;
import java.util.ArrayList;

public interface PlayerCallbacks {
    void clientToPlayer(ArrayList<Integer> enemyDice) throws IOException;
}
