package mims.ui.panel;

import mims.ui.DefaultPanel;

import javax.swing.*;

public class DPLConfigPanel extends DefaultPanel {

    private JLabel dplConfigLabel;
    private JPanel dplConfigContent;

    public DPLConfigPanel() {
        super();
        dplConfigLabel = new JLabel("DPL Configuration");
        dplConfigLabel.setFont(H1);
        headerPanel.add(dplConfigLabel);
        dplConfigContent = new JPanel();
        content.add(dplConfigContent);
    }

}
