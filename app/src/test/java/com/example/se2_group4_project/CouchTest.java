package com.example.se2_group4_project;

import com.example.se2_group4_project.cards.Couch;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CouchTest {

    Couch couch;

    @BeforeEach
    void setUp() throws JSONException {
        JSONObject couchJson = new JSONObject(
                "{" +
                        "id: 2," +
                "name: Couch-test," +
                "cardType: COUCH," +
                "number: 1," +
                "count: 2," +
                "cardFront: couch_matschig_test," +
                "cardBack: couch_test," +
                "isFront: true" +
                "}"
        );
        couch = new Couch(couchJson);
    }

    @Test
    public void basicTest(){
        int expectedNumber = 1;
        int expectedCount = 2;
        assertEquals(expectedNumber, couch.getNumber());
        assertEquals(expectedCount, couch.getCount());

        expectedNumber = 3;
        expectedCount = 1;
        couch.setNumber(3);
        couch.setCount(1);
        assertEquals(expectedNumber, couch.getNumber());
        assertEquals(expectedCount, couch.getCount());
    }

    @Test
    public void diceSpaceTest(){
        int expectedDiceSpace = 1;
        assertEquals(expectedDiceSpace, couch.getDiceSpace());
        assertFalse(couch.isIncreaseDiceSpace());

        expectedDiceSpace = 3;
        couch.setDiceSpace(3);
        assertEquals(expectedDiceSpace, couch.getDiceSpace());
    }

    @Test
    public void isAvaiableTest(){
        ArrayList <Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(1);
        goodRolledDice.add(1);
        goodRolledDice.add(4);
        goodRolledDice.add(5);

        ArrayList <Integer> badRolledDice = new ArrayList<>();
        badRolledDice.add(5);
        badRolledDice.add(5);
        badRolledDice.add(5);
        badRolledDice.add(5);

        assertTrue(couch.isAvailable(goodRolledDice));
        assertFalse(couch.isAvailable(badRolledDice));

    }

    @Test
    public void integrationTest(){
        int expectedNumber = 1;
        int expectedCount = 2;
        int expectedDiceSpace = 1;
        assertEquals(expectedNumber, couch.getNumber());
        assertEquals(expectedCount, couch.getCount());
        assertFalse(couch.isIncreaseDiceSpace());

        couch.setIncreaseDiceSpace(false);
        assertEquals(expectedDiceSpace, couch.getDiceSpace());
        assertFalse(couch.isIncreaseDiceSpace());
        couch.setIncreaseDiceSpace(true);
        assertEquals(4, couch.getDiceSpace());
        assertTrue(couch.isIncreaseDiceSpace());

        ArrayList <Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(1);
        goodRolledDice.add(1);
        goodRolledDice.add(4);
        goodRolledDice.add(5);
        assertTrue(couch.isAvailable(goodRolledDice));

        couch.setIncreaseDiceSpace(false);
        assertEquals(expectedDiceSpace, couch.getDiceSpace());
        assertFalse(couch.isIncreaseDiceSpace());

        ArrayList <Integer> badRolledDice = new ArrayList<>();
        badRolledDice.add(5);
        badRolledDice.add(5);
        badRolledDice.add(5);
        badRolledDice.add(5);
        assertFalse(couch.isAvailable(badRolledDice));
    }

}
