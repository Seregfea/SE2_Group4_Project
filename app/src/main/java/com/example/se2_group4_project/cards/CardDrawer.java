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

    public void checkIfHighlight() throws JSONException {
        for(Card card : this.playerBlueStack){

            String CardType = "";

            switch(CardType){
                case "BATHTUB":
                    JSONObject badewanne1 = null;
                    Badewanne badewanne = new Badewanne(badewanne1);
                    if(badewanne.isAvailable(playerController.getDiceValuesUsable())){
                        highlightCards();
                    }
            }
        }
    }
}
