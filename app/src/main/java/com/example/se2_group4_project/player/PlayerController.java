package com.example.se2_group4_project.player;


import android.os.Handler;
import android.util.Log;

import com.example.se2_group4_project.backend.callbacks.ClientCallbacks;
import com.example.se2_group4_project.cards.Badewanne;
import com.example.se2_group4_project.cards.Card;

import com.example.se2_group4_project.cards.Geschirr;
import com.example.se2_group4_project.cards.Item;
import com.example.se2_group4_project.cards.RoommateDifficult;
import com.example.se2_group4_project.cards.RoommateEasy;
import com.example.se2_group4_project.cards.Schaukelstuhl;
import com.example.se2_group4_project.cards.Troublemaker;
import com.example.se2_group4_project.cards.WitzigToDos;


import java.io.IOException;
import java.util.ArrayList;

public class PlayerController {
    private final int playerID;
    private ClientCallbacks clientCallbacks;
    private Handler clientHandler;
    private int playerTurn = 0;
    private int diceCount = 4;

    private int myTurn = 0;

    // testweise auf 1
    private int parkDiceCount = 1;

    // tesweise auf true
    private boolean reRoll = true;
    private ArrayList<Integer> diceValuesUsable;
    private ArrayList<Integer> diceValuesNotUsable;
    private ArrayList<Integer> parkedDices = new ArrayList<>();


    private ArrayList<Card> playerUpdatedCards;
    private int pralinen;
    private ArrayList<Item> itemCards;



    public PlayerController(int playerID, ArrayList<Card> playerInitialCards, ClientCallbacks clientCallbacks, Handler clientHandler) {
        this.playerID = playerID;
        this.playerUpdatedCards = playerInitialCards;
        this.clientCallbacks = clientCallbacks;
        this.clientHandler = clientHandler;
        Log.d("player constructor", playerInitialCards.size()+"");

    }

    // 2 Functions

    // 1 Fun - in die Liste einsetzt

    // Listen mit Würfeln den Client weiterschickt
    // Array Listen von Interger
    // Array Listen mit Wuerfeln speichernp

    // Max gibt die Werte von Dices.
    public int getPlayerID() {
        return playerID;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public int getParkDiceCount() {
        return parkDiceCount;
    }

    public void setParkDiceCount(int parkDiceCount) {
        this.parkDiceCount = parkDiceCount;
    }

    public boolean isReRoll() {
        return reRoll;
    }

    public void setDiceValuesUsable(ArrayList<Integer> diceValuesUsable) {
        this.diceValuesUsable = diceValuesUsable;
    }

    public ArrayList<Integer> getDiceValuesNotUsable() {
        return diceValuesNotUsable;
    }

    public void setDiceValuesNotUsable(ArrayList<Integer> diceValuesNotUsable) {
        this.diceValuesNotUsable = diceValuesNotUsable;
    }

    public ArrayList<Integer> getParkedDices() {
        return parkedDices;
    }

    public void setParkedDices(ArrayList<Integer> parkedDices) {
        this.parkedDices = parkedDices;
    }

    public void diceToServer() {
        clientHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    clientCallbacks.diceToEnemy(getDiceValuesNotUsable(), "1");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public ArrayList<Card> getPlayerUpdatedCards() {
        return this.playerUpdatedCards;
    }
    public void setPlayerUpdatedCards(ArrayList<Card> cards){this.playerUpdatedCards = cards;}
    public void addPlayerUpdatedCard(Card card){this.playerUpdatedCards.add(card);}
    public void removePlayerUpdatedCard(Card card){this.playerUpdatedCards.remove(card);}

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }
      
    public int getMyTurn() {
        return myTurn;
    }

    public void setMyTurn(int myTurn) {
        this.myTurn = myTurn;

    }
}
