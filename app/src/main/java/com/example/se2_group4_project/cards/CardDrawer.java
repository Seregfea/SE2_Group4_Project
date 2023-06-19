package com.example.se2_group4_project.cards;

import android.content.Context;
import android.util.Log;

import com.example.se2_group4_project.Gameboard;
import com.example.se2_group4_project.player.PlayerController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class CardDrawer {

    //Listen für meine Ablegestapel
    private ArrayList<Card> playerBlueStack = new ArrayList<>();
    private ArrayList<Card> playerGreenStack = new ArrayList<>();
    private ArrayList<Card> playerOrangeStack = new ArrayList<>();
    private ArrayList<Card> playerTealStack = new ArrayList<>();
    private ArrayList<Card> witzigStack = new ArrayList<>();
    private ArrayList<Card> witzigWitzigStack = new ArrayList<>();
    private ArrayList<Card> itemsStack = new ArrayList<>();
    private ArrayList<Card> roommateEasyStack = new ArrayList<>();
    private ArrayList<Card> roommateDifficultStack = new ArrayList<>();
    private ArrayList<Card> troublemakerStack = new ArrayList<>();
    private ArrayList<Card> schaukelstuhlStack = new ArrayList<>();
    private ConvertJSON convertJSON;
    private PlayerController playerController;
    ObjectMapper mapper;

    private Context context;

    public CardDrawer(Context context) {
        this.context = context;
        this.mapper = new ObjectMapper();
        this.convertJSON = new ConvertJSON(context);
    }

    public ArrayList<Card> getPlayerBlueStack() {
        return playerBlueStack;
    }

    public void setPlayerBlueStack(ArrayList<Card> playerBlueStack) {
        this.playerBlueStack = playerBlueStack;
    }

    public ArrayList<Card> getPlayerGreenStack() {
        return playerGreenStack;
    }

    public void setPlayerGreenStack(ArrayList<Card> playerGreenStack) {
        this.playerGreenStack = playerGreenStack;
    }

    public ArrayList<Card> getPlayerOrangeStack() {
        return playerOrangeStack;
    }

    public void setPlayerOrangeStack(ArrayList<Card> playerOrangeStack) {
        this.playerOrangeStack = playerOrangeStack;
    }

    public ArrayList<Card> getPlayerTealStack() {
        return playerTealStack;
    }

    public void setPlayerTealStack(ArrayList<Card> playerTealStack) {
        this.playerTealStack = playerTealStack;
    }

    public ArrayList<Card> getWitzigStack() {
        return witzigStack;
    }

    public void setWitzigStack(ArrayList<Card> witzigStack) {
        this.witzigStack = witzigStack;
    }

    public ArrayList<Card> getWitzigWitzigStack() {
        return witzigWitzigStack;
    }

    public void setWitzigWitzigStack(ArrayList<Card> witzigWitzigStack) {
        this.witzigWitzigStack = witzigWitzigStack;
    }

    public ArrayList<Card> getItemsStack() {
        return itemsStack;
    }

    public void setItemsStack(ArrayList<Card> itemsStack) {
        this.itemsStack = itemsStack;
    }

    public ArrayList<Card> getTroublemakerStack() {
        return troublemakerStack;
    }

    public void setTroublemakerStack(ArrayList<Card> troublemakerStack) {
        this.troublemakerStack = troublemakerStack;
    }

    public ArrayList<Card> getRoommateEasyStack() {
        return roommateEasyStack;
    }

    public void setRoommateEasyStack(ArrayList<Card> roommateEasyStack) {
        this.roommateEasyStack = roommateEasyStack;
    }

    public ArrayList<Card> getRoommateDifficultStack() {
        return roommateDifficultStack;
    }

    public void setRoommateDifficultStack(ArrayList<Card> roommateDifficultStack) {
        this.roommateDifficultStack = roommateDifficultStack;
    }

    public ArrayList<Card> getSchaukelstuhlStack() {
        return schaukelstuhlStack;
    }

    public void setSchaukelstuhlStack(ArrayList<Card> schaukelstuhlStack) {
        this.schaukelstuhlStack = schaukelstuhlStack;
    }

    //Karten holen und speichern
    public void generateInitialCards() throws FileNotFoundException {
        this.playerBlueStack = this.convertJSON.getCards("playerBlue");
        this.playerGreenStack = this.convertJSON.getCards("playerGreen");
        this.playerOrangeStack = this.convertJSON.getCards("playerOrange");
        this.playerTealStack = this.convertJSON.getCards("playerTeal");
        this.witzigStack = this.convertJSON.getCards("witzig");
        this.witzigWitzigStack = this.convertJSON.getCards("witzigWitzig");
        this.itemsStack = this.convertJSON.getCards("item");
        this.troublemakerStack = this.convertJSON.getCards("troublemaker");
        this.roommateEasyStack = this.convertJSON.getCards("roommateEasy");
        this.roommateDifficultStack = this.convertJSON.getCards("roommateDifficult");
        this.schaukelstuhlStack = this.convertJSON.getCards("schaukelstuhl");
    }


    public void addCardsType(Context cardcontext){
        try {
            this.setItemsStack(createCardAbilitys("item.json",this.itemsStack,cardcontext));
            this.setRoommateDifficultStack(createCardAbilitys("roommate-difficult.json",this.roommateDifficultStack,cardcontext));
            this.setRoommateEasyStack(createCardAbilitys("roommate-easy.json",this.roommateEasyStack,cardcontext));
            this.setSchaukelstuhlStack(createCardAbilitys("schaukelstuhl.json",this.schaukelstuhlStack,cardcontext));
            this.setTroublemakerStack(createCardAbilitys("troublemaker.json",this.troublemakerStack,cardcontext));
            this.setWitzigStack(createCardAbilitys("witzigToDo.json",this.witzigStack,cardcontext));
            this.setWitzigWitzigStack(createCardAbilitys("witzigWitzigToDo.json",this.witzigWitzigStack,cardcontext));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    //ME,
    //BATHTUB,
    //COUCH,
    //TABLEWARE,
    //SLEEP,
    //AWAKE,

    private ArrayList createCardAbilitys(String toMap, ArrayList<Card> cards, Context cardContext) throws JsonProcessingException {
        ArrayList<Card> cardNew = new ArrayList<>();
        cardNew = cards;
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Log.d("card new", cardNew.toString());
        switch (toMap) {
            case "item.json":
                Log.d("Json context item", "");
                ArrayList<Item> item = mapper.readValue(readFileAsString(toMap), new TypeReference<ArrayList<Item>>() {
                });
                Log.d("JSON to array witzig", item.toString());

                for (int i = 0; i < item.size(); i++) {

                    Log.d("Json card front", "" + item.get(i).getCardFront());
                    String drawable = "@drawable/" + item.get(i).getCardFront();
                    Log.d("item card item", drawable);
                    int id = cardContext.getResources().getIdentifier(drawable, null, cardContext.getPackageName());
                    Log.d("Json context id1", id + "");
                    for (int d = 0; d < cardNew.size(); d++) {
                        Log.d("Json context loop", cardNew.get(d).getImageViewID() + "");
                        if (cardNew.get(d).getImageViewID() == id) {
                            Log.d("Json context id2", cardNew.get(d).getImageViewID() + "");
                            cardNew.get(d).setItem(item.get(i));
                        }
                    }
                }
                return cardNew;

            case "roommate-difficult.json":
                Log.d("Json context romm dif", "");
                ArrayList<RoommateDifficult> room = mapper.readValue(readFileAsString(toMap), new TypeReference<ArrayList<RoommateDifficult>>() {
                });
                Log.d("JSON to array witzig", room.toString());

                for (int i = 0; i < room.size(); i++) {

                    Log.d("Json card front", "" + room.get(i).getCardFront());
                    String drawable = "@drawable/" + room.get(i).getCardFront();
                    Log.d("Json cont", drawable);
                    int id = cardContext.getResources().getIdentifier(drawable, null, cardContext.getPackageName());
                    Log.d("Json context id1", id + "");
                    for (int d = 0; d < cardNew.size(); d++) {
                        if (cardNew.get(d).getImageViewBackID() == id) {
                            Log.d("Json context id2", cardNew.get(d).getImageViewID() + "");
                            cardNew.get(d).setRoommateDifficult(room.get(i));
                        }
                    }
                }
                return cardNew;

            case "roommate-easy.json":
                Log.d("Json context romm easy", "");
                ArrayList<RoommateEasy> roomE = mapper.readValue(readFileAsString(toMap), new TypeReference<ArrayList<RoommateEasy>>() {});
                Log.d("JSON to array witzig", roomE.toString());

                for (int i = 0; i < roomE.size(); i++) {

                    Log.d("Json card front", "" + roomE.get(i).getCardFront());
                    String drawable = "@drawable/" + roomE.get(i).getCardFront();
                    Log.d("item card item id", drawable);
                    int id = cardContext.getResources().getIdentifier(drawable, null, cardContext.getPackageName());
                    Log.d("Json context id1", id + "");
                    for (int d = 0; d < cardNew.size(); d++) {
                        if (cardNew.get(d).getImageViewBackID() == id) {
                            Log.d("Json context id2", cardNew.get(d).getImageViewID() + "");
                            cardNew.get(d).setRoommateEasy(roomE.get(i));
                        }
                    }
                }
                return cardNew;

            case "schaukelstuhl.json":
                Log.d("Json context schauk", "");
                ArrayList<Schaukelstuhl> schaukel = mapper.readValue(readFileAsString(toMap), new TypeReference<ArrayList<Schaukelstuhl>>() {});
                Log.d("JSON to array witzig", schaukel.toString());

                Log.d("Json card front", "" + schaukel.get(0).getCardFront());
                String drawableS = "@drawable/" + schaukel.get(0).getCardFront();
                Log.d("item card item id", drawableS);
                int idS = cardContext.getResources().getIdentifier(drawableS, null, cardContext.getPackageName());
                Log.d("Json context id1", idS + "");
                cardNew.get(0).setSchaukelstuhl(schaukel.get(0));
                return cardNew;


            case "troublemaker.json":
                Log.d("Json context trouble", "");
                ArrayList<Troublemaker> trouble = mapper.readValue(readFileAsString(toMap), new TypeReference<ArrayList<Troublemaker>>() {});
                Log.d("JSON to array witzig", trouble.toString());

                for (int i =0; i < trouble.size(); i++){

                    Log.d("Json card front", ""+trouble.get(i).getCardFront());
                    String drawable = "@drawable/"+trouble.get(i).getCardFront();
                    Log.d("item card item id", drawable);
                    int id = cardContext.getResources().getIdentifier(drawable, null,cardContext.getPackageName());
                    Log.d("Json context id1", id+"");
                    for (int d = 0; d < cardNew.size(); d++){
                        if(cardNew.get(d).getImageViewBackID() == id){
                            Log.d("Json context id2", cardNew.get(d).getImageViewID()+"");
                            cardNew.get(d).setTroublemaker(trouble.get(i));
                        }
                    }
                }
                return cardNew;

            case "witzigToDo.json":
                Log.d("Json context witz", "");
                ArrayList<WitzigToDos> witzig = mapper.readValue(readFileAsString(toMap), new TypeReference<ArrayList<WitzigToDos>>() {});
                Log.d("JSON to array witzig", witzig.toString());

                for (int i =0; i < witzig.size(); i++){

                    Log.d("Json card front", ""+witzig.get(i).getCardFront());
                    String drawable = "@drawable/"+witzig.get(i).getCardFront();
                    Log.d("item card item id", drawable);
                    int id = cardContext.getResources().getIdentifier(drawable, null,cardContext.getPackageName());
                    Log.d("Json context id1", id+"");
                    for (int d = 0; d < cardNew.size(); d++){
                        if(cardNew.get(d).getImageViewID() == id){
                            Log.d("Json context id2", cardNew.get(d).getImageViewID()+"");
                            cardNew.get(d).setWitzigToDos(witzig.get(i));
                        }
                    }
                }
                return cardNew;

            case "witzigWitzigToDo.json":
                Log.d("Json context witz", "");
                ArrayList<WitzigWitzigToDos> witzigT = mapper.readValue(readFileAsString(toMap), new TypeReference<ArrayList<WitzigWitzigToDos>>() {});
                Log.d("JSON to array witzig", witzigT.toString());

                for (int i =0; i < witzigT.size(); i++){

                    Log.d("Json card front", ""+witzigT.get(i).getCardFront());
                    String drawable = "@drawable/"+witzigT.get(i).getCardFront();
                    Log.d("item card item id", drawable);
                    int id = cardContext.getResources().getIdentifier(drawable, null,cardContext.getPackageName());
                    Log.d("Json context id1", id+"");
                    for (int d = 0; d < cardNew.size(); d++){
                        if(cardNew.get(d).getImageViewID() == id){
                            Log.d("Json context id2", cardNew.get(d).getImageViewID()+"");
                            cardNew.get(d).setWitzigWitzigToDos(witzigT.get(i));
                        }
                    }
                }
                return cardNew;

        }
        return null;
    }


    private String readFileAsString(String filename) {
        String jsonString;
        try {
            InputStream is = this.context.getAssets().open(filename);
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
    // checkIfHighlight methode abändern als allgemeine Methode für zugriff auf objekte
    // checkIfHighlight überpfrüft dann nur mehr die ifs

    public void checkIfHighlight(ArrayList<Card> cardStack, Gameboard gameboard, ArrayList<Integer> dices) throws JSONException {

        ArrayList<Integer> rolledDices = dices;
        for (Card card : cardStack) {

            CardType cardType = card.getCardType();
            this.playerController = gameboard.getPlayer();

            switch (cardType) {
                case ITEM:
                    JSONObject itemObjekt = card.jsonObject();
                    Item item = new Item(itemObjekt);

                    if (item.isAvailable(rolledDices)) {
                        Log.d("Highlight", "Item Highlight");
                        gameboard.highlightCards(card);
                    }
                    break;

                case ROOMMATE:
                    JSONObject roommateObjektEasy =  card.jsonObject();
                    RoommateEasy roommateEasy = new RoommateEasy(roommateObjektEasy);

                    if (roommateEasy.isAvailable(rolledDices)) {
                        gameboard.highlightCards(card);
                    }
                    break;

                case ROOMMATEDIFF:
                JSONObject roommateObjektDifficult = card.jsonObject();
                RoommateDifficult roommateDifficult = new RoommateDifficult(roommateObjektDifficult);

                if (roommateDifficult.isAvailable(rolledDices)) {
                    gameboard.highlightCards(card);
                }
                break;

                case ME:
                    JSONObject meObjekt = card.jsonObject();
                    Me me = new Me(meObjekt);

                    if (me.isAvailable(rolledDices)) {
                        gameboard.highlightCards(card);
                    }
                    break;

                case BATHTUB:
                    JSONObject badewanneObjekt = card.jsonObject();
                    Badewanne badewanne = new Badewanne(badewanneObjekt);

                    if (badewanne.isAvailable(rolledDices)) {
                        gameboard.highlightCards(card);
                    }
                    break;

                case COUCH:
                    JSONObject couchObjekt = card.jsonObject();
                    Couch couch = new Couch(couchObjekt);

                    if (couch.isAvailable(rolledDices)) {
                        gameboard.highlightCards(card);
                    }
                    break;
                case TABLEWARE:
                    JSONObject geschirrObjekt = card.jsonObject();
                    Geschirr geschirr = new Geschirr(geschirrObjekt);

                    if (geschirr.isAvailable(rolledDices)) {
                        gameboard.highlightCards(card);
                    }
                    break;

                case WITZIG:
                    JSONObject witzigObjekt = card.jsonObject();
                    WitzigToDos witzigToDos = new WitzigToDos(witzigObjekt);

                    if (witzigToDos.isAvailable(rolledDices)) {
                        gameboard.highlightCards(card);
                    }
                    break;

                case WITZIGWITZIG:
                    JSONObject witzigwitzigObjekt = card.jsonObject();
                    WitzigWitzigToDos witzigWitzigToDos = new WitzigWitzigToDos(witzigwitzigObjekt);

                    if (witzigWitzigToDos.isAvailable(rolledDices)) {
                        gameboard.highlightCards(card);
                    }
                    break;
            }
        }
    }
}
