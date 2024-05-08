package mims.ui.panel.subsettings;

import javax.swing.*;
import java.awt.*;

public class DataHandlerPanel extends SimplePanel {
    private JToggleButton autoReportGeneratorOn, autoReportGeneratorOff;

    public DataHandlerPanel() {
        super("Data Handling");

        // REPORT GENERATION SECTION
        JPanel reportGenerationPanel = new JPanel();
        reportGenerationPanel.setBorder(BorderFactory.createTitledBorder("Report Generation"));
        reportGenerationPanel.setLayout(new BoxLayout(reportGenerationPanel, BoxLayout.Y_AXIS));
            JPanel reportGenerationTogglePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel reportGenerationLabel = new JLabel("Auto Report Generation");
            autoReportGeneratorOn = new JToggleButton("On");
            autoReportGeneratorOff = new JToggleButton("Off");
            reportGenerationTogglePanel.add(reportGenerationLabel);
            reportGenerationTogglePanel.add(autoReportGeneratorOff);
            reportGenerationTogglePanel.add(autoReportGeneratorOn);
                // action listeners
                autoReportGeneratorOff.addActionListener(e-> {
                autoReportGeneratorOff.setSelected(true);
                autoReportGeneratorOn.setSelected(false);
                // todo connect this to actual data through the controller
                });
                autoReportGeneratorOn.addActionListener(e-> {
                autoReportGeneratorOff.setSelected(false);
                autoReportGeneratorOn.setSelected(true);
                // todo connect this to actual data through the controller
                });
        reportGenerationPanel.add(reportGenerationTogglePanel);
        add(reportGenerationPanel);
    }
}
