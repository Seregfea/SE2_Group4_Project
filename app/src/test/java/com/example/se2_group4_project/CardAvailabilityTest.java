package com.example.se2_group4_project;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.Item;
import com.example.se2_group4_project.cards.RoommateDifficult;
import com.example.se2_group4_project.cards.RoommateEasy;
import com.example.se2_group4_project.cards.Troublemaker;
import com.example.se2_group4_project.cards.WitzigToDos;
import com.example.se2_group4_project.cards.WitzigWitzigToDos;
import com.example.se2_group4_project.gameboard_adjustments.CardAvailability;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CardAvailabilityTest {


    @Test
    public void testCheckAvailability() {

        CardAvailability cardAvailability;
        ArrayList<Card> allCards;
        Item itemCard;
        RoommateEasy roommateEasyCard;
        RoommateDifficult roommateDifficultCard;
        WitzigToDos witzigToDosCard;
        WitzigWitzigToDos witzigWitzigToDosCard;

        /*


        // Create mock cards
        Item mockItem = Mockito.mock(Item.class);
        RoommateDifficult mockRoommateDifficult = Mockito.mock(RoommateDifficult.class);
        RoommateEasy mockRoommateEasy = Mockito.mock(RoommateEasy.class);
        Troublemaker mockTroublemaker = Mockito.mock(Troublemaker.class);
        WitzigToDos mockWitzigToDos = Mockito.mock(WitzigToDos.class);
        WitzigWitzigToDos mockWitzigWitzigToDos = Mockito.mock(WitzigWitzigToDos.class);


        // Create a list with all mock cards
        ArrayList<Card> allCardsOnGameboard = new ArrayList<>(Arrays.asList(
                mockItem, mockRoommateDifficult, mockRoommateEasy, mockTroublemaker, mockWitzigToDos, mockWitzigWitzigToDos));

        CardAvailability cardAvailability = new CardAvailability(allCardsOnGameboard);

        int[] dice = {1, 2, 3, 4, 5, 6};
        cardAvailability.checkAvailability(dice);

        // Verify that isAvailable was called for each card
        verify(mockItem).isAvailable(dice);
        verify(mockRoommateDifficult).isAvailable(dice);
        verify(mockRoommateEasy).isAvailable(dice);
        verify(mockTroublemaker).isAvailable(dice);
        verify(mockWitzigToDos).isAvailable(dice);
        verify(mockWitzigWitzigToDos).isAvailable(dice);
    }

    */
    }
}

