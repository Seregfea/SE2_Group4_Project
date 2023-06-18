package com.example.se2_group4_project;

import com.example.se2_group4_project.cards.RoommateDifficult;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RoommateDifficultTest {
    private RoommateDifficult roommateHerta;
    private RoommateDifficult roommateBukowski;
    private RoommateDifficult roommateFriedrich;
    private RoommateDifficult roommateMarc;
    private RoommateDifficult roommateKrapotke;
    private RoommateDifficult roommateOtto;
    private JSONObject jsonObjectHerta;
    private JSONObject jsonObjectBukowski;
    private JSONObject jsonObjectFriedrich;
    private JSONObject jsonObjectMarc;
    private JSONObject jsonObjectKrapotke;
    private JSONObject jsonObjectOtto;


    @BeforeEach
    void setUp() throws JSONException {
        jsonObjectHerta = new JSONObject("{" + "id: 2," + "count: 3," + "following: 0," + "roommateBenefit: clean_couch" + "}");
        jsonObjectBukowski = new JSONObject("{" + "id: 0," + "count: 0," + "following: 4," + "roommateBenefit: does_not_sleep" + "}");
        jsonObjectFriedrich = new JSONObject("{" + "id: 1," + "count: 0," + "following: 3," + "roommateBenefit: clean_dishes" + "}");
        jsonObjectMarc = new JSONObject("{" + "id: 4," + "count: 0," + "following: 3," + "roommateBenefit: reroll" + "}");
        jsonObjectKrapotke = new JSONObject("{" + "id: 3," + "count: 3," + "following: 0," + "roommateBenefit: park_dice" + "}");
        jsonObjectOtto = new JSONObject("{" + "id: 6," + "count: 0," + "following: 3," + "roommateBenefit: clean_bathtub" + "}");

        roommateHerta = new RoommateDifficult(jsonObjectHerta);
        roommateBukowski = new RoommateDifficult(jsonObjectBukowski);
        roommateFriedrich = new RoommateDifficult(jsonObjectFriedrich);
        roommateMarc = new RoommateDifficult(jsonObjectMarc);
        roommateKrapotke = new RoommateDifficult(jsonObjectKrapotke);
        roommateOtto = new RoommateDifficult(jsonObjectOtto);
    }

    @AfterEach
    void tearDown() {
        jsonObjectHerta = null;
        jsonObjectBukowski = null;
        jsonObjectFriedrich = null;
        jsonObjectMarc = null;
        jsonObjectKrapotke = null;
        jsonObjectOtto = null;

        roommateHerta = null;
        roommateBukowski = null;
        roommateFriedrich = null;
        roommateMarc = null;
        roommateKrapotke = null;
        roommateOtto = null;
    }

    @Test
    public void checkIfRoommatesAreNotAwakeNormally() {
        Assertions.assertFalse(roommateHerta.isAwake());
        Assertions.assertFalse(roommateBukowski.isAwake());
        Assertions.assertFalse(roommateFriedrich.isAwake());
        Assertions.assertFalse(roommateMarc.isAwake());
        Assertions.assertFalse(roommateKrapotke.isAwake());
        Assertions.assertFalse(roommateOtto.isAwake());
    }

    @Test
    public void checkIfRoommateHertaIsAwake() {
        roommateHerta.setAwake(true);
        Assertions.assertTrue(roommateHerta.isAwake());
        Assertions.assertEquals(2, roommateHerta.getAdditionalDice());
        // Assertions.assertTrue(roommateHerta.isClean_couch());
    }

    @Test
    public void checkIfRoommateBukowskiIsAwake() {
        roommateBukowski.setAwake(true);
        Assertions.assertTrue(roommateBukowski.isAwake());
        Assertions.assertEquals(3, roommateBukowski.getAdditionalDice());
        // Assertions.assertTrue(roommateBukowski.isDoes_not_sleep());
    }

    @Test
    public void checkIfRoommateFriedrichIsAwake() {
        roommateFriedrich.setAwake(true);
        Assertions.assertTrue(roommateFriedrich.isAwake());
        Assertions.assertEquals(2, roommateFriedrich.getAdditionalDice());
        // Assertions.assertTrue(roommateFriedrich.isClean_dishes());
        // Eigenschaft muss false sein, da der Roommate diese gar nicht besitzt
        Assertions.assertFalse(roommateFriedrich.isClean_couch());
    }

    @Test
    public void checkIfRoommateMarcIsAwake() {
        roommateMarc.setAwake(true);
        Assertions.assertTrue(roommateMarc.isAwake());
        Assertions.assertEquals(2, roommateMarc.getAdditionalDice());
        // Assertions.assertTrue(roommateMarc.isReroll());
    }

    @Test
    public void checkIfRoommateKrapotkeIsAwake() {
        roommateKrapotke.setAwake(true);
        Assertions.assertTrue(roommateKrapotke.isAwake());
        Assertions.assertEquals(2, roommateKrapotke.getAdditionalDice());
        // Assertions.assertEquals(1, roommateKrapotke.getAddedDicePlace());
    }

    @Test
    public void checkIfRoommateOttoIsAwake() {
        roommateOtto.setAwake(true);
        Assertions.assertTrue(roommateOtto.isAwake());
        Assertions.assertEquals(2, roommateOtto.getAdditionalDice());
        // Assertions.assertTrue(roommateOtto.isClean_bathtub());
    }

    @Test
    public void setRoommateHertaToAsleep() {
        roommateHerta.setAwake(false);
        Assertions.assertFalse(roommateHerta.isAwake());
        Assertions.assertEquals(1, roommateHerta.getAdditionalDice());
        Assertions.assertFalse(roommateHerta.isClean_couch());
    }

    @Test
    public void setRoommateBukowskiToAsleep() {
        roommateBukowski.setAwake(false);
        // awake ist trotzdem auf true und der zusätzliche Würfel bleibt - Bukowski kann nicht schlafen gelegt werden
        Assertions.assertTrue(roommateBukowski.isAwake());
        // Assertions.assertEquals(1, roommateBukowski.getAdditionalDice());
    }

    @Test
    public void setRoommateFriedrichToAsleep() {
        roommateFriedrich.setAwake(false);
        Assertions.assertFalse(roommateFriedrich.isClean_dishes());
    }

    @Test
    public void setRoommateMarcToAsleep() {
        roommateMarc.setAwake(false);
        Assertions.assertFalse(roommateMarc.isReroll());
    }

    @Test
    public void setRoommateKrapotkeToAsleep() {
        roommateKrapotke.setAwake(false);
        Assertions.assertEquals(0, roommateKrapotke.getAddedDicePlace());
    }

    @Test
    public void setRoommateOttoToAsleep() {
        roommateOtto.setAwake(false);
        Assertions.assertFalse(roommateOtto.isClean_bathtub());
    }

    @Test
    public void checkIfRoommateIsAvailableWithCount() {
        ArrayList<Integer> diceResult = new ArrayList<>();
        diceResult.add(1);
        diceResult.add(5);
        diceResult.add(4);
        diceResult.add(2);
        diceResult.add(5);
        diceResult.add(5);
        Assertions.assertTrue(roommateHerta.isAvailable(diceResult));
    }

    @Test
    public void checkIfRoommateIsNotAvailableWithCount() {
        ArrayList<Integer> diceResult = new ArrayList<>();
        diceResult.add(1);
        diceResult.add(5);
        diceResult.add(4);
        diceResult.add(2);
        diceResult.add(5);
        Assertions.assertFalse(roommateHerta.isAvailable(diceResult));
    }

    @Test
    public void checkIfRoommateIsAvailableWithFollowing() {
        ArrayList<Integer> diceResult = new ArrayList<>();
        diceResult.add(2);
        diceResult.add(3);
        diceResult.add(4);
        diceResult.add(5);
        Assertions.assertTrue(roommateBukowski.isAvailable(diceResult));
    }

    @Test
    public void checkIfRoommateIsAvailableWithFollowingUnsorted() {
        ArrayList<Integer> diceResult = new ArrayList<>();
        diceResult.add(3);
        diceResult.add(4);
        diceResult.add(2);
        diceResult.add(1);
        Assertions.assertTrue(roommateBukowski.isAvailable(diceResult));
    }

    @Test
    public void checkIfRoommateIsNotAvailableWithFollowing() {
        ArrayList<Integer> diceResult = new ArrayList<>();
        diceResult.add(2);
        diceResult.add(5);
        diceResult.add(4);
        diceResult.add(2);
        diceResult.add(5);
        Assertions.assertFalse(roommateBukowski.isAvailable(diceResult));
    }

}
