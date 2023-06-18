package com.example.se2_group4_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.WitzigToDos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class WitzigToDosTest {

    WitzigToDos witzigToDos;
    ArrayList<Integer> rolledDice = new ArrayList<Integer>();
    ArrayList<Integer> rolledDice2 = new ArrayList<Integer>();

    @BeforeEach
    public void setUp() throws JSONException {
        JSONObject witzigObjekt = new JSONObject();

        witzigObjekt.put("number", "1");
        witzigObjekt.put("number2", "2");

        witzigObjekt.put("count", Integer.valueOf(2));
        witzigObjekt.put("count2", Integer.valueOf(1));

        witzigObjekt.put("schnapspralinen", 4);

        JSONArray toDoswitzig = new JSONArray();
        toDoswitzig.put("TABLEWARE");
        ArrayList<String> toDoPenaltyList = new ArrayList<>();
        for (int i = 0; i < toDoswitzig.length(); i++) {
            toDoPenaltyList.add(toDoswitzig.getString(i));
        }
        witzigObjekt.put("toDoPenalty", toDoswitzig);
        witzigToDos = new WitzigToDos(witzigObjekt);

        rolledDice.add(1);
        rolledDice.add(1);
        rolledDice.add(2);
        rolledDice.add(5);
        rolledDice.add(3);

        rolledDice2.add(5);
        rolledDice2.add(5);
        rolledDice2.add(2);
        rolledDice2.add(5);
        rolledDice2.add(3);
    }


    @Test
    public void witzigToDosTesting() {

        int actualSchnapspraline = witzigToDos.getSchnapspralinen();
        int expectedSchnapspraline = 4;
        assertEquals(expectedSchnapspraline, actualSchnapspraline);

        String actualNumber = witzigToDos.getNumber();
        String expectedNumber = "1";
        assertEquals(expectedNumber, actualNumber);

        int actualCount = witzigToDos.getCount();
        int expectedCount = 2;
        assertEquals(expectedCount, actualCount);

        String actualNumber2 = witzigToDos.getNumber2();
        String expectedNumber2 = "2";
        assertEquals(expectedNumber2, actualNumber2);

        int actualCount2 = witzigToDos.getCount2();
        int expectedCount2 = 1;
        assertEquals(expectedCount2, actualCount2);

        ArrayList<String> actualToDoPenalty = witzigToDos.getToDoPenalty();
        ArrayList<String> expectedToDoPenalty = new ArrayList<>();
        expectedToDoPenalty.add("TABLEWARE");
        assertEquals(expectedToDoPenalty, actualToDoPenalty);

    }

    @Test
    public void testIsAvailableTrue () {
        assertTrue(witzigToDos.isAvailable(rolledDice));

    }
    @Test
    public void testIsAvailableFalse () {
        assertFalse(witzigToDos.isAvailable(rolledDice2));
    }

}
