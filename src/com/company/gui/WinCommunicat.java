package com.company.gui;


import javax.swing.*;
import java.awt.*;

public class WinCommunicat {
     public JFrame winComm = new JFrame("Winner");
     JPanel panel = new JPanel();
     JLabel communicat = new JLabel("Winner: Leo Messi");



     JButton Restart = new JButton("Play again");

    public void setParameters(){
        panel.setBackground(Color.GREEN);

        winComm.setBounds(430, 200, 200, 200);
        Restart.setBounds(500, 330, 200, 200);

        winComm.add(panel);
        panel.add(communicat);
        panel.add(Restart);

    }

}
