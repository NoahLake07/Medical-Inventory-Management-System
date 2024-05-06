package mims.ui.panel;

import mims.ui.DefaultPanel;

import javax.swing.*;

public class NotificationsSettingsPanel extends DefaultPanel {
    private JLabel notificationSettingsLabel;
    private JPanel notificationSettingsContent;

    public NotificationsSettingsPanel(){
        super();
        notificationSettingsLabel = new JLabel("Notification Settings");
        notificationSettingsLabel.setFont(H1);
        headerPanel.add(notificationSettingsLabel);
        notificationSettingsContent = new JPanel();
        content.add(notificationSettingsContent);
    }
}
