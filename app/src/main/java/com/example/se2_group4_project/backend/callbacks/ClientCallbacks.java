package com.example.se2_group4_project.backend.callbacks;


import com.example.se2_group4_project.cards.Card;

import java.io.IOException;
import java.util.ArrayList;

public interface ClientCallbacks {
    void createPlayer(int playerNumber);
    void diceToEnemy(ArrayList<Integer> enemyDice, String cheatIdentifier) throws IOException;
    void cheatPopUpActivity();
    void cheatFunction(String cheatStart) throws IOException;
    void clientToPlayer(ArrayList<Integer> enemyDice) throws IOException;
    void endTurn(int pralinen) throws IOException;
    void acceptDice(int yes) throws IOException;
    void endTurnPralinen(int pralinen) throws IOException;
    void endTurnPlayer(ArrayList<Card> cards) throws IOException;
    void updateCards(ArrayList<Card> cards) throws IOException;
}

