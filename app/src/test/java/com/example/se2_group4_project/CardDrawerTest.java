//package com.example.se2_group4_project;
//
//import static com.example.se2_group4_project.cards.CardType.BATHTUB;
//import static com.example.se2_group4_project.cards.CardType.ITEM;
//import static com.example.se2_group4_project.cards.CardType.ROOMMATE;
//import static com.example.se2_group4_project.cards.CardType.WITZIG;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.example.se2_group4_project.cards.Badewanne;
//import com.example.se2_group4_project.cards.Card;
//import com.example.se2_group4_project.cards.CardDrawer;
//import com.example.se2_group4_project.cards.CardType;
//import com.example.se2_group4_project.cards.Couch;
//import com.example.se2_group4_project.cards.Geschirr;
//import com.example.se2_group4_project.cards.Item;
//import com.example.se2_group4_project.cards.Me;
//import com.example.se2_group4_project.cards.RoommateDifficult;
//import com.example.se2_group4_project.cards.RoommateEasy;
//import com.example.se2_group4_project.cards.WitzigToDos;
//import com.example.se2_group4_project.cards.WitzigWitzigToDos;
//import com.example.se2_group4_project.player.PlayerController;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//class CardDrawerTest {
//
//    CardDrawer cardDrawer = new CardDrawer(null);
//    ArrayList<Card> cardArray = new ArrayList<Card>();
//
//
//    @BeforeEach
//    public void initialCardArray() throws FileNotFoundException {
//        cardDrawer.generateInitialCards();
//
//        ArrayList<Card> itemStack = cardDrawer.getItemsStack();
//        Card itemCard = itemStack.get(0);
//        cardArray.add(itemCard);
//
//        ArrayList<Card> roommateDifficultStack = cardDrawer.getRoommateDifficultStack();
//        Card roomateDiffCard = roommateDifficultStack.get(2);
//        cardArray.add(roomateDiffCard);
//
//        ArrayList<Card> roommateEasyStack = cardDrawer.getRoommateEasyStack();
//        Card roomateEasyCard = roommateEasyStack.get(1);
//        cardArray.add(roomateEasyCard);
//
//        ArrayList<Card> witzigStack = cardDrawer.getWitzigStack();
//        Card witzigCard = witzigStack.get(2);
//        cardArray.add(witzigCard);
//
//        ArrayList<Card> witzigWitzigStack = cardDrawer.getWitzigWitzigStack();
//        Card witzigwitzigCard = witzigWitzigStack.get(5);
//        cardArray.add(witzigwitzigCard);
//
//        ArrayList<Card> playerBlueStack = cardDrawer.getPlayerBlueStack();
//        Card badewanneCard;
//        for (Card card : playerBlueStack) {
//            if (card.getCardType() == CardType.BATHTUB) {
//                badewanneCard = card;
//                cardArray.add(badewanneCard);
//                break;
//            }
//        }
//    }
//
//    //Liste ist leer, weil sie nie initalisiert wird - CardDrawer generateInitialCards() muss auch aufgerufen werden
//    @Test
//    public void testAvailability(){
//
//        testCheckIfHighlight(cardArray);
//    }
//
//    @Test
//    public static void testCheckIfHighlight(ArrayList<Card> cardArray) {
//
//        ArrayList<Integer> rolledDice = new ArrayList<>();
//
//        rolledDice.add(3);
//        rolledDice.add(2);
//        rolledDice.add(2);
//        rolledDice.add(1);
//        rolledDice.add(0);
//
//
//        try {
//
//            for (Card card : cardArray) {
//
//                CardType cardType = card.getCardType();
//
//                switch (cardType) {
//                    case ITEM:
//                        JSONObject itemObjekt = new JSONObject();
//                        itemObjekt.put("number", card.getNumber());
//                        itemObjekt.put("count", card.getCount());
//                        itemObjekt.put("stealCard", card.getStealCard());
//                        Item item = new Item(itemObjekt);
//
//                        if (item.isAvailable(rolledDice)) {
//                            System.out.println("ItemCard is available");
//                        }
//                        break;
//
//                    case ROOMMATE:
//                        JSONObject roommateObjektEasy = new JSONObject();
//                        roommateObjektEasy.put("number", card.getNumber());
//                        roommateObjektEasy.put("count", card.getCount());
//                        RoommateEasy roommateEasy = new RoommateEasy(roommateObjektEasy);
//
//                        if (roommateEasy.isAvailable(rolledDice)) {
//                            System.out.println("RoommateEasy is available");
//                        }
//                        JSONObject roommateObjektDifficult = new JSONObject();
//                        roommateObjektDifficult.put("following", card.getFollowing());
//                        roommateObjektDifficult.put("count", card.getCount());
//                        RoommateDifficult roommateDifficult = new RoommateDifficult(roommateObjektDifficult);
//
//                        if (roommateDifficult.isAvailable(rolledDice)) {
//                            System.out.println("RoommateDiff is available");
//                        }
//                        break;
//
//                    case ME:
//                        JSONObject meObjekt = new JSONObject();
//                        meObjekt.put("number", card.getNumber());
//                        meObjekt.put("count", card.getCount());
//                        Me me = new Me(meObjekt);
//
//                        if (me.isAvailable(rolledDice)) {
//                            System.out.println("ME is available");
//                        }
//                        break;
//
//                    case BATHTUB:
//                        JSONObject badewanneObjekt = new JSONObject();
//                        badewanneObjekt.put("number", card.getNumber());
//                        badewanneObjekt.put("count", card.getCount());
//                        Badewanne badewanne = new Badewanne(badewanneObjekt);
//                        if (badewanne.isAvailable(rolledDice)) {
//                            System.out.println("Badewanne is available");
//                        }
//                        break;
//
//                    case COUCH:
//                        JSONObject couchObjekt = new JSONObject();
//                        couchObjekt.put("number", card.getNumber());
//                        couchObjekt.put("count", card.getCount());
//                        Couch couch = new Couch(couchObjekt);
//                        if (couch.isAvailable(rolledDice)) {
//                            System.out.println("Couc is available");
//                        }
//                        break;
//
//                    case TABLEWARE:
//                        JSONObject geschirrObjekt = new JSONObject();
//                        geschirrObjekt.put("number", card.getNumber());
//                        geschirrObjekt.put("count", card.getCount());
//                        Geschirr geschirr = new Geschirr(geschirrObjekt);
//                        if (geschirr.isAvailable(rolledDice)) {
//                            System.out.println("Tableware is available");
//                        }
//                        break;
//
//                    case WITZIG:
//                        JSONObject witzigObjekt = new JSONObject();
//                        witzigObjekt.put("number", card.getNumber());
//                        witzigObjekt.put("count", card.getCount());
//                        WitzigToDos witzigToDos = new WitzigToDos(witzigObjekt);
//                        if (witzigToDos.isAvailable(rolledDice)) {
//                            System.out.println("Witzig is available");
//                        }
//                        break;
//
//                    case WITZIGWITZIG:
//                        JSONObject witzigwitzigObjekt = new JSONObject();
//                        witzigwitzigObjekt.put("number", card.getNumber());
//                        witzigwitzigObjekt.put("count", card.getCount());
//                        WitzigWitzigToDos witzigWitzigToDos = new WitzigWitzigToDos(witzigwitzigObjekt);
//                        if (witzigWitzigToDos.isAvailable(rolledDice)) {
//                            System.out.println("witzigWitzig is available");
//                        }
//                        break;
//                    default:
//                        System.out.println("Unknown card type");
//                }
//            }
//        } catch (JSONException ex) {
//            System.out.println("CardDrawer.checkIfHighlight not available");
//        }
//    }
//    @Test
//    public void testCardAttributes(){
//        testAttributes(cardArray);
//    }
//
//    public static void testAttributes(ArrayList<Card> cardArray) {
//
//        try{
//            for (Card card : cardArray) {
//                if(card.getCardType() == ITEM) {
//                    int number = card.getNumber();
//                    int count = card.getCount();
//                    Assertions.assertEquals(1, number);
//                    Assertions.assertEquals(2, count);
//                }
//                if (card.getCardType() == WITZIG){
//                    int number = card.getNumber();
//                    int count = card.getCount();
//                    Assertions.assertEquals(4,number);
//                    Assertions.assertEquals(3,count);
//                }
//            }
//        } catch (Exception e) {
//            fail("Exception occurred: " + e.getMessage());
//        }
//    }
//}