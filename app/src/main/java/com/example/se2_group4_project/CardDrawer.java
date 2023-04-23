package com.example.se2_group4_project;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CardDrawer {

    //Listen für meine Ablegestapel
    private ArrayList<Card> playerBlueStack = new ArrayList<>();
    private ArrayList<Card> playerGreenStack = new ArrayList<>();
    private ArrayList<Card> playerOrangeStack = new ArrayList<>();
    private ArrayList<Card> playerTealStack = new ArrayList<>();
    private ArrayList<Card> witzigStack = new ArrayList<>();
    private ArrayList<Card> witzigWitzigStack = new ArrayList<>();
    private ArrayList<Card> itemsStack = new ArrayList<>();

    public ArrayList<Card> getPlayerBlueStack() {
        return playerBlueStack;
    }

    public void setPlayerBlueStack(ArrayList<Card> playerBlueStack) {
        this.playerBlueStack = playerBlueStack;
    }

    public ArrayList<Card> getPlayerGreenStack() {
        return playerGreenStack;
    }

    public void setPlayerGreenStack(ArrayList<Card> playerGreenStack) {
        this.playerGreenStack = playerGreenStack;
    }

    public ArrayList<Card> getPlayerOrangeStack() {
        return playerOrangeStack;
    }

    public void setPlayerOrangeStack(ArrayList<Card> playerOrangeStack) {
        this.playerOrangeStack = playerOrangeStack;
    }

    public ArrayList<Card> getPlayerTealStack() {
        return playerTealStack;
    }

    public void setPlayerTealStack(ArrayList<Card> playerTealStack) {
        this.playerTealStack = playerTealStack;
    }

    public ArrayList<Card> getWitzigStack() {
        return witzigStack;
    }

    public void setWitzigStack(ArrayList<Card> witzigStack) {
        this.witzigStack = witzigStack;
    }

    public ArrayList<Card> getWitzigWitzigStack() {
        return witzigWitzigStack;
    }

    public void setWitzigWitzigStack(ArrayList<Card> witzigWitzigStack) {
        this.witzigWitzigStack = witzigWitzigStack;
    }

    public ArrayList<Card> getItemsStack() {
        return itemsStack;
    }

    public void setItemsStack(ArrayList<Card> itemsStack) {
        this.itemsStack = itemsStack;
    }

    public ArrayList<Card> getTroublemakerStack() {
        return troublemakerStack;
    }

    public void setTroublemakerStack(ArrayList<Card> troublemakerStack) {
        this.troublemakerStack = troublemakerStack;
    }

    public ArrayList<Card> getRoommateStack() {
        return roommateStack;
    }

    public void setRoommateStack(ArrayList<Card> roommateStack) {
        this.roommateStack = roommateStack;
    }

    private ArrayList<Card> troublemakerStack = new ArrayList<>();
    private ArrayList<Card> roommateStack = new ArrayList<>();

    //Karten holen und speichern
    public void generateInitialCards() throws FileNotFoundException {
        this.playerBlueStack = ConvertJSON.getCards("playerBlue");
        this.playerGreenStack = ConvertJSON.getCards("playerGreen");
        this.playerOrangeStack = ConvertJSON.getCards("playerOrange");
        this.playerTealStack = ConvertJSON.getCards("playerTeal");
        this.witzigStack = ConvertJSON.getCards("witzig");
        this.witzigWitzigStack = ConvertJSON.getCards("witzigWitzig");
        this.itemsStack = ConvertJSON.getCards("item");
        this.troublemakerStack = ConvertJSON.getCards("troublemaker");
        this.roommateStack = ConvertJSON.getCards("roommate");
    }
}
