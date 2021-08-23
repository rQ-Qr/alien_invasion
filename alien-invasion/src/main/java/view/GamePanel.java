package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * This is the game panel for game visualization which is extended from JPanel.
 *
 * @author Shenquan Wang, Ran Qin
 * @version 1.0
 * @since 08/15/2021
 */
public class GamePanel extends JPanel {
    /**
     * filed of this file.
     */
    GameModel models;

    /**
     * This is the constructor for this file.
     * @param models the game model.
     */
    public GamePanel(GameModel models) {
        this.models = models;
        super.setFocusable(true);
    }

    /**
     * This method is for repaint.
     */
    public void paint() {
        repaint();
    }

    /**
     * This method is for painting the component for the game.
     * @param g the graphics for the game visualization.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawBackground(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawShip(g);
        drawBullets(g);
        drawAliens(g);
        drawScore(g);
        drawBonus(g);

    }

    /**
     * This method is for drawing the ship.
     * @param g the graphics for the game visualization.
     */
    private void drawShip(Graphics g) {
        Ship ship = models.getShip();
        g.drawImage(ship.getImage(), ship.getX(), ship.getY(), ship.getSize(), ship.getSize(), this);
    }

    /**
     * This method is for drawing the bullets
     * @param g the graphics for the game visualization.
     */
    private void drawBullets(Graphics g) {
        List<Bullet> bullets = models.getBullets();
        for(Bullet bullet : bullets) {
            g.setColor(bullet.getColor());
            g.fillRect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
        }
    }

    /**
     * This method is for drawing the aliens.
     * @param g the graphics for the game visualization.
     */
    private void drawAliens(Graphics g) {
        List<Alien> aliens = models.getAliens();
        for(int i=0; i<aliens.size(); i++) {
            Alien alien = aliens.get(i);
            g.drawImage(alien.getImage(), alien.getX(), alien.getY(), alien.getSize(), alien.getSize(), this);
        }
    }

    /**
     * This method is for drawing the bonus.
     * @param g the graphics for the game visualization.
     */
    private void drawBonus(Graphics g) {
        Bonus bonus = models.getBonus();
        g.drawImage(bonus.getImage(), bonus.getX(), bonus.getY(), bonus.getSize(), bonus.getSize(), this);
    }

    /**
     * This method is for drawing the score board.
     * @param g the graphics for the game visualization.
     */
    private void drawScore(Graphics g) {
        String score = String.valueOf(models.stats.getScore());
        String highScore = String.valueOf(models.stats.getHighScore());
        String level = String.valueOf(models.stats.getLevel());
        g.setFont(new Font(" ",Font.BOLD,20));
        g.setColor(Color.white);
        g.drawString(score, 20, 25);
        g.drawString(level, 20, 50);
        g.drawString(highScore, 480, 25);
        for(Ship ship : models.stats.getShips()) {
            g.drawImage(ship.getImage(), ship.getX(), ship.getY(), ship.getSize(), ship.getSize(), this);
        }
    }

    /**
     * This method is for drawing the game background.
     * @param g the graphics for the game visualization.
     */
    private void drawBackground(Graphics g) throws IOException {
        URL url = this.getClass().getResource("/assets/figures/background2.png");
        ImageIcon imageIcon = new ImageIcon(url);
        Image background = imageIcon.getImage();
        Dimension size = new Dimension(1000,800);
        setSize(size);
        g.drawImage(background, 0 ,-125,null);
    }
}
