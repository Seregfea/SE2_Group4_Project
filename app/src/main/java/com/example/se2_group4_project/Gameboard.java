package com.example.se2_group4_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.se2_group4_project.callbacks.ClientCallbacks;
import com.example.se2_group4_project.callbacks.ServerCallbacks;
import com.example.se2_group4_project.cards.Item;
import com.example.se2_group4_project.cards.RoommateDifficult;
import com.example.se2_group4_project.client.Client;
import com.example.se2_group4_project.dices.DicePopUpActivity;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.CardDrawer;
import com.example.se2_group4_project.gameboard_layouts.ItemCardsLayout;
import com.example.se2_group4_project.gameboard_layouts.UserCardsLayout;
import com.example.se2_group4_project.pointDisplay.PointDisplay;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Gameboard extends AppCompatActivity implements ServerCallbacks {

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
    private static List<ImageView> displayedCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_gameboard);


        dicePopUpActivity = new DicePopUpActivity(this);
        btnRollDice = findViewById(R.id.btnRollDice);
        availableDiceLayout = findViewById(R.id.availableDiceContainer);
        pointView = findViewById(R.id.points);

        for (int i = 0; i < availableDices; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.dice);
            availableDiceLayout.addView(imageView);
        }

        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dicePopUpActivity.showAtLocation(view, Gravity.CENTER, 0, 0);
                dicePopUpActivity.rollDice();
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
}

