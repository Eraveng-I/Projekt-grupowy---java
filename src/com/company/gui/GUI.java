package com.company.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {
    private final AnimationPanel animationPanel = new AnimationPanel();


    public GUI(){
        JFrame frame = new JFrame("Golden ball event");
        animationPanel.setPreferredSize(new Dimension(1000,500));
        frame.getContentPane().add(BorderLayout.CENTER, animationPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Animation.setIsEndOfProgram(true);
            }
        });
        animationPanel.startAnimation();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
