package me.mafrans.te18pad;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EncryptedTextPane extends JTextPane {
    private String text;

    public EncryptedTextPane() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                text += e.getKeyChar();
                encrypt();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void encrypt() {

    }
}
