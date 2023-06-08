package com.example.se2_group4_project.dices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {
    public static ArrayList<Integer> rollDice(int numberOfDice) {
        Random random = new Random();
        ArrayList<Integer> results = new ArrayList<>();

        for (int i = 0; i < numberOfDice; i++) {
            int result = random.nextInt(6) + 1;
            results.add(result);
        }
        return results;
    }

    public static int[] sortedCountDiceArray(List<Integer> dice) {
        int[] countDiceNumbers = new int[5];

        for (int i = 0; i < dice.size(); i++) {
            if (dice.get(i) == 1) {
                countDiceNumbers[0] += 1;
            } else if (dice.get(i) == 2) {
                countDiceNumbers[1] += 1;
            } else if (dice.get(i) == 3) {
                countDiceNumbers[2] += 1;
            } else if (dice.get(i) == 4) {
                countDiceNumbers[3] += 1;
            } else if (dice.get(i) == 5) {
                countDiceNumbers[4] += 1;
            }
        }
        return countDiceNumbers;
    }

    public static int[] sortedCountDiceArrayKangaroo(List<Integer> dice) {
        int[] countDiceNumbers = new int[6];

        for (int i = 0; i < dice.size(); i++) {
            if (dice.get(i) == 1) {
                countDiceNumbers[0] += 1;
            } else if (dice.get(i) == 2) {
                countDiceNumbers[1] += 1;
            } else if (dice.get(i) == 3) {
                countDiceNumbers[2] += 1;
            } else if (dice.get(i) == 4) {
                countDiceNumbers[3] += 1;
            } else if (dice.get(i) == 5) {
                countDiceNumbers[4] += 1;
            } else if (dice.get(i) == 6) {
                countDiceNumbers[5] += 1;
            }
        }
        return countDiceNumbers;
    }

    public static int getTotalDiceResult(List<Integer> results) {
        int sum = 0;

        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) != 6) {
                sum += results.get(i);
            }
        }
        return sum;
    }

}
