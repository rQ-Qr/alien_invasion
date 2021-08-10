package edu.mygdx.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Bonus {
    private Image image;
    private int x;
    private int y;
    private int size = 35;

    public Image getImage() {
        return image;
    }

    public void down() {
        y += 3;
    }

    public boolean checkEdges() {
        if(x<=0 || x>=Setting.screenWidth-size)
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

    public Bonus(int x, int y) {
        try{
            this.image = ImageIO.read(new File("./core/assets/figures/jerrycan.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
        this.x = x;
        this.y = y;
        boundary = 1000;
    }

    public void clear() {
        this.y = 1100;
    }
}
