package com.example.se2_group4_project.gameboard_layouts;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.se2_group4_project.R;

public class CardsLayoutLeft {

    LinearLayout layout;

    public CardsLayoutLeft(LinearLayout layout) {
        this.layout = layout;
    }

    public void addImage(ImageView imageView) {
        layout.addView(imageView);
    }
}
