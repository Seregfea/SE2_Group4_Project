package com.example.se2_group4_project;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.se2_group4_project.dices.Dice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DiceMechanismUnitTest {
    private List<Integer> results;
    private List<Integer> testValues;

    @BeforeEach
    void setUp() {
        results = new ArrayList<>();
        testValues = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        results = null;
        testValues = null;
    }

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
        testValues = List.of(1, 2, 3, 4, 5, 6);
        assertEquals(15, Dice.getTotalDiceResult(testValues));
    }

    @Test
    public void sortedCountDiceArrayTest() {
        testValues = List.of(1, 1, 1, 2, 2, 3, 4, 5, 5);
        int[] expectedResult = {3, 2, 1, 1, 2};
        assertArrayEquals(expectedResult, Dice.sortedCountDiceArray(testValues));
    }

    @Test
    public void checkSortedCountDiceArrayWithUnsortedValuesTest() {
        testValues = List.of(3, 2, 5, 2);
        int[] expectedResult = {0, 2, 1, 0, 1};
        assertArrayEquals(expectedResult, Dice.sortedCountDiceArray(testValues));
    }

    @Test
    public void sortedCountDiceArrayWithKangarooTest() {
        testValues = List.of(1, 1, 1, 2, 2, 3, 4, 5, 5, 6, 6);
        int[] expectedResult = {3, 2, 1, 1, 2, 2};
        assertArrayEquals(expectedResult, Dice.sortedCountDiceArrayKangaroo(testValues));
    }

}
