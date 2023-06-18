package com.example.se2_group4_project;

import com.example.se2_group4_project.cards.RoommateEasy;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RoommateEasyTest {
    private RoommateEasy roommateEasy;
    private JSONObject jsonObject;

    @BeforeEach
    void setUp() throws JSONException {
        jsonObject = new JSONObject("{" + "id: 1," + "number: 1," + "count: 2" + "}");
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
        roommateEasy.setAwake(false);
        Assertions.assertEquals(-1, roommateEasy.getAdditionalDice());
    }

    @Test
    public void checkIfRoommateIsAvailable() {
        ArrayList<Integer> diceResult = new ArrayList<>();
        diceResult.add(2);
        diceResult.add(1);
        diceResult.add(3);
        diceResult.add(1);
        Assertions.assertTrue(roommateEasy.isAvailable(diceResult));
    }

    @Test
    public void checkIfRoommateIsNotAvailable() {
        ArrayList<Integer> diceResult = new ArrayList<>();
        diceResult.add(2);
        diceResult.add(5);
        diceResult.add(3);
        diceResult.add(2);
        Assertions.assertFalse(roommateEasy.isAvailable(diceResult));
    }

}
