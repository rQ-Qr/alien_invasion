package edu.mygdx.view;

import edu.mygdx.controller.GameController;
import edu.mygdx.model.GameModel;
import edu.mygdx.model.Setting;

import javax.swing.*;
import java.awt.*;

public class GameView {
    JFrame frame;
    GamePanel panel;
    GameController controller;
    GameModel models;

    public GameView(GameModel models, GameController controller) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.models = models;
        this.controller = controller;
        panel = new GamePanel(models);
        panel.addKeyListener(controller);
        panel.setPreferredSize(new Dimension(Setting.screenWidth, Setting.screenHeight));
        panel.setBackground(new Color(230, 230, 230));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void startGame() {
        Thread t = new Thread();
        while(models.stats.checkState()) {
            try {
                t.sleep(2);
            } catch (Exception e) {
                break;
            }
            controller.checkState();
            panel.paint();
        }
    }
}
