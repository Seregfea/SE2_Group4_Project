package com.example.se2_group4_project.cards;

import org.json.*;

public class Troublemaker {
    private int id;
    private String name;
    private CardType cardType;
    private String troublemakerPenalty;
    private int neededSchnapspralinen;
    private int schnapspralinen;
    private String cardFront;
    private String cardBack;
    private boolean isFront;
    private boolean me = false;
    private boolean couch = false;
    private boolean tableware = false;
    private boolean bathtub = false;
    private boolean item = false;
    private boolean room = false;
    private boolean roommate = false;
    private boolean turn = false;
    private String penalty;

    public Troublemaker(){
        super();
    }
    public Troublemaker(JSONObject troublemaker) throws JSONException {
        this.schnapspralinen = troublemaker.getInt("schnapspralinen");
        this.id = troublemaker.getInt("id");

        switch (troublemaker.getString("troublemakerPenalty")){
            case "couch dreckig":
                this.penalty = "couch dreckig";
                break;
            case "badewanne dreckig":
                this.penalty = "badewanne dreckig";
                break;
            case "geschirr dreckig":
                this.penalty = "geschirr dreckig";
                break;
            case "alles dreckig":
                this.penalty = "alles dreckig";
                break;
            case "alle schlafen":
                this.penalty = "alle schlafen";
                break;
            case "ich schlafe":
                this.penalty = "ich schlafe";
                break;
            default:
                System.out.println("No troublemaker assignments to do");
                break;
        }
    }
    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }
    // Getter
    public int getSchnapspralinen() {
        return schnapspralinen;
    }

    public boolean isBathtub() {
        return bathtub;
    }

    public boolean isCouch() {
        return couch;
    }

    public boolean isItem() {
        return item;
    }

    public boolean isMe() {
        return me;
    }

    public boolean isRoom() {
        return room;
    }

    public boolean isRoommate() {
        return roommate;
    }

    public boolean isTableware() {
        return tableware;
    }

    public boolean isTurn() {
        return turn;
    }

    // Setter


    public void setSchnapspralinen(int schnapspralinen) {
        this.schnapspralinen = schnapspralinen;
    }

    public void setBathtub(boolean bathtub) {
        this.bathtub = bathtub;
    }

    public void setCouch(boolean couch) {
        this.couch = couch;
    }

    public void setItem(boolean item) {
        this.item = item;
    }

    public void setMe(boolean me) {
        this.me = me;
    }

    public void setRoom(boolean room) {
        this.room = room;
    }

    public void setRoommate(boolean roommate) {
        this.roommate = roommate;
    }

    public void setTableware(boolean tableware) {
        this.tableware = tableware;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }
}

