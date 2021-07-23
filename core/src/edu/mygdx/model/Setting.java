package edu.mygdx.model;

import java.awt.*;

public class Setting {
    static public int screenWidth = 1000;
    static public int screenHeight = 650;

    static int shipSize = 50;
    static int shipX = screenWidth/2-shipSize/2;
    static int shipY = screenHeight-shipSize-10;
    static double shipSpeedFactor = 1.5;
    static int shipLimit = 3;

    static int alienSize = 50;
    static double alienSpeedFactor = 1;
    static int alienDropSpeed = 10;
    static public int alienDirection = 1;

    static int bulletSpeedFactor = 3;
    static int bulletWidth = 3;
    static int bulletHeight = 15;
    static Color bulletColor = new Color(60, 60, 60);

}
