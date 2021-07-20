package edu.mygdx.controller;

import edu.mygdx.model.GameModel;
import edu.mygdx.model.Ship;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameController implements KeyListener, MouseListener {
    GameModel models;
    public GameController(GameModel models) {
        this.models = models;
    }

    public void checkState() {
        if(models.getShip().isLeft()) {
            models.getShip().leftMove();
        }
        if(models.getShip().isRight()) {
            models.getShip().rightMove();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==37) models.getShip().setLeft(true);
        if(e.getKeyCode()==39) models.getShip().setRight(true);
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
