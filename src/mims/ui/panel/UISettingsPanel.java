package mims.ui.panel;

import mims.ui.DefaultPanel;

import javax.swing.*;

public class UISettingsPanel extends DefaultPanel {
    private JLabel uiSettingsLabel;
    private JPanel uiSettingsContent;

    public UISettingsPanel() {
        super();
        uiSettingsLabel = new JLabel("UI Settings");
        uiSettingsLabel.setFont(H1);
        headerPanel.add(uiSettingsLabel);
        uiSettingsContent = new JPanel();
        content.add(uiSettingsContent);
    }


}
