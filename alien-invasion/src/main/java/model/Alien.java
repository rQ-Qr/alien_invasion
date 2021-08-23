package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * This is Alien model file for building the game.
 *
 * @author Ran Qin, Shenquan Wang
 * @version 1.0
 * @since 08/13/2021
 */
public class Alien {
    /**
     * This is the filed for Alien model file.
     */
    private Image image;
    private int x;
    private int y;
    private int size = Setting.alienSize;

    /**
     * This method gets the image from the alien.
     * @return image image of the alien
     */
    public Image getImage() {
        return image;
    }

    /**
     * This method determines the horizontal movement.
     */
    public void move() {
        x += Setting.alienDirection * Setting.alienSpeedFactor;
    }

    /**
     *  This method determines the vertical movement.
     */
    public void down() {
        y += Setting.alienDropSpeed;
    }

    /**
     * This method checks if alien reaches the edges of the screen.
     * @return true if alien does not reach the edges of the screen.
     */
    public boolean checkEdges() {
        if(x<=0 || x>=Setting.screenWidth-Setting.alienSize)
            return true;
        else return false;
    }

    /**
     * This method returns horizontal location coordinate.
     * @return x horizontal location coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * This method returns vertical location coordinate.
     * @return y vertical location coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * This method returns the size of the alien
     * @return size size of the alien.
     */
    public int getSize() {
        return size;
    }

    private int boundary = 1000;
    private boolean isLeft = false;
    private boolean isRight = false;

    /**
     * This is the constructor for alien.
     * @param x horizontal location coordinate.
     * @param y vertical location coordinate.
     */
    public Alien(int x, int y) {
        try{
            URL url = this.getClass().getResource("/assets/figures/alien.png");
            ImageIcon imageIcon = new ImageIcon(url);
            this.image = imageIcon.getImage();
        } catch (Exception e) {
            System.out.println(e);
        }
        this.x = x;
        this.y = y;
        boundary = 1000;
    }
}
