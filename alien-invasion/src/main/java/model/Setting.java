package model;

import java.awt.*;

/**
 * This is the setting file for the game.
 *
 * @author Shenquan Wang, Ran Qin
 * @version 1.0
 * @since 08/14/2021
 */
public class Setting {
    /**
     * This is the filed for this file.
     */
    static public int screenWidth = 1000;
    static public int screenHeight = 650;

    static public User user;

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

    static int bonusX = screenWidth/2-200;
    static int bonusY = 10;

    static int speedup_scale = 1;

    /**
     * This method is for reset the speed of the aliens.
     */
    public static void resetSpeed() {
        shipSpeedFactor = 1;
        bulletSpeedFactor = 3;
        alienSpeedFactor = 1;
        alienDirection = 1;
        alienPoints = 50;
    }

    /**
     * This method is for increase the speed of the aliens.
     */
    public static void increaseSpeed() {
        shipSpeedFactor += 1;
        bulletSpeedFactor += 1;
        alienSpeedFactor += 1;
        alienDirection = 1;
        alienPoints += scoreScale;
    }

}
