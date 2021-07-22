package edu.mygdx.model;

public class GameStats {
    int shipNum;

    public GameStats() {
        resetStats();
    }

    public boolean checkState() {
        return shipNum>=0;
    }

    public void die() {
        shipNum -= 1;
    }

    public void resetStats() {
        this.shipNum = Setting.shipLimit;
    }
}
