package me.mafrans.te18pad;

import javax.swing.*;

public class Form {
    private JPanel mainPanel;
    private JSpinner seedSpinner;
    private JSlider seedSlider;
    private JTextPane textPane;
    private JScrollPane scrollPane;
    private JPanel seedPanel;

    public Form(String title) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
