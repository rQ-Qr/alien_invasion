package edu.mygdx.controller;

import java.awt.*;

public class Settingwrong {
    int screenWidth;
    int screenHeight;
    Color bgColor;

    static final private int SCREEN_WIDTH = 1200;
    static final private int SCREEN_HEIGHT = 800;

    public void Setting() {
        this.screenWidth = SCREEN_WIDTH;
        this.screenHeight = SCREEN_HEIGHT;
        this.bgColor = new Color(230, 230, 230);
    }
}
