package com.example.se2_group4_project.gameboard_layouts;

import android.widget.LinearLayout;

public class UserCardsLayout extends LinearLayout{
    LinearLayout userCards;

    public UserCardsLayout(LinearLayout userCards) {
        super(userCards.getContext());
        this.userCards = userCards;

    }


}
