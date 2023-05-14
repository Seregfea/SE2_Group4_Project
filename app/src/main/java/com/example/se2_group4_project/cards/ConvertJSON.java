package com.example.se2_group4_project.cards;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ConvertJSON {

    private Context applicationContext;

    public ConvertJSON (Context context){
        this.applicationContext = context;
    }

    public ArrayList<Card> getCards(String type) {

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
            case "roommateEasy":
                fileName = "roommate-easy.json";
                break;
            case "roommateDifficult":
                fileName = "roommate-difficult.json";
                break;
            case "schaukelstuhl":
                fileName = "schaukelstuhl.json";
                break;
        }

      String JSON = readFileAsString(fileName);



        //Auslesen der JSON Datei
      //  JsonArray jsonArray = JsonParser.parseReader(new FileReader()).getAsJsonArray();

        // Erstellen Gson Object
        Gson gson = new Gson();

        // Bekomme Liste mit allen Kartenobjekten
        ArrayList<Card> cards = gson.fromJson(JSON,
                new TypeToken<ArrayList<Card>>() {
                }.getType());


        return cards;
    }

    private String readFileAsString(String filename) {
        String jsonString;
        try {
            InputStream is = applicationContext.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (Exception e) {
            return null;
        }
        return jsonString;
    }

}
