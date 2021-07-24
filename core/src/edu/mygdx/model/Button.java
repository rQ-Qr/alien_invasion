package edu.mygdx.model;

import edu.mygdx.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Button extends JButton {

    public Button() {
        super("PLAY");
        Font font = new Font("", Font.BOLD, 20);
        super.setFont(font);
        super.setForeground(new Color(0, 255, 0));
        super.setLocation(200, 300);
        super.setVisible(true);
        super.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Button.super.setVisible(false);
                Button.super.setEnabled(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
