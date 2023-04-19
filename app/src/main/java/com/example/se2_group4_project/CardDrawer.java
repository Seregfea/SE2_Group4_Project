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
    private ArrayList<Card> troublemakerStack = new ArrayList<>();
    private ArrayList<Card> roommateStack = new ArrayList<>();

    //Karten holen und speichern
    public void generateInitialCards() throws FileNotFoundException {
        this.playerBlueStack = ConvertJSON.getCards("playerBlue");
        this.playerGreenStack = ConvertJSON.getCards("playerGreen");
        this.playerOrangeStack = ConvertJSON.getCards("playerOrange");
        this.playerTealStack = ConvertJSON.getCards("playerTeal");
        this.witzigStack = ConvertJSON.getCards("witzig");
        this.witzigWitzigStack = ConvertJSON.getCards("witzigwitzig");
        this.itemsStack = ConvertJSON.getCards("item");
        this.troublemakerStack = ConvertJSON.getCards("troublemaker");
        this.roommateStack = ConvertJSON.getCards("roommate");
    }
}
