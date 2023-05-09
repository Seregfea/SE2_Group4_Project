package com.example.se2_group4_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.se2_group4_project.cards.WitzigToDos;
import com.example.se2_group4_project.cards.WitzigWitzigToDos;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WitzigWitzigToDosTest {

    WitzigWitzigToDos witzigWitzigToDos;
    int [] rolledDice = {2,1,2,1,0};

    @Test
    public void witzigWitzigToDosTesting() throws JSONException {

        JSONObject witzigWitzig = new JSONObject("{" + "count: 2" + "number2: 3" + "count2: 1" + "number3: 4" + "count3: 1" +"toDoPenalty: COUCH, TABLEWARE" + "schnapspralinen: 7" + "}");
        witzigWitzigToDos = new WitzigWitzigToDos(witzigWitzig);

        int actualSchnapspraline = witzigWitzigToDos.getSchnapspralinen();
        int expectedSchnapspraline = 7;
        assertEquals(expectedSchnapspraline, actualSchnapspraline);

        int actualCount = witzigWitzigToDos.getCount();
        int expectedCount = 2;
        assertEquals(expectedCount, actualCount);

        String actualNumber2 = witzigWitzigToDos.getNumber2();
        String expectedNumber2 = "3";
        assertEquals(expectedNumber2, actualNumber2);

        int actualCount2 = witzigWitzigToDos.getCount2();
        int expectedCount2 = 1;
        assertEquals(expectedCount2, actualCount2);

        String actualNumber3 = witzigWitzigToDos.getNumber3();
        String expectedNumber3 = "4";
        assertEquals(expectedNumber3, actualNumber3);

        int actualCount3 = witzigWitzigToDos.getCount3();
        int expectedCount3 = 1;
        assertEquals(expectedCount3, actualCount3);

        ArrayList<String> actualToDoPenalty = witzigWitzigToDos.getToDoPenalty();
        ArrayList<String> expectedToDoPenalty = new ArrayList<>();
        expectedToDoPenalty.add("COUCH");
        expectedToDoPenalty.add("TABLEWARE");
        assertEquals(expectedToDoPenalty, actualToDoPenalty);

    }

    @Test
    public void testIsAvailable () {
        assertTrue(witzigWitzigToDos.isAvailable(rolledDice));

        rolledDice[3] = 0;
        assertFalse(witzigWitzigToDos.isAvailable(rolledDice));

    }
}
