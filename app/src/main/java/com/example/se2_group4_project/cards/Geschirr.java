package com.example.se2_group4_project.cards;

public class Geschirr {

   private int countRoll = 1;
  private boolean rollAgain = false;

    public boolean isAvailable(int[] dice){
        for(int i=0; i < 3; i++){
         if(dice[i] != 0 && dice[i+1] != 0 && dice[i+2] != 0){
             return true;
         }
        }
        return false;
    }

    public boolean checkRollAgain(){
        return rollAgain;
    }

    public int getCountRoll() {
        return countRoll;
    }

    public void setCountRoll(int countRoll) {
        this.countRoll = countRoll;
    }

    public boolean isRollAgain() {
        return rollAgain;
    }

    public void setRollAgain(boolean rollAgain) {
        this.rollAgain = rollAgain;

        if(rollAgain){
            setCountRoll(2);
        }
        else{
            setCountRoll(1);
        }
    }
}
