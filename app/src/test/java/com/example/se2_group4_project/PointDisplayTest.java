package com.example.se2_group4_project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.se2_group4_project.pointDisplay.PointDisplay;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PointDisplayTest {

    PointDisplay pointDisplay;

    @BeforeEach
    void setUp(){
        pointDisplay = new PointDisplay();
    }

    @Test
    public void pointDisplayStart() {
        int actualPoints = pointDisplay.startPoints();
        int expectedPoints = 0;
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void pointDisplayUpdatePointsPositive(){
        pointDisplay.startPoints();
        int actualPoints = pointDisplay.updatePoints(5);
        int expectedPoints = 5;
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void pointDisplayUpdatePointsNegative(){
        pointDisplay.startPoints();
        int actualPoints;
        pointDisplay.updatePoints(5);
        actualPoints = pointDisplay.updatePoints(-3);
        int expectedPoints = 2;
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void pointDisplayUpdatePointsLessThanZero(){
        pointDisplay.startPoints();
        int actualPoints = pointDisplay.updatePoints(-3);
        int expectedPoints = 0;
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void pointDisplayIntegrationTest(){
        //start of game
        int actualPoints = pointDisplay.startPoints();
        int expectedPoints = 0;
        assertEquals(expectedPoints, actualPoints);

        //first update of points
        actualPoints = pointDisplay.updatePoints(5);
        expectedPoints = 5;
        assertEquals(expectedPoints, actualPoints);

        //second update of points
        actualPoints = pointDisplay.updatePoints(-6);
        expectedPoints = 0;
        assertEquals(expectedPoints, actualPoints);

        //third update of points
        actualPoints = pointDisplay.updatePoints(10);
        expectedPoints = 10;
        assertEquals(expectedPoints, actualPoints);

        //fourth update of points
        pointDisplay.updatePoints(-3);
        pointDisplay.updatePoints(2);
        actualPoints = pointDisplay.updatePoints(-5);
        expectedPoints = 4;
        assertEquals(expectedPoints, actualPoints);

    }
}
