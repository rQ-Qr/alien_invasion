package edu.mygdx.controller;

import edu.mygdx.model.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController implements KeyListener {
    GameModel models;
    public GameController(GameModel models) {
        this.models = models;
    }

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

    public void updateShip() {
        if(models.getShip().isLeft()) {
            models.getShip().leftMove();
        }
        if(models.getShip().isRight()) {
            models.getShip().rightMove();
        }
    }

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

    public void updateBonus() {
        if(models.getBonus().getY()>0 && models.stats.getScore()>400){
            models.getBonus().down();
        }
        checkBonusCollision();
    }

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

    public void checkAlienBottom(List<Alien> aliens) {
        for(Alien alien : aliens) {
            if(alien.getY()>=Setting.screenHeight-alien.getSize()) {
                shipHit(models.getShip());
                break;
            }
        }
    }


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

    private void shipHit(Ship ship) {
        ship.recenter();
        models.getBullets().clear();
        models.getAliens().clear();
        models.createAliens();
        models.stats.die();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

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

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==37) models.getShip().setLeft(false);
        if(e.getKeyCode()==39) models.getShip().setRight(false);
    }
}
