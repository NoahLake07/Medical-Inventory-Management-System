package mims.ui.panel;

import mims.ui.DefaultPanel;

import javax.swing.*;

public class DashboardPanel extends DefaultPanel {
    private JLabel dashboardLabel;
    private JPanel dashboardContent;

    public DashboardPanel() {
        super();
        dashboardLabel = new JLabel("Dashboard");
        dashboardLabel.setFont(H1);
        headerPanel.add(dashboardLabel);
        dashboardContent = new JPanel();
        content.add(dashboardContent);
    }
}


