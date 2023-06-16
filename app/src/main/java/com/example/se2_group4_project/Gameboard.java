package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.se2_group4_project.backend.callbacks.ClientCallbacks;
import com.example.se2_group4_project.backend.server.Server;
import com.example.se2_group4_project.callbacks.GameboardCallbacks;
import com.example.se2_group4_project.backend.client.Client;
import com.example.se2_group4_project.cheating.CheatFunction;
import com.example.se2_group4_project.cheating.CheatPopUpActivity;
import com.example.se2_group4_project.databinding.ActivityDiceBinding;
import com.example.se2_group4_project.databinding.ActivityGameboardBinding;
import com.example.se2_group4_project.cards.Badewanne;
import com.example.se2_group4_project.dices.DicePopUpActivity;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.CardDrawer;

import com.example.se2_group4_project.gameboard_adjustments.SoundManager;
import com.example.se2_group4_project.player.PlayerController;

import com.example.se2_group4_project.pointDisplay.PointDisplay;
import com.example.se2_group4_project.recyclerview.MyRecyclerviewAdabter;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


public class Gameboard extends AppCompatActivity implements GameboardCallbacks {
    private Handler clientHandler;
    private ClientCallbacks clientCallbacks;
    private MyRecyclerviewAdabter playerRecyclerviewAdabter;
    private PlayerController player;

    public PlayerController getPlayer() {
        return player;
    }

    private CardDrawer c;
    private DicePopUpActivity dicePopUpActivity;
    // hardcoded
    // später: methode aus player-klasse
    private int availableDices = 4;
    private HandlerThread handlerThread;

    private boolean diceIsRolled = false;

    private int isParkBtn = 0;

    private boolean hasCheated = false;
    private Badewanne badewanne;

    private static List<ImageView> displayedCards = new ArrayList<>();

    private List<Integer> parkedDice = new ArrayList<>();

    private int testVariable = 0;

    //////////////////////////// activity bindings /////////////////////////////////
    private ActivityGameboardBinding activityGameboardBinding;
    private ActivityDiceBinding activityDiceBinding;
    private View view;

    /////////////////////////// cheat buttons/variables ///////////////////////////////
    private Button player1btn;
    private Button player2btn;
    private Button player3btn;

    private int cheatCounter;
    private boolean cheated;

    /////////////////////////// player disable variables ///////////////////////////////
    private boolean myTurn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDiceBinding = ActivityDiceBinding.inflate(getLayoutInflater());
        activityGameboardBinding = ActivityGameboardBinding.inflate(getLayoutInflater());
        view = activityGameboardBinding.getRoot();
        startWindowFeature();
        setContentView(view);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String string = bundle.get("testmodus").toString();
        Log.d("intent testmode", string);
        this.testVariable = Integer.parseInt(string);

        handlerThread = new HandlerThread("boardHandler");
        handlerThread.start();
        Log.d("pointdisplay", "start");
        PointDisplay pointDisplay = new PointDisplay();
        startPointView(pointDisplay);
        Log.d("carddrawer start", "start");
        initCarddrawer();

        if (testVariable == 1) {
            testmodus();
        } else {
            Log.d("client start", "start");
            startClient();
        }

