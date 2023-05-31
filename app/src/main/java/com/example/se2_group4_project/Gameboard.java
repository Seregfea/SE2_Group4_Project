package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.se2_group4_project.backend.callbacks.ServerUICallbacks;
import com.example.se2_group4_project.callbacks.DiceCallbacks;
import com.example.se2_group4_project.backend.callbacks.ServerCallbacks;

// Please have a look at this 
// import com.example.se2_group4_project.backend.callbacks.ServerUICallbacks;
import com.example.se2_group4_project.backend.client.Client;
import com.example.se2_group4_project.cards.Item;
import com.example.se2_group4_project.cards.RoommateDifficult;

import com.example.se2_group4_project.backend.client.Client;
import com.example.se2_group4_project.dices.DicePopUpActivity;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.CardDrawer;
import com.example.se2_group4_project.gameboard_layouts.ItemCardsLayout;
import com.example.se2_group4_project.gameboard_layouts.UserCardsLayout;
import com.example.se2_group4_project.pointDisplay.PointDisplay;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




// Please have a look at this 
public class Gameboard extends AppCompatActivity implements ServerUICallbacks, DiceCallbacks {

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

    private DicePopUpActivity dicePopUpActivity;
    private LinearLayout availableDiceLayout;
    private Button btnRollDice;

    // hardcoded
    // später: methode aus player-klasse
    private int availableDices = 4;
    private TextView pointView;
    private LinearLayout savedPlayerDices;

    private LinearLayout cardsStacks;

    private LinearLayout itemCardsLayout;
    private LinearLayout userCardsLayout;
    private LinearLayout roommateEasyLayout;
    private LinearLayout roommateDifficultLayout;
    private LinearLayout witzigLayout;
    private LinearLayout witzigWitzigLayout;
    private LinearLayout troublemakerLayout;

    private boolean diceIsRolled = false;

    private boolean hasCheated = false;

    private Card card;

