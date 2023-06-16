package com.example.se2_group4_project;

import com.example.se2_group4_project.cards.Geschirr;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class GeschirrTest {

    Geschirr geschirr;

    @BeforeEach
    void setUp() throws JSONException {
        JSONObject geschirrJson = new JSONObject(
                "{" +
                        "id: 1," +
                        "name: Geschirr-Test," +
                        "cardType: TABLEWARE," +
                        "following: 3," +
                        "cardFront: geschirr_dreckig_test," +
                        "cardBack: geschirr_sauber_test," +
                        "isFront: true" +
                        "}"
        );
        geschirr = new Geschirr(geschirrJson);
    }

    @Test
    public void basicTest(){
        int expectedFollowing = 3;
        int expectedCountRoll = 1;
        assertEquals(expectedCountRoll, geschirr.getCountRoll());
        assertEquals(expectedFollowing, geschirr.getFollowing());
        assertFalse(geschirr.isRollAgain());

        expectedCountRoll = 2;
        expectedFollowing = 2;
        geschirr.setFollowing(2);
        geschirr.setCountRoll(2);
        assertEquals(expectedCountRoll, geschirr.getCountRoll());
        assertEquals(expectedFollowing, geschirr.getFollowing());
    }

    @Test
    public void integrationTest(){
        int expectedFollowing = 3;
        int expectedCountRoll = 1;

        ArrayList<Integer> goodRolledDice = new ArrayList<>();
        goodRolledDice.add(5);
        goodRolledDice.add(2);
        goodRolledDice.add(3);
        goodRolledDice.add(4);

        ArrayList<Integer> badRolledDice = new ArrayList<>();
        badRolledDice.add(1);
        badRolledDice.add(2);
        badRolledDice.add(5);

        ArrayList<Integer> shortRolledDice = new ArrayList<>();
        shortRolledDice.add(2);
        shortRolledDice.add(1);

        assertEquals(expectedCountRoll, geschirr.getCountRoll());
        assertEquals(expectedFollowing, geschirr.getFollowing());
        assertFalse(geschirr.isRollAgain());

        geschirr.setRollAgain(true);
        assertTrue(geschirr.isRollAgain());
        geschirr.setRollAgain(false);
        assertFalse(geschirr.isRollAgain());

        assertTrue(geschirr.isAvailable(goodRolledDice));
        assertFalse(geschirr.isAvailable(badRolledDice));
        assertFalse(geschirr.isAvailable(shortRolledDice));
    }

    @Test
    public void negativeTest(){
        geschirr.setFollowing(2);
        ArrayList<Integer> shortRolledDice = new ArrayList<>();
        shortRolledDice.add(2);
        shortRolledDice.add(1);
        assertThrows(IndexOutOfBoundsException.class, ()->{
            geschirr.isAvailable(shortRolledDice);
        });

        geschirr.setFollowing(3);
        ArrayList<Integer> badRolledDice = new ArrayList<>();
        badRolledDice.add(4);
        badRolledDice.add(5);
        badRolledDice.add(6);
        assertFalse(geschirr.isAvailable(badRolledDice));

        ArrayList<Integer> badRolledDice1 = new ArrayList<>();
        badRolledDice1.add(-1);
        badRolledDice1.add(0);
        badRolledDice1.add(1);
        assertFalse(geschirr.isAvailable(badRolledDice1));

        ArrayList<Integer> emptyRolledDice = new ArrayList<>();
        assertFalse(geschirr.isAvailable(emptyRolledDice));
        assertThrows(NullPointerException.class, ()-> {
           geschirr.isAvailable(null);
        });
    }
}
