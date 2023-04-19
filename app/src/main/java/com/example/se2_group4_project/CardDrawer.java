package com.example.se2_group4_project;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CardDrawer {
    private ArrayList<Card> initialCards;
    //Listen für meine Ablegestapel, Spieler, To-do,
    private ArrayList<Card> spielerStapel = new ArrayList<>();
    private ArrayList<Card> witzigStapel = new ArrayList<>();
    private ArrayList<Card> witzigWitzigStapel = new ArrayList<>();
    private ArrayList<Card> gegenstaendeStapel = new ArrayList<>();
    private ArrayList<Card> stoerenfriedeStapel = new ArrayList<>();
    private ArrayList<Card> mitbewohnerStapel = new ArrayList<>();

    //Karten holen und speichern
    public void generateInitialCards() throws FileNotFoundException {
        this.initialCards = ConvertJSON.getCards();
    }
}
