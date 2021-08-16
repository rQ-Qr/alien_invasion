package edu.mygdx.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is game model file for other model file additional functions
 *
 * @author Ran Qin, Shenquan Wang
 * @version 1.0
 * @since 08/14/2021
 */
public class GameModel {
    /**
     * This is the field for Game Model
     */
    Ship ship;
    List<Bullet> bullets;
    List<Alien> aliens;
    Bonus bonus;
    /**
     * public field
     */
    public GameStats stats;

    /**
     * This is the constructor for Game Model
     */
    public GameModel() {
        this.stats = new GameStats();
        this.ship = new Ship(Setting.shipX, Setting.shipY);
        bullets = new ArrayList<>();
        createAliens();
        this.bonus = new Bonus(Setting.bonusX, Setting.bonusY);
    }

    /**
     * This is the getter function for ship
     *
     * @return ship the ship object
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * This method creates aliens for the game.
     */
    public void createAliens() {
        List<Alien> list = new ArrayList<>();
        int aliensCols = getAliensCols();
        int aliensRows = getAliensRows();
        for(int i=0; i<aliensRows; i++) {
            for(int j=0; j<aliensCols; j++) {
                int x = Setting.alienSize + j * 2 * Setting.alienSize;
                int y = Setting.alienSize + i * 2 * Setting.alienSize;
                list.add(new Alien(x, y));
            }
        }

        this.aliens = list;
    }

    /**
     * This method creates bonus for the game.
     */
    public void createBonus() {
        Bonus bonus = new Bonus(ship.getX()+ship.getSize()/2-1, ship.getY()-950);
    }

    /**
     * This method adding bullets to the game.
     */
    public void addBullet() {
        Bullet bullet = new Bullet(ship.getX()+ship.getSize()/2-1, ship.getY()-15,
                Setting.bulletWidth, Setting.bulletHeight);
        bullets.add(bullet);
    }

    /**
     * This method returns aliens column locations.
     * @return int column location
     */
    private int getAliensCols() {
        int availableSpace = Setting.screenWidth - 2 * Setting.alienSize;
        return availableSpace / (2 * Setting.alienSize);
    }

    /**
     * This method returns aliens row locations.
     * @return int row location
     */
    private int getAliensRows() {
        int availableSpace = Setting.screenHeight - 3 * Setting.alienSize - Setting.shipSize;
        return availableSpace / (2 * Setting.alienSize);
    }

    /**
     * This is the getter function for bullet.
     * @return bullets the bullets for the game.
     */
    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * This is the getter function for aliens.
     * @return aliens the aliens for the game.
     */
    public List<Alien> getAliens() {
        return aliens;
    }

    /**
     * This is the getter function for bonus.
     * @return bonus the bonus for the game.
     */
    public Bonus getBonus(){return bonus;}
}

