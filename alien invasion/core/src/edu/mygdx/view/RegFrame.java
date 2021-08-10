package edu.mygdx.view;

import edu.mygdx.controller.RegController;
import edu.mygdx.model.GameModel;
import edu.mygdx.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegFrame extends JFrame  {
    GameModel models;
    GameView view;
    JTextField jTextField; //定义文本框组件
    JPasswordField jPasswordField; //定义密码框组件
    JLabel jLabel1,jLabel2;
    JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8;
    JButton jb1,jb2,jb3; // button
    JCheckBox jc1;
    MouseListener m1;
    MouseListener m2;
    MouseListener m3;

    public RegFrame(GameModel models, GameView view) {
        this.models = models;
        this.view = view;
        jTextField = new JTextField(12);
        jPasswordField = new JPasswordField(12);
        jLabel1 = new JLabel("Username:");
        jLabel2 = new JLabel("Password:");
        jb1 = new JButton("Sign in");
        jb1.setPreferredSize(new Dimension(80, 28));
        jb2 = new JButton("Try for free");
        jb2.setPreferredSize(new Dimension(100, 28));
        jb3 = new JButton("Sign up");
        jb3.setPreferredSize(new Dimension(80, 28));
        jc1 = new JCheckBox("Apply for the Premium");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jp8 = new JPanel();

        initMouseListener();
        jb1.addMouseListener(m1);
        jb2.addMouseListener(m2);
        jb3.addMouseListener(m3);

        //设置布局
        GridBagLayout gb = new GridBagLayout();

        this.setLayout(gb);
        jp1.add(jLabel1);
        jp2.add(jTextField);//第一块面板添加用户名和文本框
        jp3.add(jLabel2);
        jp4.add(jPasswordField);//第二块面板添加密码和密码输入框
        jp5.add(jb1);
        jp6.add(jb2); //第三块面板添加确认和取消
        jp7.add(jb3);
        jp8.add(jc1);
        GridBagConstraints constraints = new GridBagConstraints();

        this.add(jp1, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(jp2, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(jp3, constraints);  //将三块面板添加到登陆框上面
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(jp4, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(jp5, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(jp6, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(jp7, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        this.add(jp8, constraints);


        //设置显示
        this.setSize(300, 200);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Alien Invasion");
    }

    private void initMouseListener() {
        m1 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = jTextField.getText();
                String password = new String(jPasswordField.getPassword());
                User user = RegController.login(username, password);
                RegFrame.super.setAlwaysOnTop(false);
                if(user==null) {
                    JOptionPane.showMessageDialog(null, "Connection fail!", "Error", JOptionPane.ERROR_MESSAGE);
                    RegFrame.super.setVisible(false);
                }
                else if(user.getUsername()==null || user.getUsername().equals("")) {
                    JOptionPane.showMessageDialog(null, "Login Fail!", "Error", JOptionPane.ERROR_MESSAGE);
                    RegFrame.super.setAlwaysOnTop(true);
                }
                else {
                    models.stats.setUser(user);
                    models.stats.updateStats();
                    view.refresh();
                    JOptionPane.showMessageDialog(null, "Welcome!","Success", JOptionPane.PLAIN_MESSAGE);
                    RegFrame.super.setVisible(false);
                }
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
        };

        m2 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegFrame.super.setVisible(false);
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
        };

        m3 = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = jTextField.getText();
                String password = new String(jPasswordField.getPassword());
                User user = RegController.reg(username, password, jc1.isSelected());
                RegFrame.super.setAlwaysOnTop(false);
                if(user==null) {
                    JOptionPane.showMessageDialog(null, "Connection fail!", "Error", JOptionPane.ERROR_MESSAGE);
                    RegFrame.super.setVisible(false);
                }
                else if(user.getUsername()==null || user.getUsername().equals("")) {
                    JOptionPane.showMessageDialog(null, "Username exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    RegFrame.super.setAlwaysOnTop(true);
                }
                else {
                    models.stats.setUser(user);
                    models.stats.updateStats();
                    view.refresh();
                    JOptionPane.showMessageDialog(null, "Welcome!","Success", JOptionPane.PLAIN_MESSAGE);
                    RegFrame.super.setVisible(false);
                }
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
        };
    }
}
