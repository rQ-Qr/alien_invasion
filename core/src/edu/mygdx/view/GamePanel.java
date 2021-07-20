package edu.mygdx.view;

import edu.mygdx.model.GameModel;
import edu.mygdx.model.Ship;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    GameModel models;
    public GamePanel(GameModel models) {
        this.models = models;
        this.setFocusable(true);
    }

    public void paint() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawShip(g);
    }

    protected void drawShip(Graphics g) {
        Ship ship = models.getShip();
        g.drawImage(ship.getImage(), ship.getX(), ship.getY(), ship.getSize(), ship.getSize(), this);
    }
}
