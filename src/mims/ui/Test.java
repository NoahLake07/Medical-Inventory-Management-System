package mims.ui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {

        

        //new HelpDialogue();
        JFrame frame = new JFrame();
        DefaultPanel dP = new DefaultPanel();

        JPanel example = new JPanel();
        JButton testBtn = new JButton("Click me");
        example.add(testBtn);
        dP.setContent(example);

        dP.setHeader("OOGA BOOGA");
        frame.add(dP);
        frame.setVisible(true);
        frame.setSize(400,300);


    }
}

