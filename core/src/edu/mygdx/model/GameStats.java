package edu.mygdx.model;

public class GameStats {
    int shipNum;
    boolean gameState;

    public GameStats() {
        gameState = true;
        resetStats();
    }

    public void resetGameState(){
        gameState = true;
    }

    public boolean getState() {
        return gameState;
    }


    public void die() {
        shipNum -= 1;
        gameState = shipNum>0;
    }

    public void resetStats() {
        this.shipNum = Setting.shipLimit;
    }
}
