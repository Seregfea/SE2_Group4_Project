package com.example.se2_group4_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.se2_group4_project.cards.Card;
import com.example.se2_group4_project.cards.CardDrawer;

import java.io.FileNotFoundException;

public class FlipCardsTest extends AppCompatActivity {

    private Card test;
    private ImageView testImageView;
    private Button testFLip;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipcards_test);

        testFLip = findViewById(R.id.flipTest);

        CardDrawer c = new CardDrawer(this.getApplicationContext());
        try {
            c.generateInitialCards();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        test = c.getPlayerBlueStack().get(0);
        LinearLayout linearLayout = findViewById(R.id.roommateEasyLayout);
        ImageView iView = new ImageView(linearLayout.getContext());
        String currentCardFront = test.getCurrentCardFront();
        final int imageRessourceID =
                this.getResources()
                        .getIdentifier(
                                currentCardFront, "drawable", this.getApplicationContext().getPackageName());
        iView.setImageResource(imageRessourceID);
        iView.setLayoutParams(new LinearLayout.LayoutParams(100, 300));
        linearLayout.addView(iView);
        testImageView = iView;
        test.setImageViewID(iView.getId());


        testFLip.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            flipCard(test);
        }
    });
}

    public void flipCard(Card cardFlip){
        String currentBackImage = cardFlip.getCurrentCardBack();
        ImageView currentCardSide = null;
        if(testImageView.getId() == cardFlip.getImageViewID()) {
            currentCardSide = testImageView;
        }

        final int flipedImageRessource =
                this.getResources()
                        .getIdentifier(
                                currentBackImage, "drawable",this.getApplicationContext().getPackageName());

        currentCardSide.setImageResource(flipedImageRessource);

        cardFlip.setFront(!cardFlip.isFront());
    }
}
