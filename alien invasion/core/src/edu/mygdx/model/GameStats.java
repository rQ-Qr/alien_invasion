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
    User user;

    public GameStats() {
        gameState = true;
        score = 0;
        highScore = 0;
        level = 1;
        resetStats();
    }

    public void updateStats() {
        if(user.getScore()!=null) {
            this.highScore = (int)(long)user.getScore();
        }
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

    public void gainBonus(){
        shipNum += 1;
        int x = Setting.screenWidth - (shipNum) * 40;
        Ship ship = new Ship(x, 10, 30);
        ships.add(ship);
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

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getLevel() {
        return level;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
