package edu.mygdx.model;

import java.util.ArrayList;
import java.util.List;

public class GameStats {
    int shipNum;
    int score;
    int highScore;
    int level;
    boolean gameState;
    List<Ship> ships;

    public GameStats() {
        gameState = true;
        score = 0;
        highScore = 0;
        level = 1;
        resetStats();
    }

    public void creatShips() {
        ships = new ArrayList<>();
        for (int i = 0; i < shipNum; i++) {
            int x = Setting.screenWidth - (i + 1) * 40;
            int y = 10;
            Ship ship = new Ship(x, y, 30);
            ships.add(ship);
        }
    }

    public boolean getState() {
        return gameState;
    }

    public void levelUp() {
        level++;
    }

    public void die() {
        shipNum -= 1;
        ships.remove(shipNum);
        gameState = shipNum>0;
    }

    public void resetStats() {
        gameState = true;
        this.shipNum = Setting.shipLimit;
        this.score = 0;
        this.level = 1;
        creatShips();
    }

    public void addPoints() {
        score += Setting.alienPoints;
        if(highScore<score) highScore = score;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getLevel() {
        return level;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
