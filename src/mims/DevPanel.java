package mims;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DevPanel {

    private static final String DATA_DIR = System.getenv("APPDATA") + "/MIMS_Data";
    private static final String INVENTORY_FILE = DATA_DIR + "/inventory.json";
    private static final String UISETTINGS_FILE = DATA_DIR + "/uisettings.json";
    private static final String DPLCONFIG_FILE = DATA_DIR + "/dplconfig.json";
    private static final String MIMSENV_FILE = DATA_DIR + "/mimsenv";

    public DevPanel(){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("MIMS Dev Panel");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(5, 1));

            JButton openInventoryButton = new JButton("Open Inventory File");
            JButton openUISettingsButton = new JButton("Open UI Settings File");
            JButton openDPLConfigButton = new JButton("Open DPL Config File");
            JButton openMIMSEnvButton = new JButton("Open MIMS Env File");
            JButton openDataDirButton = new JButton("Open Data Directory");

            openInventoryButton.addActionListener(e -> openFileLocation(INVENTORY_FILE));
            openUISettingsButton.addActionListener(e -> openFileLocation(UISETTINGS_FILE));
            openDPLConfigButton.addActionListener(e -> openFileLocation(DPLCONFIG_FILE));
            openMIMSEnvButton.addActionListener(e -> openFileLocation(MIMSENV_FILE));
            openDataDirButton.addActionListener(e -> openFileLocation(DATA_DIR));

            frame.add(openInventoryButton);
            frame.add(openUISettingsButton);
            frame.add(openDPLConfigButton);
            frame.add(openMIMSEnvButton);
            frame.add(openDataDirButton);

            frame.setVisible(true);
        });
    }

    private static void openFileLocation(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to open: " + path, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}