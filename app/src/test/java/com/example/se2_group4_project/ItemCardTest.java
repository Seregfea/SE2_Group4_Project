package com.example.se2_group4_project;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.se2_group4_project.cards.Item;

public class ItemCardTest {
    private Item itemCard;
    private JSONObject jsonObject;

    @BeforeEach
    void setUp() throws JSONException {
        jsonObject = new JSONObject("{" + "number: 1," + "count: 2" + "}");
        itemCard = new Item(jsonObject);
    }

    @AfterEach
    void tearDown(){
        jsonObject = null;
        itemCard = null;
    }

    @Test
    public void checkIfItemCardIsAvailableJojo(){
        int[] diceResult = {1, 1, 1};
        Assertions.assertTrue(itemCard.isAvailable(diceResult));
    }

    @Test
    public void checkIfItemCardIsAvailableKuechenradio(){
        int[] diceResult = {2, 2, 2};
        Assertions.assertTrue(itemCard.isAvailable(diceResult));
    }

    @Test
    public void checkIfItemCardIsAvailablePfanneunco(){
        int[] diceResult = {5, 5, 5};
        Assertions.assertTrue(itemCard.isAvailable(diceResult));
    }

    @Test
    public void checkIfItemCardIsAvailableWasserbombe(){
        int[] diceResult = {4, 4, 4};
        Assertions.assertTrue(itemCard.isAvailable(diceResult));
    }

    @Test
    public void checkIfItemCardIsNotAvailableJojo(){
        int[] diceResult = {4, 3, 5};
        Assertions.assertTrue(itemCard.isAvailable(diceResult));
    }

    @Test
    public void checkIfItemCardIsNotAvailableKuechenradio(){
        int[] diceResult = {2, 1, 4};
        Assertions.assertTrue(itemCard.isAvailable(diceResult));
    }

    @Test
    public void checkIfItemCardIsNotAvailablePfannenunCo(){
        int[] diceResult = {1, 1, 2};
        Assertions.assertTrue(itemCard.isAvailable(diceResult));
    }

    @Test
    public void checkIfItemCardIsNotAvailableWasserbombe(){
        int[] diceResult = {2, 4, 1};
        Assertions.assertTrue(itemCard.isAvailable(diceResult));
    }

    @Test
    public void testItemCard() throws JSONException {

    }
}
