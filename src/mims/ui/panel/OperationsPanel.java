package mims.ui.panel;

import mims.ui.DefaultPanel;

import javax.swing.*;

public class OperationsPanel extends DefaultPanel {
    private JLabel operationsLabel;
    private JPanel operationsContent;

    public OperationsPanel() {
        super();
        operationsLabel = new JLabel("Operations");
        operationsLabel.setFont(H1);
        headerPanel.add(operationsLabel);
        operationsContent = new JPanel();
        content.add(operationsContent);
    }
}
