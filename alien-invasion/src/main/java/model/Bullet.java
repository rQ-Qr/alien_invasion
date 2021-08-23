package model;

import java.awt.*;

/**
 * This is Bullet model file for building bullet
 *
 * @author Ran Qin, Shenquan Wang
 * @version 1.0
 * @since 08/13/2021
 */
public class Bullet {
    /**
     * This is the filed for Alien model file.
     */
    int x;
    int y;
    int width;
    int height;
    Color color;

    /**
     * This method determines the vertical movement.
     */
    public void move() {
        y -= Setting.bulletSpeedFactor;
    }

    /**
     * This is the constructor for bullet.
     * @param x horizontal location coordinate.
     * @param y vertical location coordinate.
     * @param width width of the bullet.
     * @param height height of the bullet.
     */
    public Bullet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = Setting.bulletColor;
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
     * This method return width of bullet.
     * @return width the width of bullet.
     */
    public int getWidth() {
        return width;
    }

    /**
     * This method return height of bullet.
     * @return width the height of bullet.
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method return color of bullet.
     * @return width the color of bullet.
     */
    public Color getColor() {
        return color;
    }
}
