package edu.mygdx.model;

import java.awt.*;

public class Setting {
    static public int screenWidth = 1000;
    static public int screenHeight = 650;

    static int shipSize = 50;
    static int shipX = screenWidth/2-shipSize/2;
    static int shipY = screenHeight-shipSize-10;
    static double shipSpeedFactor = 4;
    static int shipLimit = 3;

    static int alienSize = 45;
    static double alienSpeedFactor = 1;
    static int alienDropSpeed = 5;
    static public int alienDirection = 1;

    static int scoreScale = 10;
    static int alienPoints = 50;

    static int bulletSpeedFactor = 5;
    static int bulletWidth = 3;
    static int bulletHeight = 15;
    static Color bulletColor = new Color(255, 255, 0);

    static int speedup_scale = 1;

    public static void resetSpeed() {
        shipSpeedFactor = 1;
        bulletSpeedFactor = 3;
        alienSpeedFactor = 1;
        alienDirection = 1;
        alienPoints = 50;
    }

    public static void increaseSpeed() {
        shipSpeedFactor += 1;
        bulletSpeedFactor += 1;
        alienSpeedFactor += 1;
        alienDirection = 1;
        alienPoints += scoreScale;
    }

}
