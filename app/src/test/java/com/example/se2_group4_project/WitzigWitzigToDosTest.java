package com.example.se2_group4_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.WitzigToDos;
import com.example.se2_group4_project.cards.WitzigWitzigToDos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class WitzigWitzigToDosTest {

    WitzigWitzigToDos witzigWitzigToDos;
    ArrayList<Integer> rolledDice = new ArrayList<Integer>();
    ArrayList<Integer> rolledDice2 = new ArrayList<Integer>();

    @BeforeEach
    public void setUp() throws JSONException {
        JSONObject witzigWitzigObjekt = new JSONObject();

        witzigWitzigObjekt.put("number", "1");
        witzigWitzigObjekt.put("number2", "2");
        witzigWitzigObjekt.put( "number3", "3");
        witzigWitzigObjekt.put( "number4", "4");

        witzigWitzigObjekt.put("count", Integer.valueOf(2));
        witzigWitzigObjekt.put("count2", Integer.valueOf(1));
        witzigWitzigObjekt.put("count3", Integer.valueOf(2));
        witzigWitzigObjekt.put("count4", Integer.valueOf(1));

        witzigWitzigObjekt.put("schnapspralinen", 4);

        //JSONArray toDosWitzigWitzig = new JSONArray();
        //toDosWitzigWitzig.put("TABLEWARE");
        //toDosWitzigWitzig.put("BATHTUB");
        //ArrayList<String> toDoPenaltyList = new ArrayList<>();
        //for (int i = 0; i < toDosWitzigWitzig.length(); i++) {
        //    toDoPenaltyList.add(toDosWitzigWitzig.getString(i));
        //}
        //witzigWitzigObjekt.put("toDoPenalty", toDosWitzigWitzig);
        witzigWitzigToDos = new WitzigWitzigToDos(witzigWitzigObjekt);

        rolledDice.add(1);
        rolledDice.add(1);
        rolledDice.add(2);
        rolledDice.add(3);
        rolledDice.add(3);
        rolledDice.add(4);

        rolledDice2.add(5);
        rolledDice2.add(5);
        rolledDice2.add(2);
        rolledDice2.add(5);
        rolledDice2.add(3);
        rolledDice2.add(4);
    }


    @Test
    public void witzigToDosTesting() {

        int actualSchnapspraline = witzigWitzigToDos.getSchnapspralinen();
        int expectedSchnapspraline = 4;
        assertEquals(expectedSchnapspraline, actualSchnapspraline);

        String actualNumber = witzigWitzigToDos.getNumber();
        String expectedNumber = "1";
        assertEquals(expectedNumber, actualNumber);

        int actualCount = witzigWitzigToDos.getCount();
        int expectedCount = 2;
        assertEquals(expectedCount, actualCount);

        String actualNumber2 = witzigWitzigToDos.getNumber2();
        String expectedNumber2 = "2";
        assertEquals(expectedNumber2, actualNumber2);

        int actualCount2 = witzigWitzigToDos.getCount2();
        int expectedCount2 = 1;
        assertEquals(expectedCount2, actualCount2);

        String actualNumber3 = witzigWitzigToDos.getNumber3();
        String expectedNumber3 = "3";
        assertEquals(expectedNumber3, actualNumber3);

        int actualCount3 = witzigWitzigToDos.getCount3();
        int expectedCount3 = 2;
        assertEquals(expectedCount3, actualCount3);

        String actualNumber4 = witzigWitzigToDos.getNumber4();
        String expectedNumber4 = "4";
        assertEquals(expectedNumber4, actualNumber4);

        int actualCount4 = witzigWitzigToDos.getCount4();
        int expectedCount4 = 1;
        assertEquals(expectedCount4, actualCount4);

        //ArrayList<String> actualToDoPenalty = witzigWitzigToDos.getToDoPenalty();
        //ArrayList<String> expectedToDoPenalty = new ArrayList<>();
        //expectedToDoPenalty.add("TABLEWARE");
        //expectedToDoPenalty.add("BATHTUB");
        //assertEquals(expectedToDoPenalty, actualToDoPenalty);

    }

    @Test
    public void testIsAvailableTrue () {
        assertTrue(witzigWitzigToDos.isAvailable(rolledDice));

    }
    @Test
    public void testIsAvailableFalse () {
        assertFalse(witzigWitzigToDos.isAvailable(rolledDice2));
    }
}
