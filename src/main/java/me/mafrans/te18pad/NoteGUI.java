package me.mafrans.te18pad;

import lombok.Data;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
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
        frame = new JFrame(title);
        frame.setContentPane(getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);

        // Set default values after frame is created but before it is packed
        setDefaultValues();

        frame.pack();
        frame.setVisible(true);

        createListeners();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save (S)");
        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke("control S"));
        saveItem.addActionListener((event) -> {
            save();
        });
        menu.add(saveItem);

        JMenuItem saveAsItem = new JMenuItem("Save As (A)");
        saveAsItem.setMnemonic(KeyEvent.VK_A);
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
        saveAsItem.addActionListener((event) -> {
            saveAs();
        });
        menu.add(saveAsItem);

        JMenuItem openItem = new JMenuItem("Open (O)");
        openItem.setMnemonic(KeyEvent.VK_O);
        openItem.setAccelerator(KeyStroke.getKeyStroke("control O"));
        menu.add(openItem);

        menuBar.add(menu);

        return menuBar;
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

        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save as...");
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
