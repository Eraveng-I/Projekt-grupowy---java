package com.company.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private final AnimationPanel animationPanel = new AnimationPanel();
    public static WinCommunicat comm = new WinCommunicat();
    public static JButton winnerButton = new JButton("Announce the winner");
    public static JButton looserButton = new JButton("Vote for Lewy");
    public static JButton funFact = new JButton("Ciekawostka");
    public GUI(){

        comm.setParameters();
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

        winnerButton.setBounds(500, 3000, 200, 200);
        winnerButton.setBackground(Color.white);
        winnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var argentinaFlag = animationPanel.getAnimation().getObjects().getBallByColor(Color.cyan);
                argentinaFlag.setIsWinner(true);
            }
        });
        animationPanel.add(winnerButton);
        
        looserButton.setBounds(500,3000,200,200);
        looserButton.setBackground(Color.RED);
        looserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var polandFlag = animationPanel.getAnimation().getObjects().getBallByColor(Color.RED);
                polandFlag.setIsLooser(true);
            }
        });

        funFact.setBounds(500,3000,200,200);
        funFact.setBackground(Color.cyan);
        animationPanel.add(funFact);
        funFact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Fun Fact");
                frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame1.setSize(500, 100);
                frame1.setVisible(true);
                frame1.setResizable(false);
                JLabel label1 = new JLabel();
                label1.setText("Pierwszym i jedynym bramkarzem, który zdobył złotą piłkę, był Lew Yashin.");
                frame1.add(label1);
            }
        });
        
        animationPanel.add(looserButton);

        animationPanel.startAnimation();
    }

    public static void setWinnerButtonVisible ()
    {
        winnerButton.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
