package edu.mygdx.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Game Stats file for building the game.
 *
 * @author Ran Qin, Shenquan Wang
 * @version 1.0
 * @since 08/14/2021
 */
public class GameStats {
    /**
     * This is the filed for Game Stats file.
     */
    int shipNum;
    int score;
    int highScore;
    int level;
    boolean gameState;
    List<Ship> ships;
    User user;

    /**
     * This is the constructor for this file
     */
    public GameStats() {
        gameState = true;
        score = 0;
        highScore = 0;
        level = 1;
        resetStats();
    }

    /**
     * This method is to update hte states.
     */
    public void updateStats() {
        if(user.getScore()!=null) {
            this.highScore = (int)(long)user.getScore();
        }
    }

    /**
     * This method is to creat ship for this game.
     */
    public void creatShips() {
        ships = new ArrayList<>();
        for (int i = 0; i < shipNum; i++) {
            int x = Setting.screenWidth - (i + 1) * 40;
            int y = 10;
            Ship ship = new Ship(x, y, 30);
            ships.add(ship);
        }
    }

    /**
     * This method returns the state of the game.
     *
     * @return Ture when game is going
     */
    public boolean getState() {
        return gameState;
    }

    /**
     * This method counts the level number for the game.
     */
    public void levelUp() {
        level++;
    }

    /**
     * This method determines number of lives of the player.
     */
    public void die() {
        shipNum -= 1;
        ships.remove(shipNum);
        gameState = shipNum>0;
    }

    /**
     * This method up player's level by 1 when the player received the bonus.
     */
    public void gainBonus(){
        shipNum += 1;
        int x = Setting.screenWidth - (shipNum) * 40;
        Ship ship = new Ship(x, 10, 30);
        ships.add(ship);
    }

    /**
     * This method reset the state of the game.
     */
    public void resetStats() {
        gameState = true;
        this.shipNum = Setting.shipLimit;
        this.score = 0;
        this.level = 1;
        creatShips();
    }

    /**
     * This method increase the scores and high core of the player
     */
    public void addPoints() {
        score += Setting.alienPoints;
        if(highScore<score) highScore = score;
    }

    /**
     * This is the getter function for score.
     * @return score from the game
     */
    public int getScore() {
        return score;
    }

    /**
     * This is the getter function for high score.
     * @return highScore the highest score for the game.
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * This is the setting function for high score.
     * @param highScore the highest score for the game.
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * This is the getter function for level number.
     * @return level the level number of the game.
     */
    public int getLevel() {
        return level;
    }

    /**
     * This is the getter function for ship.
     * @return ships the ship of the game.
     */
    public List<Ship> getShips() {
        return ships;
    }

    /**
     * This is the getter function for user.
     * @return user the user of the game.
     */
    public User getUser() {
        return user;
    }

    /**
     * This is the setter function for user.
     */
    public void setUser(User user) {
        this.user = user;
    }

}
