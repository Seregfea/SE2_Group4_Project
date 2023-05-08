package com.example.se2_group4_project.cards;

import java.util.ArrayList;

public class Schaukelstuhl {


   public int sumSchnapspralinen(ArrayList<Card> player){
        int sum = 0;

       for (Card card:player) {
           sum += card.getSchnapspralinen();
       }
       return sum;
    }

    public void checkSpecialCards(){
       //Liste vom aktuellen Player

        //chrissi fragen set und get bei den points
       //int sum = sumSchnapspralinen();
//
//       if(sum >= 16){
//           enableSchaukelstuhl();
//       }
    }

    public void enableSchaukelstuhl(){
       //playerList.add zu jetztigem Spieler
    }

}
