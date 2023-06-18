package com.example.se2_group4_project.player;


import android.os.Handler;

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
    private int playerID;
    private ArrayList<Card> playerInitialCards;

    private ArrayList<Card> playerOneCards;
    private ArrayList<Card> playerTwoCards;
    private ArrayList<Card> playerThreeCards;

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

    private int pralinen;

    private boolean hasTroublemaker = false;

    public PlayerController(){}


    public PlayerController(int playerID, ArrayList<Card> playerInitialCards, ClientCallbacks clientCallbacks, Handler clientHandler) {
        this.playerID = playerID;
        this.playerInitialCards = playerInitialCards;
        this.clientCallbacks = clientCallbacks;
        this.clientHandler = clientHandler;

    }

    // 2 Functions

    // 1 Fun - in die Liste einsetzt
    public void cardsAdd(Object object){
        if(object.getClass().isAssignableFrom(Item.class)){
            
        }

        if (object.getClass().isAssignableFrom(Badewanne.class)) {

        }

        if(object.getClass().isAssignableFrom(Geschirr.class)) {

        }

        if(object.getClass().isAssignableFrom(RoommateDifficult.class)) {

        }

        if(object.getClass().isAssignableFrom(RoommateEasy.class)){

        }

        if(object.getClass().isAssignableFrom(Schaukelstuhl.class)){

        }

        if(object.getClass().isAssignableFrom(Troublemaker.class)){

        }

        if(object.getClass().isAssignableFrom(WitzigToDos.class)){

        }
    }

    public void cardsRemove() {

    }

    // Listen mit Würfeln den Client weiterschickt
    // Array Listen von Interger
    // Array Listen mit Wuerfeln speichernp

    // Max gibt die Werte von Dices.
    public void saveDiceValuesUsable(ArrayList<Integer> diceValuesUsable){
        this.diceValuesUsable = diceValuesUsable;
    }

    public void saveDiceValuesNotUsable(ArrayList<Integer> diceValuesNotUsable){
        this.diceValuesNotUsable = diceValuesNotUsable;
    }

    public void saveParkedDices(ArrayList<Integer> parkedDices){
        this.parkedDices = parkedDices;
    }

    public int getPlayerID() {
        return playerID;
    }

    public ArrayList<Card> getPlayerInitialCards() {
        return playerInitialCards;
    }
    public void addPlayerInitialCard(Card card){
        this.playerInitialCards.add(card);
    }
    public void removePlayerInitialCard(Card card){
        this.playerInitialCards.remove(card);
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

    public void setReRoll(boolean reRoll) {
        this.reRoll = reRoll;
    }

    public ArrayList<Integer> getDiceValuesUsable() {
        return diceValuesUsable;
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
                    Thread.sleep(1000);
                    clientCallbacks.cheatFunction("8");
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

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

    public void setPlayerInitialCards(ArrayList<Card> playerInitialCards) {
        this.playerInitialCards = playerInitialCards;
    }

    public ArrayList<Card> getPlayerOneCards() {
        return playerOneCards;
    }

    public void setPlayerOneCards(ArrayList<Card> playerOneCards) {
        this.playerOneCards = playerOneCards;
    }

    public ArrayList<Card> getPlayerTwoCards() {
        return playerTwoCards;
    }

    public void setPlayerTwoCards(ArrayList<Card> playerTwoCards) {
        this.playerTwoCards = playerTwoCards;
    }

    public ArrayList<Card> getPlayerThreeCards() {
        return playerThreeCards;
    }

    public void setPlayerThreeCards(ArrayList<Card> playerThreeCards) {
        this.playerThreeCards = playerThreeCards;
    }

    public int getPralinen() {
        return pralinen;
    }

    public void setPralinen(int pralinen) {
        this.pralinen = pralinen;
    }

    public boolean isHasTroublemaker() {
        return hasTroublemaker;
    }

    public void setHasTroublemaker(boolean hasTroublemaker) {
        this.hasTroublemaker = hasTroublemaker;
    }
}
