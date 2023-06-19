package com.example.se2_group4_project.cards;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class RoommateDifficult {
    private int id;
    private String name;
    private CardType cardType;
    private String roommateBenefit;
    private String cardFront;
    private String cardBack;
    private boolean isFront;
    private int following;
    private int awakeCount = 0;
    private int additionalDice = 1;
    private int addedDicePlace = 0;
    private int count;

    private RoommateDiffType rmDiffType;
    private boolean awake = false;
    private boolean does_not_sleep = false;
    private boolean clean_dishes = false;
    private boolean clean_couch = false;
    private boolean reroll = false;
    private boolean clean_bathtub = false;

    private String benefit;

    public RoommateDifficult(){
        super();
    }
    public RoommateDifficult(JSONObject roommateDifficult) throws JSONException {
        this.id = roommateDifficult.getInt("id");
        this.count = roommateDifficult.getInt("count");
        this.following = roommateDifficult.getInt("following");

        switch (roommateDifficult.getString("roommateBenefit")) {
            case "does_not_sleep":
                this.benefit = "badewanne sauber";
                this.rmDiffType = RoommateDiffType.BUKOWSKI;
                break;
            case "clean_dishes":
                this.benefit = "geschirr sauber";
                this.rmDiffType = RoommateDiffType.FW;
                break;
            case "clean_couch":
                this.benefit = "couch sauber";
                this.rmDiffType = RoommateDiffType.HERTA;
                break;
            case "reroll":
                this.benefit = "alles sauber";
                this.rmDiffType = RoommateDiffType.MU;
                break;
            case "clean_bathtub":
                this.benefit = "alle wach";
                this.rmDiffType = RoommateDiffType.OV;
                break;
            case "park_dice":
                this.benefit = "ich wach";
                this.rmDiffType = RoommateDiffType.SARAH;
                break;
            default:
                System.out.println("No roommate benefits");
                break;
        }
    }
    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
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

    public boolean isDoes_not_sleep() {
        return does_not_sleep;
    }

    public void setDoes_not_sleep(boolean does_not_sleep) {
        this.does_not_sleep = does_not_sleep;
    }

    public boolean isClean_dishes() {
        return clean_dishes;
    }

    public void setClean_dishes(boolean clean_dishes) {
        this.clean_dishes = clean_dishes;
    }

    public boolean isClean_couch() {
        return clean_couch;
    }

    public void setClean_couch(boolean clean_couch) {
        this.clean_couch = clean_couch;
    }

    public boolean isReroll() {
        return reroll;
    }

    public void setReroll(boolean reroll) {
        this.reroll = reroll;
    }

    public boolean isClean_bathtub() {
        return clean_bathtub;
    }

    public void setClean_bathtub(boolean clean_bathtub) {
        this.clean_bathtub = clean_bathtub;
    }

    public boolean isAwake() {
        return awake;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public RoommateDiffType getRmDiffType() {
        return rmDiffType;
    }

    public void setRmDiffType(RoommateDiffType rmDiffType) {
        this.rmDiffType = rmDiffType;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public int getAwakeCount() {
        return awakeCount;
    }

    public void setAwakeCount(int awakeCount) {
        this.awakeCount = awakeCount;
    }

    public boolean isAvailable(ArrayList<Integer> rolledDice) {
        if (count > 0) {
            if (rolledDice.size() < 3) {
                return false;
            }
            for (int i = 0; i < rolledDice.size(); i++) {
                int counter = 1;

                for (int j = i + 1; j < rolledDice.size(); j++) {
                    if (Objects.equals(rolledDice.get(j), rolledDice.get(i))) {
                        counter++;
                    }
                    if (counter == count) {
                        return true;
                    }
                }
            }
            return false;
        }

        if (following > 0) {
            if (rolledDice.size() < 4) {
                return false;
            }
            int counter = 1;
            Collections.sort(rolledDice);

            for (int i = 0; i < rolledDice.size() - 1; i++) {
                if (rolledDice.get(i) + 1 == rolledDice.get(i + 1)) {
                    counter++;

                    if (counter == following) {
                        return true;
                    }
                } else {
                    counter = 1;
                }
            }
            return false;
        }
        return false;
    }

    public int setAwake(boolean awake) {
        this.awake = awake;

        if (isAwake()) {
            switch (rmDiffType) {
                case BUKOWSKI:
                    if (getAwakeCount() > 0) {
                        return 0;
                    }
                    setAdditionalDice(3);
                    setAwakeCount(1);
                    return getAdditionalDice();
                case FW:
                case OV:
                case HERTA:
                case KRAPOTKE:
                case SARAH:
                case MU:
                case MARIA:
                    setAdditionalDice(2);
                    return getAdditionalDice();
            }

        } else {
            setAdditionalDice(0);
            switch (rmDiffType) {
                case BUKOWSKI:
                    this.awake = true;
                    return 0;
                case FW:
                case HERTA:
                case KRAPOTKE:
                case MU:
                case MARIA:
                case OV:
                case SARAH:
                    this.awake = false;
                    this.additionalDice = 1;
                    return getAdditionalDice();
            }
        }
        return getAdditionalDice();
    }
}
