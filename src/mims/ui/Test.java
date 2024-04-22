<<<<<<< Updated upstream
package mims.ui;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {

        new HelpDialogue();
    }
}
=======
package mims.ui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {

        

        //new HelpDialogue();
        JFrame frame = new JFrame();
        DefaultPanel dP = new DefaultPanel();

        frame.add(dP);
        frame.setVisible(true);
        frame.setSize(400,300);
    }
}
>>>>>>> Stashed changes
