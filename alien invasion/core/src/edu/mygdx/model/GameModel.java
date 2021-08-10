package edu.mygdx.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    Ship ship;
    List<Bullet> bullets;
    List<Alien> aliens;
    Bonus bonus;
    public GameStats stats;


    public GameModel() {
        this.stats = new GameStats();
        this.ship = new Ship(Setting.shipX, Setting.shipY);
        bullets = new ArrayList<>();
        createAliens();
        this.bonus = new Bonus(Setting.bonusX, Setting.bonusY);
    }

    public Ship getShip() {
        return ship;
    }

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

    public void createBonus() {
        Bonus bonus = new Bonus(ship.getX()+ship.getSize()/2-1, ship.getY()-950);
    }

    public void addBullet() {
        Bullet bullet = new Bullet(ship.getX()+ship.getSize()/2-1, ship.getY()-15,
                Setting.bulletWidth, Setting.bulletHeight);
        bullets.add(bullet);
    }

    private int getAliensCols() {
        int availableSpace = Setting.screenWidth - 2 * Setting.alienSize;
        return availableSpace / (2 * Setting.alienSize);
    }

    private int getAliensRows() {
        int availableSpace = Setting.screenHeight - 3 * Setting.alienSize - Setting.shipSize;
        return availableSpace / (2 * Setting.alienSize);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public Bonus getBonus(){return bonus;}

}

