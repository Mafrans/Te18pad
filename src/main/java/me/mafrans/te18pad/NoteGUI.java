package me.mafrans.te18pad;

import lombok.Data;
import lombok.SneakyThrows;

import javax.swing.*;

@Data
public class NoteGUI {
    private JPanel mainPanel;
    private JSpinner seedSpinner;
    private JSlider seedSlider;
    private JTextPane textPane;
    private JScrollPane scrollPane;
    private JPanel seedPanel;

    @SneakyThrows
    public NoteGUI(String title) {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame frame = new JFrame(title);
        frame.setContentPane(getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
