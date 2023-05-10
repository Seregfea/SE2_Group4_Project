package com.example.se2_group4_project.gameboard_layouts;

import android.widget.LinearLayout;

public class CardsLayoutRight extends LinearLayout  {

    LinearLayout layoutRight;

    public CardsLayoutRight(LinearLayout layoutRight) {

        super(layoutRight.getContext());
        this.layoutRight = layoutRight;
    }
}