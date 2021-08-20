package edu.mygdx.controller;

import edu.mygdx.model.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is controller for the game.
 *
 * @author Shenquan Wang, Ran Qin
 * @version 1.0
 * @since 08/15/2021
 */
public class GameController implements KeyListener {
    /**
     * This is the filed for this file.
     */
    GameModel models;
    /**
     * This is the constructor for GameController
     * @param models the models of the game
     */
    public GameController(GameModel models) {
        this.models = models;
    }

    /**
     * This method is for checking the game state.
     */
    public void checkState() {
        updateShip();
        updateBullets();

        if(models.getAliens().size()==0) {
            models.getBullets().clear();
            models.getShip().recenter();
            models.getBonus();
            models.stats.levelUp();
            models.createAliens();
            models.createBonus();
            Setting.increaseSpeed();
        }
        else {
            updateAliens();
            updateBonus();
        }
    }

    /**
     * This method is for updating ship position.
     */
    public void updateShip() {
        if(models.getShip().isLeft()) {
            models.getShip().leftMove();
        }
        if(models.getShip().isRight()) {
            models.getShip().rightMove();
        }
    }

    /**
     * This method is for updating the bullets position.
     */
    public void updateBullets() {
        List<Bullet> bullets = models.getBullets();
        for(Bullet bullet : new ArrayList<>(bullets)) {
            if(bullet.getY()<0) bullets.remove(bullet);
        }
        for(Bullet bullet : bullets) {
            bullet.move();
        }
        checkBulletCollision(bullets);
    }

    /**
     * This method is for updating the bonus position.
     */
    public void updateBonus() {
        if(models.getBonus().getY()>0 && models.stats.getScore()>=400){
            models.getBonus().down();
        }
        checkBonusCollision();
    }

    /**
     * This method is for updating the alien position.
     */
    public void updateAliens() {
        List<Alien> aliens = models.getAliens();
        boolean changeDir = false;
        for(Alien alien : aliens) {
            if(alien.checkEdges()) {
                changeDir = true;
                break;
            }
        }
        if(changeDir) {
            Setting.alienDirection = -1 * Setting.alienDirection;
        }
        for(Alien alien : aliens) {
            alien.move();
            if(changeDir) alien.down();
        }
        checkShipCollision(aliens);
        checkAlienBottom(aliens);
    }

    /**
     * This method is for restarting the game.
     */
    public void playAgain() {
        models.stats.resetStats();
        Setting.resetSpeed();
        if(models.stats.getUser()!=null)
            RegController.updateScore(models.stats.getUser().getUid(), (long)models.stats.getHighScore());
    }

    /**
     * This method is for checking if the bullets hit the aliens.
     * @param bullets the bullets from the ship.
     */
    private void checkBulletCollision(List<Bullet> bullets) {
        List<Alien> aliens = models.getAliens();
        Set<Bullet> bulletSet = new HashSet<>();
        Set<Alien> alienSet = new HashSet<>();
        for(Bullet bullet : bullets) {
            Rectangle bulletRec = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
            for(Alien alien : aliens) {
                Rectangle alienRec = new Rectangle(alien.getX(), alien.getY(), alien.getSize(), alien.getSize());
                if(bulletRec.intersects(alienRec)) {
                    bulletSet.add(bullet);
                    alienSet.add(alien);
                }
            }
        }
        for(Bullet bullet : bulletSet) {
            Sound.playSound(Sound.alienCollision());
            bullets.remove(bullet);
        }
        for(Alien alien : alienSet) {
            aliens.remove(alien);
            models.stats.addPoints();
        }
    }

    /**
     * This method is for checking if the alien reach the bottom
     * @param aliens the aliens from the game.
     */
    public void checkAlienBottom(List<Alien> aliens) {
        for(Alien alien : aliens) {
            if(alien.getY()>=Setting.screenHeight-alien.getSize()) {
                shipHit(models.getShip());
                break;
            }
        }
    }

    /**
     * This method is for checking if the ship crash with the aliens.
     * @param aliens the aliens from the game.
     */
    private void checkShipCollision(List<Alien> aliens) {
        Ship ship = models.getShip();
        Rectangle shipRec = new Rectangle(ship.getX(), ship.getY(), ship.getSize(), ship.getSize());
        for(Alien alien : aliens) {
            Rectangle alienRec = new Rectangle(alien.getX(), alien.getY(), alien.getSize(), alien.getSize());
            if(shipRec.intersects(alienRec)) {
                Sound.playSound(Sound.explosion());
                shipHit(ship);
                break;
            }
        }
    }

    /**
     * This method is for checking if the ship received the bonus.
     */
    private void checkBonusCollision() {
        Ship ship = models.getShip();
        Bonus bonus = models.getBonus();
        Rectangle shipRec = new Rectangle(ship.getX(), ship.getY(), ship.getSize(), ship.getSize());
        Rectangle bonusRec = new Rectangle(bonus.getX(), bonus.getY(), bonus.getSize(), bonus.getSize());
            if(shipRec.intersects(bonusRec)) {
                Sound.playSound(Sound.bonusSound());
                bonus.clear();
                models.stats.gainBonus();
            }

    }

    /**
     * This method is for change the situation of after the ship hit with the aliens.
     * @param ship the ship from the game.
     */
    private void shipHit(Ship ship) {
        ship.recenter();
        models.getBullets().clear();
        models.getAliens().clear();
        models.createAliens();
        models.stats.die();
    }

    /**
     * This method is for keyboard event.
     * @param e for keyboard event.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * This method is for control the ship when key pressed.
     * @param e for keyboard event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==37) models.getShip().setLeft(true);
        if(e.getKeyCode()==39) models.getShip().setRight(true);
        if(e.getKeyCode()==32) {
            List<Bullet> bullets = models.getBullets();
            Sound.playSound(Sound.shootSound());
            if(bullets.size()<3) {
                Ship ship = models.getShip();
                models.addBullet();
            }
        }
    }

    /**
     * This method is for control the ship when key released.
     * @param e for keyboard event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==37) models.getShip().setLeft(false);
        if(e.getKeyCode()==39) models.getShip().setRight(false);
    }
}
