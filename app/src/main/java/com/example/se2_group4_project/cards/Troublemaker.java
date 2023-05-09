package com.example.se2_group4_project.cards;

import org.json.*;

public class Troublemaker {
    private int schnapspralinen;
    private boolean me = false;
    private boolean couch = false;
    private boolean tableware = false;
    private boolean bathtub = false;
    private boolean item = false;
    private boolean room = false;
    private boolean roommate = false;
    private boolean turn = false;

    public Troublemaker(JSONObject troublemaker) throws JSONException {
        this.schnapspralinen = troublemaker.getInt("schnapspralinen");

        switch (troublemaker.getString("troublemakerPenalty")){
            case "TABLEWARE":
                this.tableware = true;
                break;
            case "ROOM":
                this.room = true;
                break;
            case "ITEMS":
                this.item = true;
                break;
            case "ROOMMATE":
                this.roommate = true;
                break;
            case "TURN":
                this.turn = true;
                break;
            case "BATHTUB":
                this.bathtub = true;
                break;
            case "ICH":
                this.me = true;
                break;
            case "COUCH":
                this.couch = true;
                break;
            default:
                System.out.println("No troublemaker assignments to do");
                break;
        }
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
}
