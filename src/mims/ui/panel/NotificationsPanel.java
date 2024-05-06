package mims.ui.panel;

import mims.ui.DefaultPanel;

import javax.swing.*;

public class NotificationsPanel extends DefaultPanel {
    private JLabel notificationsLabel;
    private JPanel notificationsContent;

    public NotificationsPanel() {
        super();
        notificationsLabel = new JLabel("Notifications");
        notificationsLabel.setFont(H1);
        headerPanel.add(notificationsLabel);
        notificationsContent = new JPanel();
        content.add(notificationsContent);
    }
}
