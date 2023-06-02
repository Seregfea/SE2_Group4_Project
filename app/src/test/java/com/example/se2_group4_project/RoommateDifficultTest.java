package com.example.se2_group4_project;

import com.example.se2_group4_project.cards.RoommateDifficult;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoommateDifficultTest {
    private RoommateDifficult roommateHerta;
    private RoommateDifficult roommateBukowski;
    private JSONObject jsonObjectHerta;
    private JSONObject jsonObjectBukowski;


    @BeforeEach
    void setUp() throws JSONException {
        jsonObjectHerta = new JSONObject("{" + "count: 3," + "following: 0," + "roommateBenefit: clean_couch" + "}");
        jsonObjectBukowski = new JSONObject("{" + "count: 0," + "following: 4," + "roommateBenefit: does_not_sleep" + "}");
        roommateHerta = new RoommateDifficult(jsonObjectHerta);
        roommateBukowski = new RoommateDifficult(jsonObjectBukowski);
    }

    @Test
    public void checkIfRoommatesAreNotAwakeNormally() {
        Assertions.assertFalse(roommateHerta.isAwake());
        Assertions.assertFalse(roommateBukowski.isAwake());
    }

    @Test
    public void checkIfRoommatesAreAwake() {
        roommateHerta.setAwake(true);
        roommateBukowski.setAwake(true);

        Assertions.assertTrue(roommateHerta.isAwake());
        Assertions.assertEquals(1, roommateHerta.getAdditionalDice());
        Assertions.assertTrue(roommateHerta.isClean_couch());

        Assertions.assertTrue(roommateBukowski.isAwake());
        Assertions.assertEquals(1, roommateBukowski.getAdditionalDice());
        Assertions.assertTrue(roommateBukowski.isDoes_not_sleep());
    }

    @Test
    public void setRoommateHertaToAsleep() {
        roommateHerta.setAwake(false);
        Assertions.assertFalse(roommateHerta.isAwake());
        Assertions.assertEquals(0, roommateHerta.getAdditionalDice());
        Assertions.assertFalse(roommateHerta.isClean_couch());
    }

    @Test
    public void setRoommateBukowskiToAsleep() {
        roommateBukowski.setAwake(false);
        // awake ist trotzdem auf true und der zusätzliche Würfel bleibt - Bukowski kann nicht schlafen gelegt werden
        Assertions.assertTrue(roommateBukowski.isAwake());
        Assertions.assertEquals(1, roommateBukowski.getAdditionalDice());
    }

    @Test
    public void checkIfRoommateIsAvailableWithCount() {
        int[] diceResult = {1, 5, 4, 2, 5, 5};
        Assertions.assertTrue(roommateHerta.isAvailable(diceResult));
    }

    @Test
    public void checkIfRoommateIsNotAvailableWithCount() {
        int[] diceResult = {1, 5, 4, 2, 5};
        Assertions.assertFalse(roommateHerta.isAvailable(diceResult));
    }

    @Test
    public void checkIfRoommateIsAvailableWithFollowing() {
        int[] diceResult = {2, 3, 4, 5};
        Assertions.assertTrue(roommateBukowski.isAvailable(diceResult));
    }

    @Test
    public void checkIfRoommateIsAvailableWithFollowingUnsorted() {
        int[] diceResult = {3, 4, 2, 1};
        Assertions.assertTrue(roommateBukowski.isAvailable(diceResult));
    }

    @Test
    public void checkIfRoommateIsNotAvailableWithFollowing() {
        int[] diceResult = {2, 5, 4, 2, 5};
        Assertions.assertFalse(roommateBukowski.isAvailable(diceResult));
    }

}
