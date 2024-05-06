package mims.ui.panel;

import mims.ui.DefaultPanel;

import javax.swing.*;
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

        settingsContent = new JTabbedPane();
        settingsContent.addTab("DPL Config", uiController.dplConfigPanel);
        settingsContent.addTab("Data Handling", uiController.dataHandlerPanel);
        settingsContent.addTab("Product Management", uiController.productManagerPanel);
        settingsContent.addTab("User Interface", uiController.uiSettingsPanel);
        this.content.add( settingsContent );
    }

}
