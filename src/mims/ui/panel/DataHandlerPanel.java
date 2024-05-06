package mims.ui.panel;

import mims.ui.DefaultPanel;

import javax.swing.*;

public class DataHandlerPanel extends DefaultPanel {
    private JLabel dataHandlerLabel;
    private JPanel dataHandlerContent;

    public DataHandlerPanel() {
        super();
        dataHandlerLabel = new JLabel("Data Handler");
        dataHandlerLabel.setFont(H1);
        headerPanel.add(dataHandlerLabel);
        dataHandlerContent = new JPanel();
        content.add(dataHandlerContent);
    }
}
