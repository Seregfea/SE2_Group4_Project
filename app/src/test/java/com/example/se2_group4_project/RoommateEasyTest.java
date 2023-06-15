package com.example.se2_group4_project;

import com.example.se2_group4_project.cards.RoommateEasy;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoommateEasyTest {
    private RoommateEasy roommateEasy;
    private JSONObject jsonObject;

    @BeforeEach
    void setUp() throws JSONException {
        jsonObject = new JSONObject("{" + "number: 1," + "count: 2" + "}");
        roommateEasy = new RoommateEasy(jsonObject);
    }

    @AfterEach
    void tearDown() {
        jsonObject = null;
        roommateEasy = null;
    }

    @Test
    public void checkIfRoommateIsNotAwakeNormally() {
        Assertions.assertFalse(roommateEasy.isAwake());
    }

    @Test
    public void checkIfRoommateIsAwake() {
        roommateEasy.setAwake(true);
        Assertions.assertTrue(roommateEasy.isAwake());
    }

    @Test
    public void checkGetAdditionalDiceIfIsAwake() {
        roommateEasy.setAwake(true);
        Assertions.assertEquals(1, roommateEasy.getAdditionalDice());
    }

    @Test
    public void checkGetAdditionalDiceIfIsNotAwake() {
        Assertions.assertEquals(0, roommateEasy.getAdditionalDice());
    }

    @Test
    public void checkIfRoommateIsAvailable() {
        int[] diceResult = {4, 1, 5, 1};
    //    Assertions.assertTrue(roommateEasy.isAvailable(diceResult));
    }

    @Test
    public void checkIfRoommateIsNotAvailable() {
        int[] diceResult = {2, 3, 5, 2};
    //    Assertions.assertFalse(roommateEasy.isAvailable(diceResult));
    }

}
