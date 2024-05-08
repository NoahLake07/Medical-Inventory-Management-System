package mims.ui.panel.subsettings;

import mims.ui.DefaultPanel;

import javax.swing.*;
import java.awt.*;

public class SimplePanel extends JPanel {

    public SimplePanel(String headerText){
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, DefaultPanel.H3.getSize()*2));
            JLabel header = new JLabel(headerText);
            header.setFont(DefaultPanel.H3);
            header.setAlignmentX(Component.LEFT_ALIGNMENT);
        headerPanel.add(header);
        add(headerPanel);

    }

}
