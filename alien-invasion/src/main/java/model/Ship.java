package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * This is Ship model file for building the game.
 *
 * @author Ran Qin, Shenquan Wang
 * @version 1.0
 * @since 08/15/2021
 */
public class Ship {
    /**
     * This is the filed for this file.
     */
    private Image image;
    private int x;
    private int y;
    private int size = Setting.shipSize;
    private int boundary = Setting.screenWidth;
    private boolean isLeft = false;
    private boolean isRight = false;

    /**
     * This is the constructor for this file.
     * @param x horizontal location coordinate.
     * @param y vertical location coordinate.
     */
    public Ship(int x, int y) {
        try{
            URL url = this.getClass().getResource("/assets/figures/ship.png");
            ImageIcon imageIcon = new ImageIcon(url);
            this.image = imageIcon.getImage();
        } catch (Exception e) {
            System.out.println(e);
        }
        this.x = x;
        this.y = y;
    }

    /**
     * This is another constructor for this file.
     * @param x horizontal location coordinate.
     * @param y vertical location coordinate.
     * @param size the size of the ship.
     */
    public Ship(int x, int y, int size) {
        try{
            URL url = this.getClass().getResource("/assets/figures/ship.png");
            ImageIcon imageIcon = new ImageIcon(url);
            this.image = imageIcon.getImage();
        } catch (Exception e) {
            System.out.println(e);
        }
        this.x = x;
        this.y = y;
        this.size = size;
    }

    /**
     * This method is for the ship to move to the left.
     */
    public void leftMove() {
        if(x-Setting.shipSpeedFactor>=0) x -= Setting.shipSpeedFactor;
        else x = 0;
    }

    /**
     * This method is for the ship to move to the right.
     */
    public void rightMove() {
        if(x+Setting.shipSpeedFactor<=boundary-50) x += Setting.shipSpeedFactor;
        else x = boundary-50;
    }

    /**
     * This method is for recenter the ship.
     */
    public void recenter() {
        this.x = Setting.shipX;
    }

    /**
     * This is the getter method for image.
     * @return image the image of the ship.
     */
    public Image getImage() {
        return image;
    }

    /**
     * This is the getter method for horizontal location coordinate.
     * @return x the horizontal location coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * This is the getter method for vertical location coordinate.
     * @return x the vertical location coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * This method determines if the ship is moving left.
     * @return Ture when the ship is moving left.
     */
    public boolean isLeft() {
        return isLeft;
    }

    /**
     * This method sets ship to left
     * @param left boolean for left
     */
    public void setLeft(boolean left) {
        isLeft = left;
    }

    /**
     * This method determines if the ship is moving right.
     * @return Ture when the ship is moving right.
     */
    public boolean isRight() {
        return isRight;
    }

    /**
     * This method sets ship to left
     * @param right boolean for left
     */
    public void setRight(boolean right) {
        isRight = right;
    }

    /**
     * This method is a getter for size
     * @return size the size of the ship.
     */
    public int getSize() {
        return size;
    }

}
