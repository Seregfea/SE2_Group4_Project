package com.example.se2_group4_project.gameboard_layouts;

import android.widget.LinearLayout;

public class ItemCardsLayout extends LinearLayout {
    LinearLayout itemCardsLayout;

    public ItemCardsLayout(LinearLayout itemCardsLayout) {

        super(itemCardsLayout.getContext());
        this.itemCardsLayout = itemCardsLayout;
    }
}
