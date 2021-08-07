package edu.mygdx.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Ship {
    private Image image;
    private int x;
    private int y;
    private int size = Setting.shipSize;
    private int boundary = Setting.screenWidth;
    private boolean isLeft = false;
    private boolean isRight = false;

    public Ship(int x, int y) {
        try{
            this.image = ImageIO.read(new File("./core/assets/figures/ship.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
        this.x = x;
        this.y = y;
    }

    public Ship(int x, int y, int size) {
        try{
            this.image = ImageIO.read(new File("./core/assets/figures/ship.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void leftMove() {
        if(x-Setting.shipSpeedFactor>=0) x -= Setting.shipSpeedFactor;
        else x = 0;
    }

    public void rightMove() {
        if(x+Setting.shipSpeedFactor<=boundary-50) x += Setting.shipSpeedFactor;
        else x = boundary-50;
    }

    public void recenter() {
        this.x = Setting.shipX;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public int getSize() {
        return size;
    }

}
