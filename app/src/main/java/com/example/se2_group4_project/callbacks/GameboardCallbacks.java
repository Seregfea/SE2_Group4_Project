package com.example.se2_group4_project.callbacks;

import android.os.Handler;

import com.example.se2_group4_project.backend.callbacks.ClientCallbacks;
import com.example.se2_group4_project.cards.Card;

import java.io.IOException;
import java.util.ArrayList;

public interface GameboardCallbacks {
    void diceResults(ArrayList<Integer> playerDice, ArrayList<Integer> enemyDice);

    void diceValues(ArrayList<Integer> playerDices, ArrayList<Integer> enemyDices);

    void parkedDiceValues(ArrayList<Integer> parkedDices);

    void diceEnemy(ArrayList<Integer> diceEnemy);

    void diceFirstAccept(int message);

    void sendDiceEnemyAccept(int accept);

    void sendedEnemyDice(ArrayList<Integer> enemyDice);

    void cheatPopUpActivity(int cheatedPlayer);

    void cheatFunction(String cheatStart) throws IOException;

    void createPlayer(int playerNumber);

    void getClientHändlerCallbacks(ClientCallbacks clientCallbacks, Handler clientHandler);
    void playerTurn(int playerNumber, ArrayList<Card> cards);
}
