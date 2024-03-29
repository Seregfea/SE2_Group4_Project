package com.example.se2_group4_project.cards;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Card {

    //initialisere Karten

    //Attribute der Karten
    private int id;
    private String name;
    //private ArrayList<NeededDice> neededDice;
    private int count;
    private int number;
    private int count2;
    private int number2;
    private int count3;
    private int number3;
    private int count4;
    private int number4;
    private int minSum;

    private int stealCard;
    private int following;


    private ArrayList<CardType> toDoPenalty;
    private int schnapspralinen;
    private CardType cardType;
    private Item item;
    private RoommateDifficult roommateDifficult;
    private RoommateEasy roommateEasy;
    private Schaukelstuhl schaukelstuhl;

    private Troublemaker troublemaker;
    private WitzigToDos witzigToDos;


    private WitzigWitzigToDos witzigWitzigToDos;
    private String cardFront;
    private String cardBack;
    private boolean isFront;
    private int neededSchnapspralinen;
    private int imageViewFrontID;

    private int imageViewBackID;

    public Card(CardType cardType, int id, String name, int number, int count, int number2, int count2, int number3, int count3,
                int number4, int count4, int following, int minSum, int stealCard, ArrayList<CardType> toDo,
                int schnapspralinen, String cardFront, String cardBack, int neededSchnapspralinen, boolean isFront) {
        this.cardType = cardType;
        this.id = id;
        this.name = name;
        this.number = number;
        this.count = count;
        this.number2 = number2;
        this.count2 = count2;
        this.number3 = number3;
        this.count3 = count3;
        this.number4 = number4;
        this.count4 = count4;
        this.following = following;
        this.minSum = minSum;
        this.stealCard = stealCard;
        // this.toDo = toDo;
        this.schnapspralinen = schnapspralinen;
        this.cardFront = cardFront;
        this.cardBack = cardBack;
        this.neededSchnapspralinen = neededSchnapspralinen;
        this.isFront = isFront;
    }

    public WitzigWitzigToDos getWitzigWitzigToDos() {
        return witzigWitzigToDos;
    }

    public void setWitzigWitzigToDos(WitzigWitzigToDos witzigWitzigToDos) {
        this.witzigWitzigToDos = witzigWitzigToDos;
    }
    public WitzigToDos getWitzigToDos() {
        return witzigToDos;
    }

    public void setWitzigToDos(WitzigToDos witzigToDos) {
        this.witzigToDos = witzigToDos;
    }
    public Troublemaker getTroublemaker() {
        return troublemaker;
    }

    public void setTroublemaker(Troublemaker troublemaker) {
        this.troublemaker = troublemaker;
    }
    public RoommateDifficult getRoommateDifficult() {
        return roommateDifficult;
    }

    public void setRoommateDifficult(RoommateDifficult roommateDifficult) {
        this.roommateDifficult = roommateDifficult;
    }
    public Schaukelstuhl getSchaukelstuhl() {
        return schaukelstuhl;
    }

    public void setSchaukelstuhl(Schaukelstuhl schaukelstuhl) {
        this.schaukelstuhl = schaukelstuhl;
    }
    public RoommateEasy getRoommateEasy() {
        return roommateEasy;
    }

    public void setRoommateEasy(RoommateEasy roommateEasy) {
        this.roommateEasy = roommateEasy;
    }
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CardType> getToDo() {
        return toDoPenalty;
    }

    public void setToDo(ArrayList<CardType> toDo) {
        this.toDoPenalty = toDo;
    }

    public int getSchnapspralinen() {
        return schnapspralinen;
    }

    public void setSchnapspralinen(int schnapspralinen) {
        this.schnapspralinen = schnapspralinen;
    }

    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }

    public String getCardBack() {
        return cardBack;
    }

    public void setCardBack(String cardBack) {
        this.cardBack = cardBack;
    }

    public int getNeededSchnapspralinen() {
        return neededSchnapspralinen;
    }

    public void setNeededSchnapspralinen(int neededSchnapspralinen) {
        this.neededSchnapspralinen = neededSchnapspralinen;
    }

    public boolean isFront() {
        return isFront;
    }

    public String getCurrentCardFront() {
        if (isFront) {
            return cardFront;
        } else {
            return cardBack;
        }
    }

    public String getCurrentCardBack() {
        if (isFront) {
            return cardBack;
        } else {
            return cardFront;
        }
    }

    public int getImageViewBackID() {
        return imageViewBackID;
    }

    public void setImageViewBackID(int imageViewBackID) {
        this.imageViewBackID = imageViewBackID;
    }
    public int getImageViewID() {
        return imageViewFrontID;
    }

    public void setImageViewID(int imageViewID) {
        this.imageViewFrontID = imageViewID;
    }

    public void setFront(boolean front) {
        isFront = front;
    }

    public int getCount() {
        return count;
    }

    public int getNumber() {
        return number;
    }

    public int getStealCard() {
        return stealCard;
    }

    public int getFollowing() {
        return following;
    }

    public int getCount2() {
        return count2;
    }

    public int getNumber2() {
        return number2;
    }

    public int getCount3() {
        return count3;
    }

    public int getNumber3() {
        return number3;
    }

    public int getCount4() {
        return count4;
    }

    public int getNumber4() {
        return number4;
    }

    public int getMinSum() {
        return minSum;
    }

    //Methode für JSON Objekte, halten die Daten
    public JSONObject jsonObject() throws JSONException {
        JSONObject card = new JSONObject();
        switch (this.cardType) {
            case ITEM:
                card.put("number", this.getNumber());
                card.put("count", this.getCount());
                card.put("stealCard", this.getStealCard());
                card.put("schnapspralinen", this.getSchnapspralinen());
                card.put("itemBenefit", "");
                 break;
            case ROOMMATE:

            case ME:

            case BATHTUB:

            case COUCH:
                card.put("number", this.getNumber());
                card.put("count", this.getCount());
                break;

            case ROOMMATEDIFF:
                card.put("following", this.getFollowing());
                card.put("count", this.getCount());
                card.put("roommateBenefit","");
                break;
            case TABLEWARE:
                card.put("following", this.getFollowing());
                break;
            case WITZIG:
                card.put("number", this.getNumber());
                card.put("count", this.getCount());
                card.put("number2", this.getNumber2());
                card.put("count2", this.getCount2());
                card.put("following", this.getFollowing());
                card.put("min_sum", this.getMinSum());
                card.put("schnapspralinen", this.getSchnapspralinen());
                JSONArray toDoswitzig = new JSONArray(this.getToDo());
                card.put("toDoPenalty", toDoswitzig);
                break;
            case WITZIGWITZIG:
                card.put("number", this.getNumber());
                card.put("count", this.getCount());
                card.put("number2", this.getNumber2());
                card.put("count2", this.getCount2());
                card.put("number3", this.getNumber3());
                card.put("count3", this.getCount3());
                card.put("number4", this.getNumber4());
                card.put("count4", this.getCount4());
                card.put("schnapspralinen", this.getSchnapspralinen());
                JSONArray toDoswitzigwitzig = new JSONArray(this.getToDo());
                card.put("toDoPenalty", toDoswitzigwitzig);
        }


        return card;
    }
}

