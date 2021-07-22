package edu.mygdx.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Alien {
    private Image image;
    private int x;
    private int y;
    private int size = Setting.alienSize;

    public Image getImage() {
        return image;
    }

    public void move() {
        x += Setting.alienDirection * Setting.alienSpeedFactor;
    }

    public void down() {
        y += Setting.alienDropSpeed;
    }

    public boolean checkEdges() {
        if(x<=0 || x>=Setting.screenWidth-Setting.alienSize)
            return true;
        else return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    private int boundary = 1000;
    private boolean isLeft = false;
    private boolean isRight = false;

    public Alien(int x, int y) {
        try{
            this.image = ImageIO.read(new File("./core/assets/figures/alien.bmp"));
        } catch (Exception e) {
            System.out.println(e);
        }
        this.x = x;
        this.y = y;
        boundary = 1000;
    }
}
