package com.example.se2_group4_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {
    public static List<Integer> rollDice(int numberOfDice) {
        Random random = new Random();
        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < numberOfDice; i++) {
            int result = random.nextInt(6) + 1;
            results.add(result);
        }
        return results;
    }
}
