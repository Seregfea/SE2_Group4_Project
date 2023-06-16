package com.example.se2_group4_project;

import com.example.se2_group4_project.cards.Badewanne;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class BadewanneTest {

    Badewanne badewanne;

    @BeforeEach
    void setUp() throws JSONException {
        JSONObject badewanneJSON = new JSONObject(
                "{" +
                        "id: 0," +
                        "name: Badewanne-test," +
                        "cardType: BATHTUB," +
                        "number: 5," +
                        "count: 2," +
                        "cardFront: bad_dreckig_test," +
                        "cardBack: bad_sauber_test," +
                        "isFront: true" +
                        "}"
        );
        badewanne = new Badewanne(badewanneJSON);
    }

    @Test
    public void basicTest(){
        int expectedNumber = 5;
        int expectedCount = 2;
        assertEquals(expectedNumber, badewanne.getNumber());
        assertEquals(expectedCount, badewanne.getCount());
        assertFalse(badewanne.isKanguru());

        expectedNumber = 2;
        expectedCount = 3;
        badewanne.setNumber(2);
        badewanne.setCount(3);
        badewanne.setKanguru(true);
        assertEquals(expectedNumber, badewanne.getNumber());
        assertEquals(expectedCount, badewanne.getCount());
        assertTrue(badewanne.isKanguru());
    }

    @Test
    public void integrationTest(){
        ArrayList<Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(5);
        goodRolledDice.add(5);
        goodRolledDice.add(3);
        goodRolledDice.add(4);
        assertTrue(badewanne.isAvailable(goodRolledDice));

        ArrayList<Integer> badRolledDice = new ArrayList<>();
        badRolledDice.add(2);
        badRolledDice.add(4);
        badRolledDice.add(3);
        assertFalse(badewanne.isAvailable(badRolledDice));
    }

    @Test
    public void negativeTest(){
        ArrayList<Integer> emptyRolledDice = new ArrayList<>();
        assertFalse(badewanne.isAvailable(emptyRolledDice));
        assertThrows(NullPointerException.class, ()->{
           badewanne.isAvailable(null);
        });
    }
}
