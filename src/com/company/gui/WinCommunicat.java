package com.company.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import

public class WinCommunicat {
     public JFrame winComm = new JFrame("Winner");
     JPanel panel = new JPanel();
     JLabel communicat = new JLabel("Winner: Leo Messi");



     JButton Restart = new JButton("Play again");


    public void setParameters(){
        panel.setBackground(Color.GREEN);

        winComm.setBounds(430, 200, 200, 200);
        Restart.setBounds(500, 330, 200, 200);

        Restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winComm.setVisible(false);
                GUI.setWinnerButtonVisible();
            }
        });

        winComm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GUI.setWinnerButtonVisible();
            }
        });




        winComm.add(panel);
        panel.add(communicat);
        panel.add(Restart);

    }

}
