package com.company.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import

public class WinCommunicat {
     public int numberOfWins = 1;
     public JFrame winComm = new JFrame("Winner");
     JPanel panel = new JPanel();
     JLabel communicat = new JLabel("Winner: Leo Messi");

     JButton Restart = new JButton("Play again");
     JButton Exit = new JButton("Exit");
     JLabel NumberOfWins = new JLabel("Number of wins: " + numberOfWins);
    public void setParameters(){
        panel.setBackground(Color.GREEN);

        winComm.setBounds(430, 200, 200, 200);
        Restart.setBounds(500, 330, 200, 200);
        Exit.setBounds(670, 460, 200, 200);
        NumberOfWins.setBounds(10,350,200,50);

        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winComm.setVisible(false);
                GUI.setWinnerButtonVisible();
                panel.remove(NumberOfWins);
                numberOfWins++;
                NumberOfWins = new JLabel("Number of wins: " + numberOfWins);
                panel.add(NumberOfWins);

            }
        });

        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        winComm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GUI.setWinnerButtonVisible();
                panel.remove(NumberOfWins);
                numberOfWins++;
                NumberOfWins = new JLabel("Number of wins: " + numberOfWins);
                panel.add(NumberOfWins);
            }
        });

        winComm.add(panel);
        panel.add(communicat);
        panel.add(Restart);
        panel.add(Exit);
        panel.add(NumberOfWins);

    }

}
