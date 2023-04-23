package com.example.se2_group4_project;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ConvertJSON {

    public static ArrayList<Card> getCards(String type) throws FileNotFoundException {

        String fileName = null;

        switch (type) {
            case "playerBlue":
                fileName = "playerBlue.json";
                break;
            case "playerGreen":
                fileName = "playerGreen.json";
                break;
            case "playerOrange":
                fileName = "playerOrange.json";
                break;
            case "playerTeal":
                fileName = "playerTeal.json";
                break;
            case "witzig":
                fileName = "witzigToDo.json";
                break;
            case "witzigWitzig":
                fileName = "witzigWitzigToDo.json";
                break;
            case "item":
                fileName = "item.json";
                break;
            case "troublemaker":
                fileName = "troublemaker.json";
                break;
            case "roommate":
                fileName = "roommate.json";
                break;
        }

        //Auslesen der JSON Datei
        JsonArray jsonArray = JsonParser.parseReader(new FileReader("initialData/" + fileName)).getAsJsonArray();

        // Erstellen Gson Object
        Gson gson = new Gson();

        // Bekomme Liste mit allen Kartenobjekten
        ArrayList<Card> cards = gson.fromJson(jsonArray.toString(),
                new TypeToken<ArrayList<Card>>() {
                }.getType());


        return cards;
    }

}
