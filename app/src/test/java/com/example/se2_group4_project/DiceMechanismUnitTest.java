package com.example.se2_group4_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceMechanismUnitTest {
    private List<Integer> results = new ArrayList<>();

    @Test
    public void rollDiceTest_checkCorrectSize() {
        results = Dice.rollDice(2);
        assertEquals(2, results.size());
    }

    @Test
    public void rollDiceTest_checkCorrectResults() {
        results = Dice.rollDice(2);
        assertTrue(results.get(0) > 0 && results.get(0) < 7 && results.get(1) > 0 && results.get(1) < 7);
    }

    @Test
    public void getTotalDiceResult_checkResult() {
        List<Integer> testValues = List.of(1, 2, 3, 4, 5, 6);
        assertEquals(15, Dice.getTotalDiceResult(testValues));
    }

}
