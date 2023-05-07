package com.example.se2_group4_project.pointDisplay;

import android.content.Context;
import android.widget.TextView;

public class PointDisplay {

    private int points;

    public PointDisplay(){
        this.points = 0;
    }

    //start with 0 points
    public int startPoints(){
        return points;
    }

    //update your points
    public int updatePoints(int point){
        if (this.points+point < 0){
            this.points = 0;
            return points;
        }
        this.points = this.points + point;
        return points;
    }
}
