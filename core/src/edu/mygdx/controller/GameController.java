package edu.mygdx.controller;

import edu.mygdx.model.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController implements KeyListener, MouseListener {
    GameModel models;
    public GameController(GameModel models) {
        this.models = models;
    }

    public void checkState() {
        updateShip();
        updateBullets();
        updateAliens();
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
            bullets.remove(bullet);
        }
        for(Alien alien : alienSet) {
            aliens.remove(alien);
        }
        if(aliens.size()==0) {
            bullets.clear();
            models.createAliens();
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
                shipHit(ship);
                break;
            }
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
            if(bullets.size()<3) {
                Ship ship = models.getShip();
                Bullet bullet = new Bullet(ship.getX()+ship.getSize()/2-1, ship.getY()-15,
                        3, 15);
                bullets.add(bullet);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==37) models.getShip().setLeft(false);
        if(e.getKeyCode()==39) models.getShip().setRight(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
