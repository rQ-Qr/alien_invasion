package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * This is Bonus model file for building bonus
 *
 * @author Shenquan Wang, Ran Qin
 * @version 1.0
 * @since 08/13/2021
 */
public class Bonus {
    /**
     * This is the filed for Bonus model file.
     */
    private Image image;
    private int x;
    private int y;
    private int size = 35;

    /**
     * This method gets the image from the bonus.
     * @return image image of the bonus.
     */
    public Image getImage() {
        return image;
    }

    /**
     * This method determines the vertical movement.
     */
    public void down() {
        y += 3;
    }

    /**
     * This method determines if the object reach the edges
     * @return Ture if the object reach the edges
     */
    public boolean checkEdges() {
        if(x<=0 || x>=Setting.screenWidth-size)
            return true;
        else return false;
    }

    /**
     * This method returns horizontal location coordinate.
     * @return x horizontal location coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * This method returns vertical location coordinate.
     * @return y vertical location coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * This method returns the size of the bonus.
     * @return size size of the bonus.
     */
    public int getSize() {
        return size;
    }

    private int boundary = 1000;
    private boolean isLeft = false;
    private boolean isRight = false;

    /**
     * This is the constructor for bonus.
     * @param x horizontal location coordinate.
     * @param y vertical location coordinate.
     */
    public Bonus(int x, int y) {
        try{
            URL url = this.getClass().getResource("/assets/figures/jerrycan.png");
            ImageIcon imageIcon = new ImageIcon(url);
            this.image = imageIcon.getImage();
        } catch (Exception e) {
            System.out.println(e);
        }
        this.x = x;
        this.y = y;
        boundary = 1000;
    }

    /**
     * This method remove the bonus from the screen.
     */
    public void clear() {
        this.y = 1100;
    }
}
