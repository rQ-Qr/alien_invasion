package edu.mygdx.view;

import edu.mygdx.controller.GameController;
import edu.mygdx.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class GameView implements Ite {
    JFrame frame;
    GamePanel panel;
    GameController controller;

    public GameView(GameModel models, GameController controller) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GamePanel(models);
        this.controller = controller;
        panel.addKeyListener(controller);
        panel.setPreferredSize(new Dimension(1000, 600));
        panel.setBackground(new Color(230, 230, 230));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }


    public void startGame() {
        while(true) {
            try {
                Thread.sleep(2);
            } catch (Exception e) {
                System.out.println(e);
            }

            controller.checkState();
            panel.paint();
        }
    }
    public static void main(String[] args) {
        GameModel models = new GameModel();
        GameController controller = new GameController(models);
        GameView view = new GameView(models, controller);
        view.startGame();
    }

}
