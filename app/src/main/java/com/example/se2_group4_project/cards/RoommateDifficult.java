package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

public class RoommateDifficult {
    private int additionalDice = 0;
    private int addedDicePlace = 0;
    private int count;
    private int following;
    private RoommateDiffType rmDiffType;
    private boolean awake = false;
    private boolean does_not_sleep = false;
    private boolean clean_dishes = false;
    private boolean clean_couch = false;
    private boolean reroll = false;
    private boolean clean_bathtub = false;

    public RoommateDifficult(JSONObject roommateDifficult) throws JSONException {
        this.count = roommateDifficult.getInt("count");
        this.following = roommateDifficult.getInt("following");

        switch (roommateDifficult.getString("roommateBenefit")) {
            case "does_not_sleep":
                this.does_not_sleep = true;
                this.rmDiffType = RoommateDiffType.BUKOWSKI;
                break;
            case "clean_dishes":
                this.clean_dishes = true;
                this.rmDiffType = RoommateDiffType.FW;
                break;
            case "clean_couch":
                this.clean_couch = true;
                this.rmDiffType = RoommateDiffType.HERTA;
                break;
            case "reroll":
                this.reroll = true;
                this.rmDiffType = RoommateDiffType.MU;
                break;
            case "clean_bathtub":
                this.clean_bathtub = true;
                this.rmDiffType = RoommateDiffType.OV;
                break;
            case "park_dice":
                this.addedDicePlace = 1;
                this.rmDiffType = RoommateDiffType.SARAH;
                break;
            default:
                System.out.println("No roommate benefits");
                break;
        }
    }

    public int getAdditionalDice() {
        return additionalDice;
    }

    public void setAdditionalDice(int additionalDice) {
        this.additionalDice = additionalDice;
    }

    public int getAddedDicePlace() {
        return addedDicePlace;
    }

    public void setAddedDicePlace(int addedDicePlace) {
        this.addedDicePlace = addedDicePlace;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isDoes_not_sleep () {
        return does_not_sleep;
    }

    public void setDoes_not_sleep ( boolean does_not_sleep){
        this.does_not_sleep = does_not_sleep;
    }

    public boolean isClean_dishes () {
        return clean_dishes;
    }

    public void setClean_dishes ( boolean clean_dishes){
        this.clean_dishes = clean_dishes;
    }

    public boolean isClean_couch () {
        return clean_couch;
    }

    public void setClean_couch ( boolean clean_couch){
        this.clean_couch = clean_couch;
    }

    public boolean isReroll () {
        return reroll;
    }

    public void setReroll ( boolean reroll){
        this.reroll = reroll;
    }

    public boolean isClean_bathtub () {
        return clean_bathtub;
    }

    public void setClean_bathtub ( boolean clean_bathtub){
        this.clean_bathtub = clean_bathtub;
    }

    public boolean isAwake() {
        return awake;
    }

    public boolean isAvailable(int[] dice) {
        if (count > 0) {
            for (int i = 0; i < dice.length; i++) {
                if (dice[i] >= count) {
                    return true;
                }
            }
            return false;
        }
        switch (following) {
            case 3:
                for (int i = 0; i < 3; i++) {
                    if (dice[i] != 0 && dice[i + 1] != 0 && dice[i + 2] != 0) {
                        return true;
                    }
                }
                return false;
            case 4:
                for (int i = 0; i < 2; i++) {
                    if (dice[i] != 0 && dice[i + 1] != 0 && dice[i + 2] != 0 && dice[i + 3] != 0) {
                        return true;
                    }
                }
                return false;
        }
        return false;
    }

    public void setAwake(boolean awake) {
        this.awake = awake;

        if (awake) {
            setAdditionalDice(1);
            switch (rmDiffType) {
                case BUKOWSKI:
                    this.awake = true;
                    this.additionalDice = 1;
                case FW:
                    this.clean_dishes = true;
                    this.additionalDice = 1;
                case HERTA:
                    this.clean_couch = true;
                    this.additionalDice = 1;
                case KRAPOTKE:
                    this.addedDicePlace = 1;
                    this.additionalDice = 1;
                case MU:
                    this.reroll = true;
                    this.additionalDice = 1;
                case MARIA:
                    this.reroll = true;
                    this.additionalDice = 1;
                case OV:
                    this.clean_bathtub = true;
                    this.additionalDice = 1;
                case SARAH:
                    this.addedDicePlace = 1;
                    this.additionalDice = 1;
            }

        } else {
            setAdditionalDice(0);
            switch (rmDiffType) {
                case BUKOWSKI:
                    this.awake = true;
                    this.additionalDice = 1;
                case FW:
                    this.clean_dishes = false;
                    this.additionalDice = 0;
                case HERTA:
                    this.clean_couch = false;
                    this.additionalDice = 0;
                case KRAPOTKE:
                    this.addedDicePlace = 1;
                    this.additionalDice = 0;
                case MU:
                    this.reroll = false;
                    this.additionalDice = 0;
                case MARIA:
                    this.reroll = false;
                    this.additionalDice = 0;
                case OV:
                    this.clean_bathtub = false;
                    this.additionalDice = 0;
                case SARAH:
                    this.addedDicePlace = 1;
                    this.additionalDice = 0;
            }
        }
    }
}
