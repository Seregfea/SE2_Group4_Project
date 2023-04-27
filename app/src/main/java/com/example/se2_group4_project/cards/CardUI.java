package com.example.se2_group4_project.cards;

import android.widget.ImageView;

public class CardUI {
    ImageView imageView;

    public CardUI(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setRotation(float rotation) {
        imageView.setRotation(rotation);
    }
}