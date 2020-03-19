package me.mafrans.te18pad;

import lombok.Data;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.io.*;

@Data
public class NoteGUI {
    private JFrame frame;

    private JPanel mainPanel;
    private JSpinner seedSpinner;
    private JSlider seedSlider;
    private JTextPane textPane;
    private JScrollPane scrollPane;
    private JPanel seedPanel;
    private JFileChooser fileChooser;

    private int seed;
    private Dimension preferredSize;
    private File selectedFile;

    private static int DEFAULT_SEED = 50;
    private static Dimension PREFERRED_SIZE = new Dimension(800, 600);

    @SneakyThrows
    public NoteGUI(String title) {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        frame = new JFrame(title);
        frame.setContentPane(getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set default values after frame is created but before it is packed
        setDefaultValues();

        frame.pack();
        frame.setVisible(true);

        createListeners();
    }

    private void setDefaultValues() {
        setSeed(DEFAULT_SEED);
        setPreferredSize(PREFERRED_SIZE);
    }

    private void createListeners() {
        seedSlider.addChangeListener((e) -> {
            setSeed(getSeedSlider().getValue());
        });

        seedSpinner.addChangeListener((e) -> {
            setSeed((int)getSeedSpinner().getValue());
        });
    }

    public void setSeed(int value) {
        seed = value;
        getSeedSlider().setValue(getSeed());
        getSeedSpinner().setValue(getSeed());

        ((EncryptedTextPane)textPane).setKey(String.valueOf(seed));
    }

    public void setPreferredSize(Dimension value) {
        preferredSize = value;
        frame.setPreferredSize(getPreferredSize());
    }

    private void createUIComponents() {
        EncryptedTextPane encryptedTextPane = new EncryptedTextPane();
        encryptedTextPane.setKey(String.valueOf(seed));

        textPane = encryptedTextPane;
    }

    @SneakyThrows
    private void saveToFile(File file) {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(textPane.getText());
        bufferedWriter.close();
    }

    private void save() {
        if(selectedFile == null) {
            selectedFile = saveAs();
        }
        else {
            saveToFile(selectedFile);
        }
    }

    @SneakyThrows
    private File saveAs() {
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File outFile = fileChooser.getSelectedFile();
            saveToFile(outFile);
            return outFile;
        }
        return null;
    }
}
