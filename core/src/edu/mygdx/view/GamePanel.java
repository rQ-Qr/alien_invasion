package edu.mygdx.view;

import edu.mygdx.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {
    GameModel models;
    public GamePanel(GameModel models) {
        this.models = models;
        super.setFocusable(true);
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
        drawScore(g);
    }

    private void drawShip(Graphics g) {
        Ship ship = models.getShip();
        g.drawImage(ship.getImage(), ship.getX(), ship.getY(), ship.getSize(), ship.getSize(), this);
    }

    private void drawBullets(Graphics g) {
        List<Bullet> bullets = models.getBullets();
        for(Bullet bullet : bullets) {
            g.setColor(bullet.getColor());
            g.fillRect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        }
    }

    private void drawAliens(Graphics g) {
        List<Alien> aliens = models.getAliens();
        for(int i=0; i<aliens.size(); i++) {
            Alien alien = aliens.get(i);
            g.drawImage(alien.getImage(), alien.getX(), alien.getY(), alien.getSize(), alien.getSize(), this);
        }
    }

    private void drawScore(Graphics g) {
        String score = String.valueOf(models.stats.getScore());
        String highScore = String.valueOf(models.stats.getHighScore());
        String level = String.valueOf(models.stats.getLevel());
        g.setFont(new Font(" ",Font.BOLD,20));
        g.drawString(score, 20, 25);
        g.drawString(level, 20, 50);
        g.drawString(highScore, 480, 25);
        for(Ship ship : models.stats.getShips()) {
            g.drawImage(ship.getImage(), ship.getX(), ship.getY(), ship.getSize(), ship.getSize(), this);
        }
    }
}
