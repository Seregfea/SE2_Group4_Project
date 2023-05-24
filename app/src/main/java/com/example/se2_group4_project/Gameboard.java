package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.se2_group4_project.callbacks.ClientCallbacks;
import com.example.se2_group4_project.callbacks.DiceCallbacks;
import com.example.se2_group4_project.callbacks.ServerCallbacks;
import com.example.se2_group4_project.client.Client;
import com.example.se2_group4_project.dices.Dice;
import com.example.se2_group4_project.dices.DicePopUpActivity;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.CardDrawer;
import com.example.se2_group4_project.pointDisplay.PointDisplay;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Gameboard extends AppCompatActivity implements ServerCallbacks, DiceCallbacks {
    private DicePopUpActivity dicePopUpActivity;
    private LinearLayout availableDiceLayout;
    private Button btnRollDice;
    // hardcoded
    // später: methode aus player-klasse
    private int availableDices = 4;
    private TextView pointView;
    private LinearLayout savedplayerDices;

    private LinearLayout cardsStacks;

    private LinearLayout itemCardsLayout;
    private LinearLayout userCardsLayout;
    private LinearLayout roommateEasyLayout;
    private LinearLayout roommateDifficultLayout;
    private LinearLayout witzigLayout;
    private LinearLayout witzigWitzigLayout;
    private LinearLayout troublemakerLayout;

    private boolean diceIsRolled = false;

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
        savedplayerDices = findViewById(R.id.savedDicesContainer);
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
                dicePopUpActivity.showAtLocation(view, Gravity.CENTER, 0, 0);
                try {
                    dicePopUpActivity.rollDice();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

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
        addCardsToLinearLayout(R.id.roommateDifficultLayout, c.getRoommateDifficultStack());
        addCardsToLinearLayout(R.id.roommateEasyLayout, c.getRoommateEasyStack());
        addCardsToLinearLayout(R.id.witzigLayout, c.getWitzigStack());
        addCardsToLinearLayout(R.id.witzigWitzigLayout, c.getWitzigWitzigStack());
        addCardsToLinearLayout(R.id.troublemakerLayout, c.getTroublemakerStack());
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


        // Views Recycle
        for (int i = 0; i < cardDrawer.getRoommateEasyStack().size(); i++){
            ImageView roommateEasyCardImage = (ImageView) roommateEasyLayout.getChildAt(i);

            roommateEasyCardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Clicked Roommate easy Card");
                    roommateEasyLayout.removeView(roommateEasyCardImage);
                    Log.d("Card easy remove", "Card Easy found");
                    userCardsLayout.addView(roommateEasyCardImage);
                    Log.d("Card easy remove", "Card Easy add");
                }
            });
        }

        for (int i = 0; i < cardDrawer.getRoommateDifficultStack().size(); i++){
            ImageView roommateDifficultCardImage = (ImageView) roommateDifficultLayout.getChildAt(i);

            roommateDifficultCardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Clicked Roommate difficult Card");
                    roommateDifficultLayout.removeView(roommateDifficultCardImage);
                    Log.d("Card easy remove", "Card Difficult remove");
                    userCardsLayout.addView(roommateDifficultCardImage);
                    Log.d("Card easy remove", "Card Difficult add");
                }
            });
        }

        for (int i = 0; i < cardDrawer.getWitzigStack().size(); i++){
            ImageView witzigCardImage = (ImageView) witzigLayout.getChildAt(i);

            witzigCardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Clicked Witzig Card");
                    witzigLayout.removeView(witzigCardImage);
                    Log.d("Card easy remove", "Card Difficult remove");
                    userCardsLayout.addView(witzigCardImage);
                    Log.d("Card easy remove", "Card Difficult add");
                }
            });
        }

        for (int i = 0; i < cardDrawer.getWitzigWitzigStack().size(); i++){
            ImageView witzigWitzigCardImage = (ImageView) witzigWitzigLayout.getChildAt(i);

            witzigWitzigCardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Clicked WitzigWitzig Card");
                    witzigWitzigLayout.removeView(witzigWitzigCardImage);
                    Log.d("Card easy remove", "Card Difficult remove");
                    userCardsLayout.addView(witzigWitzigCardImage);
                    Log.d("Card easy remove", "Card Difficult add");
                }
            });
        }

        for (int i = 0; i < cardDrawer.getTroublemakerStack().size(); i++){
            ImageView troublemakerCardImage = (ImageView) troublemakerLayout.getChildAt(i);

            troublemakerCardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Clicked Troublemaker Card");
                    troublemakerLayout.removeView(troublemakerCardImage);
                    Log.d("Card easy remove", "Card Difficult remove");
                    userCardsLayout.addView(troublemakerCardImage);
                    Log.d("Card easy remove", "Card Difficult add");
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
        // anzeigen der gespeicherten Würfel
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                savedplayerDices.removeAllViews();

                for (int diceValue : playerDice) {
                    ImageView imageView = new ImageView(Gameboard.this);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
                    imageView.setPadding(3, 3, 3, 3);
                    imageView.setImageResource(getDiceImage(diceValue));
                    savedplayerDices.addView(imageView);
                }
                Log.d("display selected dices","Image Views created " + playerDice.size());

                savedplayerDices.invalidate();
                savedplayerDices.requestLayout();

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
    }

}

