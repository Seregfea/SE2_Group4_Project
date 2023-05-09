package com.example.se2_group4_project;

import static org.junit.jupiter.api.Assertions.*;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.CardDrawer;
import com.example.se2_group4_project.cards.CardType;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

class CardDrawerTest {

   /* @Test
    void generateInitialCards() throws FileNotFoundException {
        //arrange
        CardDrawer cardDrawer = new CardDrawer();

        //act
        cardDrawer.generateInitialCards();

        //assert
//        assertNotNull(cardDrawer.getPlayerBlueStack());
//        assertNotNull(cardDrawer.getPlayerGreenStack());
//        assertNotNull(cardDrawer.getPlayerOrangeStack());
//        assertNotNull(cardDrawer.getPlayerTealStack());
//        assertNotNull(cardDrawer.getWitzigStack());
//        assertNotNull(cardDrawer.getWitzigWitzigStack());
//        assertNotNull(cardDrawer.getItemsStack());
//        assertNotNull(cardDrawer.getTroublemakerStack());
//

        ArrayList<Card> blueStackCards = cardDrawer.getPlayerBlueStack();
        assertEquals(blueStackCards.get(0).getCardType(), CardType.BATHTUB);
        assertEquals(blueStackCards.get(1).getCardType(), CardType.TABLEWARE);
        assertEquals(blueStackCards.get(2).getCardType(), CardType.COUCH);

    }

    @Test
    void checkBlueCards() throws FileNotFoundException {
        //arrange
        CardDrawer cardDrawer = new CardDrawer();

        //act
        cardDrawer.generateInitialCards();

        //assert
        ArrayList<Card> cards = cardDrawer.getPlayerBlueStack();
        assertEquals(4, cards.size());

        // Check each card in the list
        Card card1 = cards.get(0);
        assertEquals(0, card1.getId());
        assertEquals("Badewanne-hellblau", card1.getName());
        assertEquals(CardType.BATHTUB, card1.getCardType());
        assertEquals(1, card1.getNeededDice().size());

        Card card2 = cards.get(1);
        assertEquals(1, card2.getId());
        assertEquals("Geschirr-hellblau", card2.getName());
        assertEquals(CardType.TABLEWARE, card2.getCardType());
        assertEquals(1, card2.getNeededDice().size());

        Card card3 = cards.get(2);
        assertEquals(2, card3.getId());
        assertEquals("Couch-hellblau", card3.getName());
        assertEquals(CardType.COUCH, card3.getCardType());
        assertEquals(1, card3.getNeededDice().size());

        Card card4 = cards.get(3);
        assertEquals(3, card4.getId());
        assertEquals("Ich-Hellblau", card4.getName());
        assertEquals(CardType.ME, card4.getCardType());
        assertEquals(1, card4.getNeededDice().size());
//        assertEquals("2", card4.getNeededDice().get(0).getNumber());
//        assertEquals(2, card4.getNeededDice().get(0).getCount());
//        assertEquals("ich_hellblau_vs.png", card4.getCardFront());
//        assertEquals("ich_hellblau_rs.png", card4.getCardBack());
    } */
}