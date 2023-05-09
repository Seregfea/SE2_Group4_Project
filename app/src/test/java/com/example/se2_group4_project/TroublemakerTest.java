package com.example.se2_group4_project;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.se2_group4_project.cards.Troublemaker;

public class TroublemakerTest {

    @Test
    public void basicTestPinguin() throws JSONException {
        JSONObject troublemaker_pinguin = new JSONObject(
                "{" +
                        "troublemakerPenalty: TABLEWARE," +
                        "schnapspralinen: 0" +
                        "}"
        );
        Troublemaker troublemaker = new Troublemaker(troublemaker_pinguin);
        int actualPraline = troublemaker.getSchnapspralinen();
        int expectedPraline = 0;
        String actualPenalty = troublemaker_pinguin.getString("troublemakerPenalty");
        String expectedPenalty = "TABLEWARE";
        assertEquals(expectedPenalty, actualPenalty);
        assertEquals(expectedPraline, actualPraline);
        assertTrue(troublemaker.isTableware());
    }

    @Test
    public void basicTestDickerMann() throws JSONException {
        JSONObject troublemaker_dickermann = new JSONObject(
                "{" +
                        "troublemakerPenalty: TURN," +
                        "schnapspralinen: -2" +
                        "}"
        );
        Troublemaker troublemaker = new Troublemaker(troublemaker_dickermann);
        int actualPraline = troublemaker.getSchnapspralinen();
        int expectedPraline = -2;
        String actualPenalty = troublemaker_dickermann.getString("troublemakerPenalty");
        String expectedPenalty = "TURN";
        assertEquals(expectedPenalty, actualPenalty);
        assertEquals(expectedPraline, actualPraline);
        assertTrue(troublemaker.isTurn());
    }

    @Test
    public void basicTestGespenst() throws JSONException {
        JSONObject troublemaker_gespenst = new JSONObject(
                "{" +
                        "troublemakerPenalty: ROOM," +
                        "schnapspralinen: -1" +
                        "}"
        );
        Troublemaker troublemaker = new Troublemaker(troublemaker_gespenst);
        int actualPraline = troublemaker.getSchnapspralinen();
        int expectedPraline = -1;
        String actualPenalty = troublemaker_gespenst.getString("troublemakerPenalty");
        String expectedPenalty = "ROOM";
        assertEquals(expectedPenalty, actualPenalty);
        assertEquals(expectedPraline, actualPraline);
        assertTrue(troublemaker.isRoom());
    }

}
