package com.example.se2_group4_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.se2_group4_project.cards.Me;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MeTest {

    @Test
    public void meBlueTest() throws JSONException {
        ArrayList<Integer> badRolledDiceAvailable = new ArrayList<>();
        badRolledDiceAvailable.add(2);
        badRolledDiceAvailable.add(3);

        ArrayList<Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(2);
        goodRolledDice.add(2);
        goodRolledDice.add(2);

        JSONObject me_blue = new JSONObject(
                "{"+
                        "number: 2,"+
                        "count: 2,"+
                        "}"
        );
        Me meBlue = new Me(me_blue);
        assertEquals(2, meBlue.getNumber());
        assertEquals(2, meBlue.getCount());
        assertTrue(meBlue.isAvailable(goodRolledDice));
        assertFalse(meBlue.isAvailable(badRolledDiceAvailable));

        meBlue.setDiceSpaceMe(false);
        assertFalse(meBlue.isDiceSpaceMe());
        assertEquals(1, meBlue.getAmountDiceMe());
        meBlue.setDiceSpaceMe(true);
        assertTrue(meBlue.isDiceSpaceMe());
        assertEquals(4, meBlue.getAmountDiceMe());
    }
    @Test
    public void meGreenTest() throws JSONException {
        ArrayList<Integer> badRolledDiceAvailable = new ArrayList<>();
        badRolledDiceAvailable.add(2);
        badRolledDiceAvailable.add(3);

        ArrayList<Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(5);
        goodRolledDice.add(5);
        goodRolledDice.add(5);

        JSONObject me_Green = new JSONObject(
                "{"+
                        "number: 5,"+
                        "count: 2,"+
                        "}"
        );
        Me meGreen = new Me(me_Green);
        assertEquals(5, meGreen.getNumber());
        assertEquals(2, meGreen.getCount());
        assertTrue(meGreen.isAvailable(goodRolledDice));
        assertFalse(meGreen.isAvailable(badRolledDiceAvailable));

        meGreen.setDiceSpaceMe(false);
        assertFalse(meGreen.isDiceSpaceMe());
        assertEquals(1, meGreen.getAmountDiceMe());
        meGreen.setDiceSpaceMe(true);
        assertTrue(meGreen.isDiceSpaceMe());
        assertEquals(4, meGreen.getAmountDiceMe());
    }
    @Test
    public void meOrangeTest() throws JSONException {
        ArrayList<Integer> badRolledDiceAvailable = new ArrayList<>();
        badRolledDiceAvailable.add(2);
        badRolledDiceAvailable.add(3);

        ArrayList<Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(4);
        goodRolledDice.add(4);
        goodRolledDice.add(4);

        JSONObject me_Orange = new JSONObject(
                "{"+
                        "number: 4,"+
                        "count: 2,"+
                        "}"
        );
        Me meOrange = new Me(me_Orange);
        assertEquals(4, meOrange.getNumber());
        assertEquals(2, meOrange.getCount());
        assertTrue(meOrange.isAvailable(goodRolledDice));
        assertFalse(meOrange.isAvailable(badRolledDiceAvailable));

        meOrange.setDiceSpaceMe(false);
        assertFalse(meOrange.isDiceSpaceMe());
        assertEquals(1, meOrange.getAmountDiceMe());
        meOrange.setDiceSpaceMe(true);
        assertTrue(meOrange.isDiceSpaceMe());
        assertEquals(4, meOrange.getAmountDiceMe());
    }
    @Test
    public void meTealTest() throws JSONException {
        ArrayList<Integer> badRolledDiceAvailable = new ArrayList<>();
        badRolledDiceAvailable.add(2);
        badRolledDiceAvailable.add(3);

        ArrayList<Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(1);
        goodRolledDice.add(1);
        goodRolledDice.add(1);

        JSONObject me_Teal = new JSONObject(
                "{"+
                        "number: 1,"+
                        "count: 2,"+
                        "}"
        );
        Me meTeal = new Me(me_Teal);
        assertEquals(1, meTeal.getNumber());
        assertEquals(2, meTeal.getCount());
        assertTrue(meTeal.isAvailable(goodRolledDice));
        assertFalse(meTeal.isAvailable(badRolledDiceAvailable));

        meTeal.setDiceSpaceMe(false);
        assertFalse(meTeal.isDiceSpaceMe());
        assertEquals(1, meTeal.getAmountDiceMe());
        meTeal.setDiceSpaceMe(true);
        assertTrue(meTeal.isDiceSpaceMe());
        assertEquals(4, meTeal.getAmountDiceMe());
    }
}
