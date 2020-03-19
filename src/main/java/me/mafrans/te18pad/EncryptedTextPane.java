package me.mafrans.te18pad;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class EncryptedTextPane extends JTextPane {
    @Getter @Setter
    private String algorithm = "DES";

    private String text = "";

    private String key;

    @SneakyThrows
    public EncryptedTextPane() {
        Font font = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.getSystemClassLoader().getResourceAsStream("roboto-mono.ttf"));
        font = font.deriveFont(14f);
        setFont(font);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == '\b') {
                    if(text.length() > 0) {
                        text = text.substring(0, text.length() - 1);
                        encrypt();
                    }
                    return;
                }

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

    @SneakyThrows
    public byte[] getEncrypted() {
        byte[] keyBytes = new byte[8];
        System.arraycopy(this.key.getBytes(), 0, keyBytes, 0, this.key.getBytes().length);

        SecretKeySpec keyspec = new SecretKeySpec(keyBytes, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);

        cipher.init(Cipher.ENCRYPT_MODE, keyspec);
        return cipher.doFinal(text.getBytes());
    }

    public void encrypt() {
        this.setText(new String(getEncrypted()));
    }

    @SneakyThrows
    public void decrypt(byte[] encrypted) {
        byte[] keyBytes = new byte[8];
        System.arraycopy(this.key.getBytes(), 0, keyBytes, 0, this.key.getBytes().length);

        SecretKeySpec keyspec = new SecretKeySpec(keyBytes, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);

        cipher.init(Cipher.DECRYPT_MODE, keyspec);
        byte[] decrypted = cipher.doFinal(encrypted);

        this.setInternalText(new String(decrypted));
    }

    public void setKey(String key) {
        this.key = key;
        encrypt();
    }

    public String getInternalText() {
        return text;
    }

    public void setInternalText(String string) {
        this.text = string;
        encrypt();
    }
}
