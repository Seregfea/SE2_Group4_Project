//package com.example.se2_group4_project;
//
//import static com.example.se2_group4_project.cards.CardType.ITEM;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.example.se2_group4_project.cards.Card;
//import com.example.se2_group4_project.cards.CardDrawer;
//import com.example.se2_group4_project.cards.CardType;
//import com.example.se2_group4_project.cards.Item;
//import com.example.se2_group4_project.player.PlayerController;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//class CardDrawerTest {
//
//    @Test
//    void testIsAvailable() throws JSONException {
//        PlayerController playerController;
//
//        JSONObject itemObjekt = new JSONObject();
//        itemObjekt.put("number", 1);
//        itemObjekt.put("count", 2);
//        itemObjekt.put("stealCard", 3);
//        Item item = new Item(itemObjekt);
//
//        item.isAvailable(playerController.getDiceValuesUsable());
//    }
//        @Test
//        public void testHighlightCardsWhenItemIsAvailable() {
//            // Set up the test scenario
//            Card card = new Card(ITEM, 0, "Jojo", 1,2,3,null,
//                    0, "jojo_vs", null, 0, true); // Example card values
//            PlayerController playerController = new PlayerController();
//            playerController.setDiceValuesUsable(3);
//            // Create a mock Item object
//            JSONObject itemObjekt = new JSONObject();
//            itemObjekt.put("number", card.getNumber());
//            itemObjekt.put("count", card.getCount());
//            itemObjekt.put("stealCard", card.getStealCard());
//            Item item = new Item(itemObjekt);
//
//            // Set up a flag to track if highlightCards() method is called
//            boolean highlightCardsCalled = false;
//
//            // Create a mock Item object that overrides the highlightCards() method
//            Item mockItem = new Item(itemObjekt) {
//                @Override
//                public void highlightCards() {
//                    highlightCardsCalled = true;
//                }
//            };
//
//            // Perform the test
//            if (mockItem.isAvailable(playerController.getDiceValuesUsable())) {
//                mockItem.highlightCards();
//            }
//
//            // Assert that the highlightCards() method is called
//            assertTrue(highlightCardsCalled);
//        }
//
////    void generateInitialCards() throws FileNotFoundException {
////        //arrange
////        CardDrawer cardDrawer = new CardDrawer();
////
////        //act
////        cardDrawer.generateInitialCards();
////
////        //assert
//////        assertNotNull(cardDrawer.getPlayerBlueStack());
//////        assertNotNull(cardDrawer.getPlayerGreenStack());
//////        assertNotNull(cardDrawer.getPlayerOrangeStack());
//////        assertNotNull(cardDrawer.getPlayerTealStack());
//////        assertNotNull(cardDrawer.getWitzigStack());
//////        assertNotNull(cardDrawer.getWitzigWitzigStack());
//////        assertNotNull(cardDrawer.getItemsStack());
//////        assertNotNull(cardDrawer.getTroublemakerStack());
//////
////
////        ArrayList<Card> blueStackCards = cardDrawer.getPlayerBlueStack();
////        assertEquals(blueStackCards.get(0).getCardType(), CardType.BATHTUB);
////        assertEquals(blueStackCards.get(1).getCardType(), CardType.TABLEWARE);
////        assertEquals(blueStackCards.get(2).getCardType(), CardType.COUCH);
////
////    }
////
////    @Test
////    void checkBlueCards() throws FileNotFoundException {
////        //arrange
////        CardDrawer cardDrawer = new CardDrawer();
////
////        //act
////        cardDrawer.generateInitialCards();
////
////        //assert
////        ArrayList<Card> cards = cardDrawer.getPlayerBlueStack();
////        assertEquals(4, cards.size());
////
////        // Check each card in the list
////        Card card1 = cards.get(0);
////        assertEquals(0, card1.getId());
////        assertEquals("Badewanne-hellblau", card1.getName());
////        assertEquals(CardType.BATHTUB, card1.getCardType());
////        assertEquals(1, card1.getNeededDice().size());
////
////        Card card2 = cards.get(1);
////        assertEquals(1, card2.getId());
////        assertEquals("Geschirr-hellblau", card2.getName());
////        assertEquals(CardType.TABLEWARE, card2.getCardType());
////        assertEquals(1, card2.getNeededDice().size());
////
////        Card card3 = cards.get(2);
////        assertEquals(2, card3.getId());
////        assertEquals("Couch-hellblau", card3.getName());
////        assertEquals(CardType.COUCH, card3.getCardType());
////        assertEquals(1, card3.getNeededDice().size());
////
////        Card card4 = cards.get(3);
////        assertEquals(3, card4.getId());
////        assertEquals("Ich-Hellblau", card4.getName());
////        assertEquals(CardType.ME, card4.getCardType());
////        assertEquals(1, card4.getNeededDice().size());
//////        assertEquals("2", card4.getNeededDice().get(0).getNumber());
//////        assertEquals(2, card4.getNeededDice().get(0).getCount());
//////        assertEquals("ich_hellblau_vs.png", card4.getCardFront());
//////        assertEquals("ich_hellblau_rs.png", card4.getCardBack());
////    } */
//}