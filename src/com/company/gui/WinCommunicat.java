package com.company.gui;


import javax.swing.*;

public class WinCommunicat {
     JFrame winComm = new JFrame("Winner");
     JPanel panel = new JPanel();
     JLabel communicat = new JLabel("Winner: Leo Messi");



     JButton Restart = new JButton("Play again");


    public void setParameters(){
        winComm.add(panel);
        panel.add(communicat);
        panel.add(Restart);

    }

}
