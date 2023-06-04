package com.example.se2_group4_project.gameboard_adjustments;

import android.widget.ImageView;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.Item;
import com.example.se2_group4_project.cards.RoommateDifficult;
import com.example.se2_group4_project.cards.RoommateEasy;
import com.example.se2_group4_project.cards.Troublemaker;
import com.example.se2_group4_project.cards.WitzigToDos;
import com.example.se2_group4_project.cards.WitzigWitzigToDos;

import java.util.ArrayList;

public class CardAvailability {

    ArrayList<Card> allCardsOnGameboard;

    public CardAvailability(ArrayList<Card> allCardsOnGameboard) {
        this.allCardsOnGameboard = allCardsOnGameboard;
    }

    // wir haben Imageview array mit allen karten am spielfeld aber fürs abchecken
    // muss ich wissen welche karte das jeweilige imageview ist

    public void checkAvailability(int[] dice) {
        for (Object card : allCardsOnGameboard) {
            if (card instanceof Item) {
                Item item = (Item) card;
                if (item.isAvailable(dice)) {
                    // Karte highlighten
                }
            } else if (card instanceof RoommateDifficult) {
                RoommateDifficult roommateDifficult = (RoommateDifficult) card;
                if (roommateDifficult.isAvailable(dice)) {
                    // Karte highlighten
                }
            } else if (card instanceof RoommateEasy) {
                RoommateEasy roommateEasy = (RoommateEasy) card;
                if (roommateEasy.isAvailable(dice)) {
                    // Karte highlighten
                }
            } else if (card instanceof Troublemaker) {
                Troublemaker troublemaker = (Troublemaker) card;
                /*if (troublemaker.isAvailable(dice)) {
                    // Karte highlighten
                } */
            } else if (card instanceof WitzigToDos) {
                WitzigToDos witzigToDos = (WitzigToDos) card;
                if (witzigToDos.isAvailable(dice)) {
                    // Karte highlighten
                }
            } else if (card instanceof WitzigWitzigToDos) {
                WitzigWitzigToDos witzigWitzigToDos = (WitzigWitzigToDos) card;
                if (witzigWitzigToDos.isAvailable(dice)) {
                    // Karte highlighten
                }
            }
        }
    }
}