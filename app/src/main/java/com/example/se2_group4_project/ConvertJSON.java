package com.example.se2_group4_project;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ConvertJSON {

    public static ArrayList<Card> getCards() throws FileNotFoundException {

        //auslesen der JSON Datei
        JsonParser parser = new JsonParser();
        Object obj = parser.parse(new FileReader("items.json"));
        //konvertiere das Objekt in ein JSON Objekt
        JsonObject jsonObject = (JsonObject) obj;

        // Erstellen Gson Object
        Gson gson = new Gson();

        // Bekomme Liste mit allen Kartenobjekten
        ArrayList<Card> cards = gson.fromJson(jsonObject.toString(),
                new TypeToken<ArrayList<Card>>(){}.getType());

        return cards;
    }

}