    private static List<ImageView> displayedCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_gameboard);


        dicePopUpActivity = new DicePopUpActivity(this);
        dicePopUpActivity.setDiceCallbacks(this);
        btnRollDice = findViewById(R.id.btnRollDice);
        availableDiceLayout = findViewById(R.id.availableDiceContainer);
        savedPlayerDices = findViewById(R.id.savedDicesContainer);
        pointView = findViewById(R.id.points);

        cardsStacks = findViewById(R.id.cardStacks);
        itemCardsLayout = findViewById(R.id.ItemCardsLayout);
        userCardsLayout = findViewById(R.id.UserCardsLayout);
        roommateEasyLayout = findViewById(R.id.roommateEasyLayout);
        roommateDifficultLayout = findViewById(R.id.roommateDifficultLayout);
        witzigLayout = findViewById(R.id.witzigLayout);
        witzigWitzigLayout = findViewById(R.id.witzigWitzigLayout);
        troublemakerLayout = findViewById(R.id.troublemakerLayout);


        // available Dice Layout
        for (int i = 0; i < availableDices; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.dice);
            availableDiceLayout.addView(imageView);
        }

        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDiceRolling(view);
                // testweise auf true setzen
                hasCheated = true;
            }
        });

        /*
        btnRollDice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasCheated) {
                    startDiceRolling(view);
                } else {
                    System.out.println("not cheated");
                }
            }
        });

         */


        // Client connects to server
        Bundle extra = getIntent().getExtras();
        String ip = extra.getString("ip");
        Client client = new Client(ip, 1234, this);
        Toast.makeText(this, "Connected with" + ip, Toast.LENGTH_SHORT).show();
        //client.start();

        PointDisplay pointDisplay = new PointDisplay();
        startPointView(pointDisplay);

        CardDrawer c = new CardDrawer(this.getApplicationContext());
        try {
            c.generateInitialCards();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        addCardsToLinearLayout(R.id.CardsLayoutLeft, c.getPlayerBlueStack());
        addCardsToLinearLayout(R.id.CardsLayoutTop, c.getPlayerGreenStack());
        addCardsToLinearLayout(R.id.CardsLayoutRight, c.getPlayerOrangeStack());
        addCardsToLinearLayout(R.id.UserCardsLayout, c.getPlayerTealStack());

        addTopCardToLinearLayout(R.id.roommateDifficultLayout, c.getRoommateDifficultStack());
        addTopCardToLinearLayout(R.id.roommateEasyLayout, c.getRoommateEasyStack());
        addTopCardToLinearLayout(R.id.witzigLayout, c.getWitzigStack());
        addTopCardToLinearLayout(R.id.witzigWitzigLayout, c.getWitzigWitzigStack());
        addTopCardToLinearLayout(R.id.troublemakerLayout, c.getTroublemakerStack());

        addCardsToLinearLayout(R.id.ItemCardsLayout, c.getItemsStack());
        //addCardsToLinearLayout(R.id.SchaukelstuhlLayout, c.getSchaukelstuhl); //Schaukelstuhl von Verena

    }

    public void addCardsToPlayer(){
        CardDrawer cardDrawer = new CardDrawer(this.getApplicationContext());

        try {
            cardDrawer.generateInitialCards();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(int i = 0; i < cardDrawer.getItemsStack().size(); i++){
            final ImageView itemCardImage = (ImageView) itemCardsLayout.getChildAt(i);
             itemCardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemCardsLayout.removeView(itemCardImage);
                    userCardsLayout.addView(itemCardImage);
                }
             });
        }

    }


    public void addTopCardToLinearLayout(int linearLayoutId, ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            LinearLayout linearLayout = findViewById(linearLayoutId);
            ImageView iView = new ImageView(linearLayout.getContext());

            String currentCardFront = card.getCurrentCardFront();

            final int imageRessourceID =
                    this.getResources()
                            .getIdentifier(
                                    currentCardFront, "drawable", this.getApplicationContext().getPackageName());

            iView.setImageResource(imageRessourceID);
            linearLayout.addView(iView);
            displayedCards.add(iView);
            card.setImageViewID(iView.getId());

            iView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    linearLayout.removeView(iView);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.weight = 5;
                    iView.setLayoutParams(params);

                    userCardsLayout.addView(iView);
                }
            });
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

            float aspectRatio = 5;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = aspectRatio ;

            if (linearLayoutId == R.id.ItemCardsLayout){
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
    public void flipCurrentCardListener(){
        // If card is available --> then we can flip
        for (ImageView card : displayedCards){
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // flipCard(card);
                }
            });
        }
    }


    public void flipCard(Card cardFlip){
        String currentBackImage = cardFlip.getCurrentCardBack();
        ImageView currentCardSide = null;
        for (ImageView iView: displayedCards
             ) {
            if(iView.getId() == cardFlip.getImageViewID()) {
                currentCardSide = iView;
            }
        }

        final int flipedImageRessource =
        this.getResources()
                .getIdentifier(
                        currentBackImage, "drawable",this.getApplicationContext().getPackageName());

        currentCardSide.setImageResource(flipedImageRessource);

        cardFlip.setFront(!cardFlip.isFront());
    }


    public void startDiceRolling(View view) {
        dicePopUpActivity.showAtLocation(view, Gravity.CENTER, 0, 0);
        try {
            dicePopUpActivity.rollDice();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void startPointView(PointDisplay pointDisplay){
        pointView.setText(String.valueOf("Points: "+pointDisplay.startPoints()));
    }

    public void updatePointView(int point, PointDisplay pointDisplay){
        pointView.setText(String.valueOf(pointDisplay.updatePoints(point)));
    }

    @Override
    public void onMessageSend(String send) {

    }

    @Override
    public void onMessageRecieve(String recieve) {

    }

    @Override

    public void diceResults(List<Integer> playerDice, List<Integer> enemyDice) {
        // anzeigen und selektieren der gespeicherten Würfel
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Map<ImageView, Boolean> diceSelect = new HashMap<>();
                savedPlayerDices.removeAllViews();

                for (int diceValue : playerDice) {
                    ImageView imageView = new ImageView(Gameboard.this);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                    imageView.setPadding(3, 3, 3, 3);
                    imageView.setImageResource(getDiceImage(diceValue));
                    savedPlayerDices.addView(imageView);
                    diceSelect.put(imageView, false);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int diceIndex = savedPlayerDices.indexOfChild(v);

                            Log.d("index of clicked dice", "diceIndex: " + diceIndex);
                            Log.d("clicked image view", imageView.toString());

                            diceSelect.put((ImageView) v, !diceSelect.get(v));

                            if (diceSelect.get(v)) {
                                v.setBackgroundColor(Color.GREEN);
                                Log.d("selected saved dice", "set to green: " + diceIndex);
                            } else {
                                v.setBackgroundColor(Color.TRANSPARENT);
                                Log.d("unselected saved dice", "set to standard: " + diceIndex);
                            }

                            // Aktualisierung im UI Thread - zur korrekten Anzeige der Hintergrundfarbe
                            v.post(new Runnable() {
                                @Override
                                public void run() {
                                    v.invalidate();
                                }
                            });
                        }
                    });
                }

                Log.d("display selected dices","Image Views created " + playerDice.size());

                savedPlayerDices.invalidate();
                savedPlayerDices.requestLayout();

                diceIsRolled = true;

                if (diceIsRolled){
                    addCardsToPlayer();
                    // call function to flip current card
                    // flipCurrentCardListener();
                }
            }
        });
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

    public void messageToAll(String message) {

    }
}

