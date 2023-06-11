package com.example.se2_group4_project.cards;

import android.content.Context;

import com.example.se2_group4_project.dices.DicePopUpActivity;
import com.example.se2_group4_project.player.PlayerController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
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
    private ArrayList<Card> roommateEasyStack = new ArrayList<>();
    private ArrayList<Card> roommateDifficultStack = new ArrayList<>();
    private ArrayList<Card> troublemakerStack = new ArrayList<>();
    private ArrayList<Card> schaukelstuhlStack = new ArrayList<>();
    private ConvertJSON convertJSON;
    private PlayerController playerController;

    public CardDrawer(Context context) {
        this.convertJSON = new ConvertJSON(context);
    }

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

    public ArrayList<Card> getRoommateEasyStack() {
        return roommateEasyStack;
    }

    public void setRoommateEasyStack(ArrayList<Card> roommateEasyStack) {
        this.roommateEasyStack = roommateEasyStack;
    }

    public ArrayList<Card> getRoommateDifficultStack() {
        return roommateDifficultStack;
    }

    public void setRoommateDifficultStack(ArrayList<Card> roommateDifficultStack) {
        this.roommateDifficultStack = roommateDifficultStack;
    }

    public ArrayList<Card> getSchaukelstuhlStack() {
        return schaukelstuhlStack;
    }

    public void setSchaukelstuhlStack(ArrayList<Card> schaukelstuhlStack) {
        this.schaukelstuhlStack = schaukelstuhlStack;
    }

    //Karten holen und speichern
    public void generateInitialCards() throws FileNotFoundException {
        this.playerBlueStack = this.convertJSON.getCards("playerBlue");
        this.playerGreenStack = this.convertJSON.getCards("playerGreen");
        this.playerOrangeStack = this.convertJSON.getCards("playerOrange");
        this.playerTealStack = this.convertJSON.getCards("playerTeal");
        this.witzigStack = this.convertJSON.getCards("witzig");
        this.witzigWitzigStack = this.convertJSON.getCards("witzigWitzig");
        this.itemsStack = this.convertJSON.getCards("item");
        this.troublemakerStack = this.convertJSON.getCards("troublemaker");
        this.roommateEasyStack = this.convertJSON.getCards("roommateEasy");
        this.roommateDifficultStack = this.convertJSON.getCards("roommateDifficult");
        this.schaukelstuhlStack = this.convertJSON.getCards("schaukelstuhl");
    }


    public void highlightCards() {
    }

    public void checkIfHighlight(ArrayList<Card> cardStack) throws JSONException {

        for (Card card : cardStack) {

            CardType cardType = card.getCardType();

            switch (cardType) {
                case ITEM:
                    JSONObject itemObjekt = new JSONObject();
                    itemObjekt.put("number", card.getNumber());
                    itemObjekt.put("count", card.getCount());
                    itemObjekt.put("stealCard", card.getStealCard());
                    Item item = new Item(itemObjekt);

                    if (item.isAvailable(playerController.getDiceValuesUsable())) {
                        highlightCards();
                    }
                    break;

                case ROOMMATE:
                    JSONObject roommateObjektEasy = new JSONObject();
                    roommateObjektEasy.put("number", card.getNumber());
                    roommateObjektEasy.put("count", card.getCount());
                    RoommateEasy roommateEasy = new RoommateEasy(roommateObjektEasy);

                    if (roommateEasy.isAvailable(playerController.getDiceValuesUsable())) {
                        highlightCards();
                    }
                    JSONObject roommateObjektDifficult = new JSONObject();
                    roommateObjektDifficult.put("following", card.getFollowing());
                    roommateObjektDifficult.put("count", card.getCount());
                    RoommateDifficult roommateDifficult = new RoommateDifficult(roommateObjektDifficult);

                    if (roommateDifficult.isAvailable(playerController.getDiceValuesUsable())) {
                        highlightCards();
                    }
                    break;

                case ME:
                    JSONObject meObjekt = new JSONObject();
                    meObjekt.put("number", card.getNumber());
                    meObjekt.put("count", card.getCount());
                    Me me = new Me(meObjekt);

                    if (me.isAvailable(playerController.getDiceValuesUsable())) {
                        highlightCards();
                    }
                    break;

                case BATHTUB:
                    JSONObject badewanneObjekt = new JSONObject();
                    badewanneObjekt.put("number", card.getNumber());
                    badewanneObjekt.put("count", card.getCount());
                    Badewanne badewanne = new Badewanne(badewanneObjekt);
                    if (badewanne.isAvailable(playerController.getDiceValuesUsable())) {
                        highlightCards();
                    }
                    break;

                case COUCH:
                    JSONObject couchObjekt = new JSONObject();
                    couchObjekt.put("number", card.getNumber());
                    couchObjekt.put("count", card.getCount());
                    Couch couch = new Couch(couchObjekt);
                    if (couch.isAvailable(playerController.getDiceValuesUsable())) {
                        highlightCards();
                    }
                    break;
                case TABLEWARE:
                    JSONObject geschirrObjekt = new JSONObject();
                    geschirrObjekt.put("number", card.getNumber());
                    geschirrObjekt.put("count", card.getCount());
                    Geschirr geschirr = new Geschirr(geschirrObjekt);
                    if (geschirr.isAvailable(playerController.getDiceValuesUsable())) {
                        highlightCards();
                    }
                    break;

                case WITZIG:
                    JSONObject witzigObjekt = new JSONObject();
                    witzigObjekt.put("number", card.getNumber());
                    witzigObjekt.put("count", card.getCount());
                    //witzigObjekt.put("number2", card.getNumber2());
                    //witzigObjekt.put("count2", card.getCount2());
                    witzigObjekt.put("following", card.getFollowing());
                    //witzigObjekt.put("min_sum", card.getMinSum());
                    WitzigToDos witzigToDos = new WitzigToDos(witzigObjekt);
                    if (witzigToDos.isAvailable(playerController.getDiceValuesUsable())) {
                        highlightCards();
                    }
                    break;

                case WITZIGWITZIG:
                    JSONObject witzigwitzigObjekt = new JSONObject();
                    witzigwitzigObjekt.put("number", card.getNumber());
                    witzigwitzigObjekt.put("count", card.getCount());
                    //witzigwitzigObjekt.put("number2", card.getNumber2());
                    //witzigwitzigObjekt.put("count2", card.getCount2());
                    //witzigwitzigObjekt.put("number3", card.getNumber3());
                    //witzigwitzigObjekt.put("count3", card.getCount3());
                    //witzigwitzigObjekt.put("number4", card.getNumber4());
                    //witzigwitzigObjekt.put("count4", card.getCount4());
                    WitzigWitzigToDos witzigWitzigToDos = new WitzigWitzigToDos(witzigwitzigObjekt);
                    if (witzigWitzigToDos.isAvailable(playerController.getDiceValuesUsable())) {
                        highlightCards();
                        break;
                    }
            }
        }
    }
}
