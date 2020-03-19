package me.mafrans.te18pad;

import javax.swing.*;

public class Te18pad {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        NoteGUI noteGUI = new NoteGUI("Real Time Encoder");
    }
}
