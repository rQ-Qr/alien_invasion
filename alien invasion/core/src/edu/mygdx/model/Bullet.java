package edu.mygdx.model;

import java.awt.*;

public class Bullet {
    int x;
    int y;
    int width;
    int height;
    Color color;

    public void move() {
        y -= Setting.bulletSpeedFactor;
    }

    public Bullet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = Setting.bulletColor;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
}
