package mims.ui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class Test {

    public static void main(String[] args) {


        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new Error(e);
        }

        new HelpDialogue();
        /*JFrame frame = new JFrame();
        DefaultPanel dP = new DefaultPanel();

        JPanel example = new JPanel();
        JButton testBtn = new JButton("Click me");
        example.add(testBtn);
        dP.setContent(example);

        dP.setHeader("how does this sound");
        frame.add(dP);
        frame.setVisible(true);
        frame.setSize(400,300);*/


    }
}