        myTurn = true; //until player states are implemented test it by switching this boolean
        if(myTurn) {
            setUpDice();
            setUpCheatButtons();
            setListeners();
        }
        cheatCounter = 0;
        cheated = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int musicChoice = sharedPreferences.getInt("musicChoice", R.raw.mysterious); // default_music ist ein Platzhalter für die Standardmusik
        SoundManager.keepMusicGoing = true;
        SoundManager.start(this, musicChoice);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!SoundManager.keepMusicGoing) {
            SoundManager.stop();
        }
        SoundManager.keepMusicGoing = false;
    }

    @Override
    protected void onDestroy() {
        handlerThread.quit();
        super.onDestroy();
    }

    ///////////////////////////////////// card methods ////////////////////////

    public void createPlayerBoard(int player) {

        switch (player) {
            case 0:
                this.player = new PlayerController(player, c.getPlayerBlueStack(), clientCallbacks, new Handler(handlerThread.getLooper()));
                addCardsToLinearLayout(R.id.CardsLayoutLeft, c.getPlayerTealStack());
                addCardsToLinearLayout(R.id.CardsLayoutTop, c.getPlayerGreenStack());
                addCardsToLinearLayout(R.id.CardsLayoutRight, c.getPlayerOrangeStack());
                break;
            case 1:
                this.player = new PlayerController(player, c.getPlayerGreenStack(), clientCallbacks, new Handler(handlerThread.getLooper()));
                addCardsToLinearLayout(R.id.CardsLayoutLeft, c.getPlayerBlueStack());
                addCardsToLinearLayout(R.id.CardsLayoutTop, c.getPlayerOrangeStack());
                addCardsToLinearLayout(R.id.CardsLayoutRight, c.getPlayerTealStack());
                break;
            case 2:
                this.player = new PlayerController(player, c.getPlayerOrangeStack(), clientCallbacks, new Handler(handlerThread.getLooper()));
                addCardsToLinearLayout(R.id.CardsLayoutLeft, c.getPlayerGreenStack());
                addCardsToLinearLayout(R.id.CardsLayoutTop, c.getPlayerTealStack());
                addCardsToLinearLayout(R.id.CardsLayoutRight, c.getPlayerBlueStack());
                break;
            case 3:
                this.player = new PlayerController(player, c.getPlayerTealStack(), clientCallbacks, new Handler(handlerThread.getLooper()));
                addCardsToLinearLayout(R.id.CardsLayoutLeft, c.getPlayerOrangeStack());
                addCardsToLinearLayout(R.id.CardsLayoutTop, c.getPlayerBlueStack());
                addCardsToLinearLayout(R.id.CardsLayoutRight, c.getPlayerGreenStack());
                break;
            default:
                Log.d("no player", "no player " + player);
                break;
        }
        createRecyclerviewPlayer(activityGameboardBinding.userCardRecyclerView, LinearLayoutManager.HORIZONTAL, this.player, R.layout.recycler_item_view);
        addCardsToLinearLayout(R.id.roommateDifficultLayout, c.getRoommateDifficultStack());
        addCardsToLinearLayout(R.id.roommateEasyLayout, c.getRoommateEasyStack());
        addCardsToLinearLayout(R.id.witzigLayout, c.getWitzigStack());
        addCardsToLinearLayout(R.id.witzigWitzigLayout, c.getWitzigWitzigStack());
        addCardsToLinearLayout(R.id.troublemakerLayout, c.getTroublemakerStack());
        addCardsToLinearLayout(R.id.ItemCardsLayout, c.getItemsStack());
        addCardsToLinearLayout(R.id.SchaukelstuhlLayout, c.getSchaukelstuhlStack());

    }

    public void addCardsToPlayer() {
        CardDrawer cardDrawer = new CardDrawer(this.getApplicationContext());

        try {
            cardDrawer.generateInitialCards();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < cardDrawer.getItemsStack().size(); i++) {

            final ImageView itemCardImage = (ImageView) activityGameboardBinding.ItemCardsLayout.getChildAt(i);
            final Card card = cardDrawer.getItemsStack().get(i);
            Log.d("card gameboard", card.toString());
            itemCardImage.setOnClickListener(view -> {
                Log.d("get item card", " click 1");
                activityGameboardBinding.ItemCardsLayout.removeView(itemCardImage);

                this.playerRecyclerviewAdabter.addPlayerItem(card);
                int integry = this.playerRecyclerviewAdabter.getItemCount();
                Log.d("get item card", " click 2");
                this.playerRecyclerviewAdabter.notifyDataSetChanged();
                Log.d("get item card", "" + integry);
            });
        }
    }

    private void createItem(View view) {

    }

    public void addTopCardToLinearLayout(int linearLayoutId, ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            LinearLayout linearLayout = findViewById(linearLayoutId);
            ImageView iView = new ImageView(linearLayout.getContext());

            String currentCardFront = card.getCurrentCardFront();

            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    currentCardFront, "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setId(imageRessourceID);
            linearLayout.addView(iView);
            displayedCards.add(iView);
            card.setImageViewID(iView.getId());
            if(this.player.getMyTurn() == 1) { // disabled if not your turn
                iView.setOnClickListener(view -> {
                    linearLayout.removeView(iView);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.weight = 5;
                    iView.setLayoutParams(params);
                    // activityGameboardBinding.UserCardsLayout.addView(iView);
                });
            }
        }
    }

    public void addCardsToLinearLayout(int linearLayoutId, ArrayList<Card> cards) {
        LinearLayout linearLayout = findViewById(linearLayoutId);
        for (Card card : cards) {
            ImageView iView = new ImageView(linearLayout.getContext());

            String currentCardFront = card.getCurrentCardFront();

            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    currentCardFront, "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            iView.setId(imageRessourceID);

            float aspectRatio = 5;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = aspectRatio;

            if (linearLayoutId == R.id.ItemCardsLayout) {
                iView.setLayoutParams(params);
            }

            if (linearLayoutId == R.id.UserCardsLayout) {
                iView.setRotation(0);
                iView.setLayoutParams(params);

            } else if (linearLayoutId == R.id.CardsLayoutLeft) {
                iView.setRotation(90);
                iView.setLayoutParams(params);

            } else if (linearLayoutId == R.id.CardsLayoutRight) {
                iView.setRotation(-90f);
                iView.setLayoutParams(params);

            } else if (linearLayoutId == R.id.CardsLayoutTop) {
                iView.setRotation(180f);
                iView.setLayoutParams(params);

            }
            linearLayout.addView(iView);
            displayedCards.add(iView);
            card.setImageViewID(iView.getId());
        }
    }

    // TODO: Implement onClickListener on cards to flip them
    public void flipCurrentCardListener() {
        // If card is available --> then we can flip
        for (ImageView card : displayedCards) {
            card.setOnClickListener(view -> {
                // flipCard(card);
            });
        }
    }


    public void flipCard(Card cardFlip) {
        String currentBackImage = cardFlip.getCurrentCardBack();
        ImageView currentCardSide = null;
        for (ImageView iView : displayedCards
        ) {
            if (iView.getId() == cardFlip.getImageViewID()) { //hier wird zb die id von der imageview mit der karten id gecheckt
                currentCardSide = iView;
            }
        }

        final int flipedImageRessource =
                this.getResources()
                        .getIdentifier(
                                currentBackImage, "drawable", this.getApplicationContext().getPackageName());

        assert currentCardSide != null;
        currentCardSide.setImageResource(flipedImageRessource);

        cardFlip.setFront(!cardFlip.isFront());
    }

    public void highlightCards(Card card) {
        final int imageRessourceID =
                this.getResources()
                        .getIdentifier(
                                "cardborder", "drawable", this.getApplicationContext().getPackageName());

        for (ImageView iView : displayedCards) {
            if (iView.getId() == card.getImageViewID()) { //hier wird zb die id von der imageview mit der karten id gecheckt
                iView.setForeground(this.getResources().getDrawable(R.drawable.cardborder));
            }
        }
    }


    ///////////////////////// dice methods ////////////////////////

    public void setUpDice() {
        Log.d("setUpDice", "called");
        dicePopUpActivity = new DicePopUpActivity(this, this, new Handler(handlerThread.getLooper()));

        for (int i = 0; i < availableDices; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.dice);
            activityGameboardBinding.availableDiceContainer.addView(imageView);
        }

            activityGameboardBinding.btnRollDice.setEnabled(true);
            activityGameboardBinding.savedDicesContainer.removeAllViews();
            activityGameboardBinding.parkedDicesContainer.removeAllViews();

    }

    public void startDiceRolling(View view) {
        dicePopUpActivity.showAtLocation(view, Gravity.CENTER, 0, 0);
        try {
            dicePopUpActivity.rollDice(player.getDiceCount());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void diceResults(ArrayList<Integer> playerDice, ArrayList<Integer> enemyDice) {
        // Anzeigen und Selektieren der gespeicherten Würfel
        runOnUiThread(() -> {
            final Map<ImageView, Boolean> diceSelect = new HashMap<>();
            ArrayList<Integer> selectedDices = new ArrayList<>();

            activityGameboardBinding.savedDicesContainer.removeAllViews();
/*
            for (int diceValue : player.getParkedDices()) {
                ImageView imageView = new ImageView(Gameboard.this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                imageView.setPadding(3, 3, 3, 3);
                imageView.setImageResource(getDiceImage(diceValue));
                activityGameboardBinding.parkedDicesContainer.addView(imageView);
            }
 */
            for (int diceValue : playerDice) {
                ImageView imageView = new ImageView(Gameboard.this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                imageView.setPadding(3, 3, 3, 3);
                imageView.setTag(diceValue);
                imageView.setImageResource(getDiceImage(diceValue));
                activityGameboardBinding.savedDicesContainer.addView(imageView);
                diceSelect.put(imageView, false);

                imageView.setOnClickListener(v -> {
                    int diceIndex = activityGameboardBinding.savedDicesContainer.indexOfChild(v);

                    Log.d("index of clicked dice", "diceIndex: " + diceIndex);
                    Log.d("clicked image view", imageView.toString());

                    diceSelect.put((ImageView) v, !diceSelect.get(v));

                    if (diceSelect.get(v)) {
                        v.setBackgroundColor(Color.GREEN);
                        selectedDices.add(diceValue);
                        Log.d("selected saved dice", "set to green: " + diceIndex);
                    } else {
                        v.setBackgroundColor(Color.TRANSPARENT);
                        if (selectedDices.size() != 0) {
                            selectedDices.remove(Integer.valueOf(diceValue));
                        }
                        Log.d("unselected saved dice", "set to standard: " + diceIndex);
                    }
                    // Aktualisierung im UI Thread - zur korrekten Anzeige der Hintergrundfarbe
                    v.post(() -> v.invalidate());
                });
            }

            Log.d("display selected dices", "Image Views created " + playerDice.size());

            activityGameboardBinding.savedDicesContainer.invalidate();
            activityGameboardBinding.savedDicesContainer.requestLayout();

            if (player.getParkDiceCount() > 0) {
                activityGameboardBinding.btnParkDice.setText("parken");
                isParkBtn = 1;
            }

            activityGameboardBinding.btnParkDice.setOnClickListener(view -> {
                boolean hasParked = false;
                ArrayList<Integer> parkedDices = new ArrayList<>();

                if (isParkBtn == 1) {
                    if (checkDiceParking(diceSelect, player.getParkDiceCount())) {
                        Log.d("selected saved dices", selectedDices.toString());

                        for (int diceValue : selectedDices) {
                            ImageView imageView = new ImageView(Gameboard.this);
                            imageView.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                            imageView.setPadding(3, 3, 3, 3);
                            imageView.setImageResource(getDiceImage(diceValue));
                            activityGameboardBinding.parkedDicesContainer.addView(imageView);
                            parkedDices.add(diceValue);
                            playerDice.remove(Integer.valueOf(diceValue));

                            for (int i = 0; i < activityGameboardBinding.savedDicesContainer.getChildCount(); i++) {
                                ImageView imageViewToDelete = (ImageView) activityGameboardBinding.savedDicesContainer.getChildAt(i);
                                int value = (int) imageViewToDelete.getTag();

                                if (value == diceValue) {
                                    activityGameboardBinding.savedDicesContainer.removeView(imageViewToDelete);
                                }
                            }

                            hasParked = true;
                        }

                        activityGameboardBinding.savedDicesContainer.invalidate();
                        activityGameboardBinding.savedDicesContainer.requestLayout();
                        activityGameboardBinding.parkedDicesContainer.invalidate();
                        activityGameboardBinding.parkedDicesContainer.requestLayout();

                        Log.d("dices parked", parkedDices.toString());
                        Log.d("player dices", playerDice.toString());
                        parkedDiceValues(parkedDices);
                    } else {
                        Toast.makeText(Gameboard.this, "Du willst eine zu hohe Anzahl an Würfeln parken!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    activityGameboardBinding.btnRollDice.setText("end turn");
                }
                if (hasParked) {
                    activityGameboardBinding.btnParkDice.setText("end rolling");
                    isParkBtn = 0;
                }
            });

            diceIsRolled = true;

            if (diceIsRolled) {
                if (!player.isReRoll()) {
                    activityGameboardBinding.btnParkDice.setText("end rolling");
                    isParkBtn = 0;
                }
                addCardsToPlayer();
                // call function to flip current card
                // flipCurrentCardListener();
                try {
                    c.checkIfHighlight(c.getItemsStack(), this);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                try {
                    c.checkIfHighlight(c.getRoommateEasyStack(), this);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                try {
                    c.checkIfHighlight(c.getRoommateDifficultStack(), this);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                /*
                try {
                    c.checkIfHighlight(c.getWitzigStack(), this);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                try {
                    c.checkIfHighlight(c.getWitzigWitzigStack(), this);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                 */
                try {
                    c.checkIfHighlight(c.getPlayerBlueStack(), this);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                try {
                    c.checkIfHighlight(c.getPlayerGreenStack(), this);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                try {
                    c.checkIfHighlight(c.getPlayerTealStack(), this);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                try {
                    c.checkIfHighlight(c.getPlayerOrangeStack(), this);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            testDice();
        });
    }

    public boolean checkDiceParking(Map<ImageView, Boolean> diceSelect, int parkDiceCount) {
        boolean result = false;
        int counter = 0;

        for (ImageView imageView : diceSelect.keySet()) {
            if (diceSelect.get(imageView)) {
                counter++;
            }
        }

        if (counter <= parkDiceCount) {
            result = true;
        }

        return result;
    }

    public int getDiceImage(int result) {
        switch (result) {
            default:
                return R.drawable.d1;
            case 2:
                return R.drawable.d2;
            case 3:
                return R.drawable.d3;
            case 4:
                return R.drawable.d4;
            case 5:
                return R.drawable.d5;
            case 6:
                return R.drawable.d6;
        }
    }

    public void testDice() {
        Log.d("dice callback - parked dices", this.parkedDice.toString());
    }


    ////////////////////// other methods //////////////////////////

    private void createRecyclerviewCards(RecyclerView recyclerview, int orientation, ArrayList<Card> playercards, int recyclerItem) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, orientation, false);
        recyclerview.setLayoutManager(manager);
        this.playerRecyclerviewAdabter = new MyRecyclerviewAdabter(getApplicationContext(), playercards, recyclerItem);
        activityGameboardBinding.userCardRecyclerView.setAdapter(playerRecyclerviewAdabter);
    }

    private void createRecyclerviewPlayer(RecyclerView recyclerview, int orientation, PlayerController playerController, int recyclerItem) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, orientation, false);
        recyclerview.setLayoutManager(manager);
        this.playerRecyclerviewAdabter = new MyRecyclerviewAdabter(getApplicationContext(), playerController, recyclerItem);
        activityGameboardBinding.userCardRecyclerView.setAdapter(playerRecyclerviewAdabter);
    }

    private void initCarddrawer() {
        this.c = new CardDrawer(this.getApplicationContext());
        try {
            c.generateInitialCards();
            Log.d("carddrawer", "start");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void startWindowFeature() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE); // requestWindowFeature ??
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void startClient() {
        Bundle extra = getIntent().getExtras();
        Log.d("bundle client", extra.toString());
        String ip = extra.getString("ip");
        Log.d("server ip (clientconnect)", ip);
        Client client = new Client(ip, 1234, this, new Handler(Looper.getMainLooper()));
        Log.d("player number gameboard", "?");
        client.start();
        //Toast.makeText(this, "Connected with" + ip, Toast.LENGTH_SHORT).show();
    }

    private void setListeners() {
        activityGameboardBinding.btnRollDice.setOnClickListener(view -> {
            if (activityGameboardBinding.btnRollDice.getText().equals("würfeln")) {
                startDiceRolling(view);
                // vorerst testweise auf true setzen
                hasCheated = true;
            } else {
                clientHandler.post(() -> {
                    try {
                        clientCallbacks.endTurnPlayer(this.player.getPlayerInitialCards());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }

    public void setUpCheatButtons(){
        player1btn = findViewById(R.id.player1btn);
        player2btn = findViewById(R.id.player2btn);
        player3btn = findViewById(R.id.player3btn);
    }

    @SuppressLint("SetTextI18n")
    public void startPointView(PointDisplay pointDisplay) {
        activityGameboardBinding.points.setText(getString(R.string.points_display) + pointDisplay.startPoints());
    }

    public void updatePointView(int point, PointDisplay pointDisplay) {
        activityGameboardBinding.points.setText(String.valueOf(pointDisplay.updatePoints(point)));
    }

    public void checkSpecialCards(int pralinen) {
        if (pralinen >= 10) {
            //then add one Troublemaker to current player
            //how do i know who the current player is
            //add to which arraylist
        }

        if (pralinen >= 16) {
            //then add one Schaukelstuhl to current player
            //how do i know who the current player is
            //add to which arraylist
            //how to check 1 round?
        }
    }
//    public void endTurn(){
//        checkSpecialCards(10);
//        clientHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    clientCallbacks.endTurn(player.pralinen);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }

    private void testmodus() {
        Server server = new Server(1234, new Handler(handlerThread.getLooper()));
        new Thread(server).start();
        Client client = new Client("localhost", 1234, this, new Handler(Looper.getMainLooper()));
        Log.d("player number gameboard", "?");
        client.start();
    }

    ///////////////////// callbacks //////////////////////////////////
    @Override
    public void createPlayer(int playerNumber) {
        Log.d("create player card", playerNumber + "");
        createPlayerBoard(playerNumber);

        // anzeigen der geparkten Würfel
        for (int diceValue : player.getParkedDices()) {
            ImageView imageView = new ImageView(Gameboard.this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
            imageView.setPadding(3, 3, 3, 3);
            imageView.setImageResource(getDiceImage(diceValue));
            activityGameboardBinding.parkedDicesContainer.addView(imageView);
        }
    }

    @Override
    public void getClientHändlerCallbacks(ClientCallbacks clientCallbacks, Handler clientHandler) {
        this.clientCallbacks = clientCallbacks;
        this.clientHandler = clientHandler;
    }

    @Override
    public void playerTurn(int playerNumber, ArrayList<Card> cards) {
        player.setMyTurn(1);
    }

    @Override
    public void diceValues(ArrayList<Integer> playerDices, ArrayList<Integer> enemyDices) {
        player.setDiceValuesUsable(playerDices);
        player.setDiceValuesNotUsable(enemyDices);
    }

    @Override
    public void parkedDiceValues(ArrayList<Integer> parkedDices) {
        this.parkedDice = parkedDices;
        player.setParkedDices(parkedDices);
    }

    @Override
    public void diceEnemy(ArrayList<Integer> diceEnemy) throws IOException {
        player.setDiceValuesNotUsable(diceEnemy);
        dicePopUpActivity.visualizeDice(player.getDiceValuesNotUsable());
        if (!cheated){
            cheatFunction();
        }
    }

    @Override
    public void diceFirstAccept(int message) {
        if (message == 1) {
            this.player.setPlayerTurn(message);
        }
    }

    @Override
    public void sendDiceEnemyAccept(int accept) {
        clientHandler.post(() -> {
            try {
                clientCallbacks.acceptDice(accept);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Log.d("senddice enemy", "" + accept);
    }

    @Override
    public void sendedEnemyDice(ArrayList<Integer> enemyDice) {
        dicePopUpActivity.setIsEnemyDice(1);
        dicePopUpActivity.visualizeDice(enemyDice);
    }

    @Override
    public void cheatFunction() {
        CheatFunction cheatFunction = new CheatFunction(this, this.clientHandler, this.clientCallbacks);
        cheatFunction.registerSensor();
    }

    @Override
    public void cheatPopUpActivity(int cheatedPlayer) {
        if (cheatCounter < 1) {
            cheatCounter ++;
            CheatPopUpActivity cheatPopUpActivity = new CheatPopUpActivity(this);
            cheatPopUpActivity.showAtLocation(view, Gravity.CENTER, 0, 0);
            cheatPopUpActivity.setCheatingPlayer(cheatedPlayer);
            player1btn.setOnClickListener(view -> {
                if (cheatPopUpActivity.cheatingPlayer(1)) {
                    clientHandler.post(() -> {
                        try {
                            clientCallbacks.reduceDiceOfCheater(cheatedPlayer);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    cheatPopUpActivity.dismiss();
                    startDiceRolling(view);
                } else {
                    cheatPopUpActivity.dismiss();
                    this.player.setDiceCount(this.player.getDiceCount() - 1);
                    startDiceRolling(view);
                }
            });
            player2btn.setOnClickListener(view -> {
                if (cheatPopUpActivity.cheatingPlayer(2)) {
                    clientHandler.post(() -> {
                        try {
                            clientCallbacks.reduceDiceOfCheater(cheatedPlayer);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    cheatPopUpActivity.dismiss();
                    startDiceRolling(view);
                } else {
                    cheatPopUpActivity.dismiss();
                    this.player.setDiceCount(this.player.getDiceCount() - 1);
                    startDiceRolling(view);
                }
            });
            player3btn.setOnClickListener(view -> {
                if (cheatPopUpActivity.cheatingPlayer(3)) {
                    clientHandler.post(() -> {
                        try {
                            clientCallbacks.reduceDiceOfCheater(cheatedPlayer);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    cheatPopUpActivity.dismiss();
                    startDiceRolling(view);
                } else {
                    cheatPopUpActivity.dismiss();
                    this.player.setDiceCount(this.player.getDiceCount() - 1);
                    startDiceRolling(view);
                }
            });
        }
    }

    public DicePopUpActivity getDicePopUpActivity() {
        return dicePopUpActivity;
    }

    @Override
    public void reduceDiceCheatingPlayer() {
        this.player.setDiceCount(this.player.getDiceCount()-1);
    }
}

