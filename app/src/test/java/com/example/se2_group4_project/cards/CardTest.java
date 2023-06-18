package com.example.se2_group4_project.cards;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void jsonObject_item() throws JSONException {
        Card itemCard = new Card(CardType.ITEM, 1,"Decke",3,2,0,0,0,0,0,0,
                0,0,3,null,2,"decke_vs","gegenstand_rs",0,true);
        JSONObject json = itemCard.jsonObject();

        assertEquals(json.get("number"),3);
        assertEquals(json.get("count"),2);
        assertEquals(json.get("stealCard"),3);
        assertEquals(json.get("schnapspralinen"),2);
        assertEquals(json.get("itemBenefit"),"");
    }
    @Test
    void jsonObject_roommateDiff() throws JSONException {
        Card roommateDiffCard = new Card(CardType.ROOMMATEDIFF, 6,"Otto-Von",0,0,0,0,0,0,0,0,
                4,0,0,null,0,"spezial_ottovon_vs","spezial_ottovon_rs",0,false);

        JSONObject json = roommateDiffCard.jsonObject();

        assertEquals(json.get("roommateBenefit"),"");
        assertEquals(json.get("following"), 4);
        assertEquals(json.get("count"), 0);
    }

    @Test
    void jsonObject_roommateEasy() throws JSONException {
        Card roommateEasyCard = new Card(CardType.ROOMMATE, 15,"Sissi",4,2,0,0,0,0,0,0,
                0,0,0,null,0,"normal_sissi_vs","normal_sissi_rs",0,false);

        JSONObject json = roommateEasyCard.jsonObject();

        assertEquals(json.get("number"), 4);
        assertEquals(json.get("count"), 2);
    }

    @Test
    void jsonObject_me() throws JSONException {
        Card meCard = new Card(CardType.ME, 3,"Ich-Hellblau",2,2,0,0,0,0,0,0,
                0,0,0,null,0,"ich_hellblau_vs","ich_hellblau_vs",0,true);

        JSONObject json = meCard.jsonObject();

        assertEquals(json.get("number"), 2);
        assertEquals(json.get("count"), 2);
    }

    @Test
    void jsonObject_bathtub() throws JSONException {
        Card baththubCard = new Card(CardType.BATHTUB, 0,"Badewanne-grün",3,2,0,0,0,0,0,0,
                0,0,0,null,0,"bad_dreckig_gruen","bad_sauber_gruen",0,true);

        JSONObject json = baththubCard.jsonObject();

        assertEquals(json.get("number"), 3);
        assertEquals(json.get("count"), 2);
    }

    @Test
    void jsonObject_tableware() throws JSONException {
        Card tablewareCard = new Card(CardType.TABLEWARE, 1,"Geschirr-orange",0,0,0,0,0,0,0,0,
                3,0,0,null,0,"geschirr_dreckig_orange","geschirr_sauber_orange",0,true);

        JSONObject json = tablewareCard.jsonObject();

        assertEquals(json.get("following"),3);
    }

    @Test
    void jsonObject_couch() throws JSONException {
        Card couchCard = new Card(CardType.COUCH, 2,"Couch-türkis",1,2,0,0,0,0,0,0,
                0,0,0,null,0,"couch_matschig_tuerkis","couch_sauber_tuerkis",0,true);

        JSONObject json = couchCard.jsonObject();

        assertEquals(json.get("number"), 1);
        assertEquals(json.get("count"), 2);
    }

    @Test
    void jsonObject_witzig() throws JSONException {
        Card witzigCard = new Card(CardType.WITZIG, 4,"Eierkuchen backen",0,0,0,0,0,0,0,0,
                0,16,0,null,4,"witzig_eierkuchen","witzig_rs",0,true);

        JSONObject json = witzigCard.jsonObject();

        assertEquals(json.get("number"),0);
        assertEquals(json.get("number2"),0);
        assertEquals(json.get("count"),0);
        assertEquals(json.get("count2"),0);
        assertEquals(json.get("following"), 0);
        assertEquals(json.get("min_sum"),16);
        assertEquals(json.get("schnapspralinen"),4);
       // assertNull(json.get("toDoPenalty"));
    }

    @Test
    void jsonObject_witzigWitzig() throws JSONException {
        Card witzigWitzigCard = new Card(CardType.WITZIGWITZIG, 5,"Neue Regeln für Monopoly erfinden",1,3,2,2,0,0,0,0,
                0,0,0,null,7,"witzigwitzig_monopoly","witzigwitzig_rs",0,true);

        JSONObject json = witzigWitzigCard.jsonObject();

        assertEquals(json.get("number"),1);
        assertEquals(json.get("number2"),2);
        assertEquals(json.get("number3"),0);
        assertEquals(json.get("number4"),0);
        assertEquals(json.get("count"),3);
        assertEquals(json.get("count2"),2);
        assertEquals(json.get("count3"),0);
        assertEquals(json.get("count4"),0);
        assertEquals(json.get("schnapspralinen"),7);
       // assertEquals(json.get("toDoPenalty"), "[]");
    }
}