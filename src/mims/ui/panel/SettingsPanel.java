package mims.ui.panel;

import mims.ui.DefaultPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static mims.MIMS.uiController;

public class SettingsPanel extends DefaultPanel {

    JLabel settingsLabel;
    JTabbedPane settingsContent;

    public SettingsPanel() {
        super();
        settingsLabel = new JLabel("Settings");
        settingsLabel.setFont(H1);
        headerPanel.add(settingsLabel);
        settingsLabel.setSize(this.getWidth(), 50);
        super.content.setLayout(new BorderLayout());

        settingsContent = new JTabbedPane();
        settingsContent.addTab("DPL Config", uiController.dplConfigPanel);
        settingsContent.addTab("Data Handling", uiController.dataHandlerPanel);
        settingsContent.addTab("Product Management", uiController.productManagerPanel);
        settingsContent.addTab("User Interface", uiController.uiSettingsPanel);
        settingsContent.addTab("Notifications", uiController.notificationsSettingsPanel);
        this.content.add( settingsContent, BorderLayout.CENTER);

        JButton saveBtn = new JButton("Save");
        this.content.add(saveBtn, BorderLayout.SOUTH);
        saveBtn.addActionListener(e -> super.getController().saveSettings());
    }

}
