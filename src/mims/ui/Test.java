package mims.ui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        new HelpDialogue();
    }
}
