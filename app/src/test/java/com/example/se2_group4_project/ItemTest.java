package com.example.se2_group4_project;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.se2_group4_project.cards.Item;

import java.util.ArrayList;

public class ItemTest {

    @Test
    public void testJOJOItem() throws JSONException {
        ArrayList<Integer> badRolledDiceAvailable = new ArrayList<>();
        badRolledDiceAvailable.add(2);
        badRolledDiceAvailable.add(3);

        ArrayList<Integer> badRolledDiceSteal = new ArrayList<>();
        badRolledDiceSteal.add(1);
        badRolledDiceSteal.add(2);
        badRolledDiceSteal.add(3);

        ArrayList<Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(1);
        goodRolledDice.add(1);
        goodRolledDice.add(1);

        JSONObject item_JoJo = new JSONObject(
                "{"+
                        "number: 1,"+
                        "count: 2,"+
                        "stealCard: 3,"+
                        "itemBenefit: Couch matschig" +
                        "}"
        );
        Item itemJoJo = new Item(item_JoJo);
        assertEquals(1, itemJoJo.getNumber());
        assertEquals(2, itemJoJo.getCount());
        assertEquals(3, itemJoJo.getStealCard());
        assertTrue(itemJoJo.isCouchMatschig());
        assertFalse(itemJoJo.isBadewanneDreckig());
        assertFalse(itemJoJo.isGeschirrDreckig());

        assertTrue(itemJoJo.isAvailable(goodRolledDice));
        assertFalse(itemJoJo.isAvailable(badRolledDiceAvailable));
        assertTrue(itemJoJo.isStealable(goodRolledDice));
        assertFalse(itemJoJo.isStealable(badRolledDiceSteal));
        assertThrows(NullPointerException.class, ()->{
            itemJoJo.isAvailable(null);
        });
        assertThrows(NullPointerException.class, ()->{
            itemJoJo.isStealable(null);
        });
    }
    @Test
    public void testDeckeItem() throws JSONException {
        ArrayList<Integer> badRolledDiceAvailable = new ArrayList<>();
        badRolledDiceAvailable.add(2);
        badRolledDiceAvailable.add(3);

        ArrayList<Integer> badRolledDiceSteal = new ArrayList<>();
        badRolledDiceSteal.add(1);
        badRolledDiceSteal.add(2);
        badRolledDiceSteal.add(3);

        ArrayList<Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(3);
        goodRolledDice.add(3);
        goodRolledDice.add(3);

        JSONObject item_Decke = new JSONObject(
                "{"+
                        "number: 3,"+
                        "count: 2,"+
                        "stealCard: 3,"+
                        "itemBenefit: Couch matschig" +
                        "}"
        );
        Item itemDecke = new Item(item_Decke);
        assertEquals(3, itemDecke.getNumber());
        assertEquals(2, itemDecke.getCount());
        assertEquals(3, itemDecke.getStealCard());
        assertTrue(itemDecke.isCouchMatschig());
        assertFalse(itemDecke.isBadewanneDreckig());
        assertFalse(itemDecke.isGeschirrDreckig());

        assertTrue(itemDecke.isAvailable(goodRolledDice));
        assertFalse(itemDecke.isAvailable(badRolledDiceAvailable));
        assertTrue(itemDecke.isStealable(goodRolledDice));
        assertFalse(itemDecke.isStealable(badRolledDiceSteal));
        assertThrows(NullPointerException.class, ()->{
            itemDecke.isAvailable(null);
        });
        assertThrows(NullPointerException.class, ()->{
            itemDecke.isStealable(null);
        });
    }
    @Test
    public void testWasserbombeItem() throws JSONException {
        ArrayList<Integer> badRolledDiceAvailable = new ArrayList<>();
        badRolledDiceAvailable.add(2);
        badRolledDiceAvailable.add(3);

        ArrayList<Integer> badRolledDiceSteal = new ArrayList<>();
        badRolledDiceSteal.add(1);
        badRolledDiceSteal.add(2);
        badRolledDiceSteal.add(3);

        ArrayList<Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(4);
        goodRolledDice.add(4);
        goodRolledDice.add(4);

        JSONObject item_Wasserbombe = new JSONObject(
                "{"+
                        "number: 4,"+
                        "count: 2,"+
                        "stealCard: 3,"+
                        "itemBenefit: Badewanne dreckig" +
                        "}"
        );
        Item itemWasserbombe = new Item(item_Wasserbombe);
        assertEquals(4, itemWasserbombe.getNumber());
        assertEquals(2, itemWasserbombe.getCount());
        assertEquals(3, itemWasserbombe.getStealCard());
        assertFalse(itemWasserbombe.isCouchMatschig());
        assertTrue(itemWasserbombe.isBadewanneDreckig());
        assertFalse(itemWasserbombe.isGeschirrDreckig());

        assertTrue(itemWasserbombe.isAvailable(goodRolledDice));
        assertFalse(itemWasserbombe.isAvailable(badRolledDiceAvailable));
        assertTrue(itemWasserbombe.isStealable(goodRolledDice));
        assertFalse(itemWasserbombe.isStealable(badRolledDiceSteal));
        assertThrows(NullPointerException.class, ()->{
            itemWasserbombe.isAvailable(null);
        });
        assertThrows(NullPointerException.class, ()->{
            itemWasserbombe.isStealable(null);
        });
    }
}
