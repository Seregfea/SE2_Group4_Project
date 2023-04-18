package com.example.se2_group4_project;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CardDrawer {
    //Listen für meine Ablegestapel, Spieler, To-do,
    //
    private ArrayList<Card> initialCards;

    //Karten holen und speichern
    public void generateInitialCards() throws FileNotFoundException {
        this.initialCards = ConvertJSON.getCards();
    }
}
