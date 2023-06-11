package com.example.se2_group4_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.se2_group4_project.cards.WitzigToDos;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class WitzigToDosTest {

    WitzigToDos witzigToDos;
    ArrayList<Integer> rolledDice = new ArrayList<Integer>();


    @Test
    public void witzigToDosTesting() throws JSONException {

        JSONObject witzig = new JSONObject("{" + "number: 1" + "count: 2" + "toDoPenalty: TABLEWARE," + "schnapspralinen: 4" + "}");
        witzigToDos = new WitzigToDos(witzig);

        int actualSchnapspraline = witzigToDos.getSchnapspralinen();
        int expectedSchnapspraline = 4;
        assertEquals(expectedSchnapspraline, actualSchnapspraline);

        String actualNumber = witzigToDos.getNumber();
        String expectedNumber = "1";
        assertEquals(expectedNumber, actualNumber);

        int actualCount = witzigToDos.getCount();
        int expectedCount = 2;
        assertEquals(expectedCount, actualCount);

        ArrayList<String> actualToDoPenalty = witzigToDos.getToDoPenalty();
        ArrayList<String> expectedToDoPenalty = new ArrayList<>();
        expectedToDoPenalty.add("TABLEWARE");
        assertEquals(expectedToDoPenalty, actualToDoPenalty);

    }

    @Test
    public void testIsAvailable () {
        rolledDice.add(1);
        rolledDice.add(1);
        rolledDice.add(1);
        rolledDice.add(1);
        rolledDice.add(1);

        assertFalse(witzigToDos.isAvailable(rolledDice));

        rolledDice.set(0,2);
        assertTrue(witzigToDos.isAvailable(rolledDice));

    }
}
