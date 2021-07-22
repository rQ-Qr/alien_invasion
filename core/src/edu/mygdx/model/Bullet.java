package edu.mygdx.model;

public class Bullet {
    int x;
    int y;
    int width;
    int height;

    public void move() {
        y -= Setting.bulletSpeedFactor;
    }

    public Bullet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
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
}
