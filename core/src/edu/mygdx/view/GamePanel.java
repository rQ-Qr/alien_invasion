package edu.mygdx.view;

import edu.mygdx.model.Alien;
import edu.mygdx.model.Bullet;
import edu.mygdx.model.GameModel;
import edu.mygdx.model.Ship;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
        drawBullets(g);
        drawAliens(g);
    }

    protected void drawShip(Graphics g) {
        Ship ship = models.getShip();
        g.drawImage(ship.getImage(), ship.getX(), ship.getY(), ship.getSize(), ship.getSize(), this);
    }

    protected void drawBullets(Graphics g) {
        List<Bullet> bullets = models.getBullets();
        for(Bullet bullet : bullets) {
            g.setColor(new Color(60, 60, 60));
            g.fillRect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        }
    }

    protected void drawAliens(Graphics g) {
        List<Alien> aliens = models.getAliens();
        int size = aliens.size();
        for(int i=0; i<size; i++) {
            Alien alien = aliens.get(i);
            g.drawImage(alien.getImage(), alien.getX(), alien.getY(), alien.getSize(), alien.getSize(), this);
        }
    }
}
