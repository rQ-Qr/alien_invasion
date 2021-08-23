package view;

import controller.GameController;
import model.Button;
import model.GameModel;
import model.Setting;

import javax.swing.*;
import java.awt.*;

/**
 * This is Game view file for game view.
 *
 * @author Shenquan Wang, Ran Qin
 * @version 1.0
 * @since 08/15/2021
 */
public class GameView {
    /**
     * This is the filed for game view.
     */
    JFrame frame;
    GamePanel panel;
    GameController controller;
    GameModel models;
    Button button;

    /**
     * This is the constructor for game view.
     * @param models the game model for the game.
     * @param controller the game controller for the game.
     */
    public GameView(GameModel models, GameController controller) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame regFrame = new RegFrame(models, this);
        regFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        regFrame.setLocationRelativeTo(null);
        regFrame.setAlwaysOnTop(true);
        this.models = models;
        this.controller = controller;
        this.button = new Button();

        panel = new GamePanel(models);
        panel.setLayout(null);
        panel.addKeyListener(controller);
        panel.add(this.button);
        panel.setPreferredSize(new Dimension(Setting.screenWidth, Setting.screenHeight));
        //panel.setBackground(new Color(230, 230, 230));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method is for the starting of the game.
     */
    public void startGame() {
        while(true) {
            while(this.button.isEnabled()) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    break;
                }
            }

            while(models.stats.getState()) {
                try {
                    Thread.sleep(15);
                } catch (Exception e) {
                    break;
                }
                controller.checkState();
                panel.paint();
            }
            controller.playAgain();
            this.button.setEnabled(true);
            this.button.setVisible(true);
            panel.paint();
        }
    }

    /**
     * This method is for refresh panel.
     */
    public void refresh() {
        this.panel.paint();
    }

}
