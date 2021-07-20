package edu.mygdx.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class Ship {
    private Image image;
    private int x;
    private int y;
    private int size = Setting.shipSize;
    private int boundary = 1000;
    private boolean isLeft = false;
    private boolean isRight = false;

    public Ship() {
        try{
            this.image = ImageIO.read(new File("D:\\project\\alien invasion\\core\\assets\\figures\\ship.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
        x = 0;
        y = 0;
        boundary = 1000;
    }

    public Ship(int x, int y) {
        try{
            this.image = ImageIO.read(new File("D:\\project\\alien invasion\\core\\assets\\figures\\ship.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
        this.x = x;
        this.y = y;
        boundary = 1000;
    }

    public void leftMove() {
        if(x-1>=0) x -= 1;
        else x = 0;
    }

    public void rightMove() {
        if(x+1<=boundary-50) x += 1;
        else x = boundary-50;
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
