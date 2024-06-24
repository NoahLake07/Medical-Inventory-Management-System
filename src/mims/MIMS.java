package mims;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import mims.data.Inventory;
import mims.data.app.MIMSEnv;
import mims.data.app.UISettings;
import mims.ui.HelpDialogue;
import mims.ui.panel.*;
import mims.ui.panel.subsettings.*;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;

import static mims.IOManager.devMode;

public class MIMS extends JFrame {

    JSplitPane splitPane;
    JPanel sideBar, currentPage;
    public static UIController uiController;

    JPanel settingsMenu; // for the settings sub-menu
    boolean subMenuVisible = false;

    // APP DATA
    private Inventory inventory;
    private UISettings uiSettings;
    private DPL dpl;
    private MIMSEnv mimsEnv;

    /**
     * Default constructor for MIMS
     */
    public MIMS(){
        basicSetup();
        loadAssets();

        uiController = new UIController(this);
        setupUI();

        uiController.setPage("dashboard");
        this.setVisible(true);
    }

    private void basicSetup(){
        this.setTitle("MIMS v1.0");
        this.setName("MIMS 2024");
        this.setSize(800, 600);
        this.setUndecorated(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupUI(){
        currentPage = new JPanel();
        splitPane = new JSplitPane();

        // SETUP MENU BAR
        sideBar = new JPanel();
        sideBar.setBackground(new Color(225, 239, 255));
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.setMaximumSize(new Dimension(1000, this.getHeight()));

        // SETUP MENU HEADER
        JLabel header = new JLabel("MIMS");
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setHorizontalAlignment(JLabel.CENTER); // Center the header
        sideBar.add(header);
        header.setMaximumSize(new Dimension(Short.MAX_VALUE,50));

        // SETUP MENU ITEMS
        String[] buttonNames = {"Dashboard", "Operations", "Settings", "Notifications"};
        for (String buttonName : buttonNames) {
            JButton button = new JButton(buttonName);
            button.setFont(new Font("Arial", Font.PLAIN, 14)); // Set a more professional-looking font
            //button.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // Make the button span the entire width of the sidebar
            button.addActionListener(e -> uiController.setPage(buttonName.toLowerCase()));
            sideBar.add(button);
        }

        initializeUIPanels();

        // CONFIGURE SPLIT PANE
        splitPane.setLeftComponent(sideBar);
        splitPane.setDividerLocation(getWidth()/4);
        splitPane.setContinuousLayout(true);
        splitPane.setRightComponent(currentPage);
        this.add(splitPane);
    }

    private void initializeUIPanels(){
        uiController.initUIPanels();
    }

    public boolean isDevMode(){
        return devMode;
    }

    private void loadAssets(){
        // DISPLAY LOADING DIALOG
        JDialog dialog = new JDialog(this, "Loading...", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setUndecorated(true);
        dialog.getContentPane().setBackground(new Color(230, 238, 255));

        JLabel loadLabel = new JLabel("MIMS v1.0...");
        loadLabel.setHorizontalAlignment(JLabel.CENTER);
        loadLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dialog.add(loadLabel, BorderLayout.CENTER);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        dialog.add(progressBar, BorderLayout.SOUTH);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                // INITIALIZE IO MANAGER
                IOManager ioManager = new IOManager();
                ioManager.verifyInit();

                // ALLOW BUFFER TIME
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // UTILIZE IO MANAGER TO LOAD ASSETS
                mimsEnv = ioManager.loadMIMSEnv();
                inventory = ioManager.loadInventory();
                uiSettings = ioManager.loadUISettings();
                dpl = ioManager.loadDPL();

                return null;
            }

            @Override
            protected void done() {
                // CLOSE LOADING DIALOG
                dialog.dispose();

                // SET THE UI SETTINGS
                if(uiSettings != null){
                    try {
                        if(uiSettings.getTheme().equals(UISettings.Theme.LIGHT))
                            UIManager.setLookAndFeel(new FlatLightLaf());
                        else
                            UIManager.setLookAndFeel(new FlatDarkLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        worker.execute();
        dialog.setVisible(true);
    }

    void setRightComponent(JPanel panel){
        splitPane.setRightComponent(panel);
    }

    public class UIController {

        private JPanel currentPage = new JPanel();
        private MIMS parent;

        public DashboardPanel dashboardPanel;
        public NotificationsPanel notificationsPanel;
        public OperationsPanel operationsPanel;
            public NotificationsSettingsPanel notificationsSettingsPanel;
            public ProductManagerPanel productManagerPanel;
            public UISettingsPanel uiSettingsPanel;
            public DPLConfigPanel dplConfigPanel;
            public DataHandlerPanel dataHandlerPanel;
        public SettingsPanel settingsPanel;

        public UIController(MIMS parent){
            this.parent = parent;
        }

        public MIMS getParent(){
            return this.parent;
        }

        public void initUIPanels(){
            dashboardPanel = new DashboardPanel();
            dataHandlerPanel = new DataHandlerPanel();
            notificationsPanel = new NotificationsPanel();
            notificationsSettingsPanel = new NotificationsSettingsPanel();
            operationsPanel = new OperationsPanel();
            productManagerPanel = new ProductManagerPanel();
            uiSettingsPanel = new UISettingsPanel();
            dplConfigPanel = new DPLConfigPanel();
            settingsPanel = new SettingsPanel();
        }

        public void saveSettings(){
            // TODO save settings to static objects inside MIMS class (the settings will control the vars in the MIMS class)
        }

        /**
         * A method to only be called from within the UIController class to set the page.
         * @param page
         */
        private void setPage(JPanel page){
            int dividerLocation = parent.splitPane.getDividerLocation(); // Store the current divider location
            this.currentPage = page;
            parent.setRightComponent(currentPage);
            parent.splitPane.setDividerLocation(dividerLocation);
        }

        /**
         * A method used only by the main MIMS sidebar to set the page.
         * @param page
         */
        public void setPage(String page){

            /*
            AVAILABLE PAGES:
            - dashboard
            - operations
                - check in
                - check out
                - edit product par levels
                - manually create/edit/delete product inventory
                - run expiration/par check
            - settings
                - dpl configuration (dynamic product locator)
                - user interface
                - product management
                - notification settings
                - automatic report generation
                - data auto saver
            - notifications
            - help (opens a dialogue with a form to submit a help request)
             */

            switch(page){
                case "dashboard":
                    if(parent.isDevMode()) System.out.println("page changed -> dashboard");
                    setPage(dashboardPanel);
                    break;
                case "operations":
                    if(parent.isDevMode()) System.out.println("page changed -> operations");
                    setPage(operationsPanel);
                    break;
                case "dpl configuration":
                    if(parent.isDevMode()) System.out.println("page changed -> dpl config");
                    setPage(dplConfigPanel);
                    break;
                case "user interface":
                    if(parent.isDevMode()) System.out.println("page changed -> ui settings");
                    setPage(uiSettingsPanel);
                    break;
                case "product management":
                    if(parent.isDevMode()) System.out.println("page changed -> product management");
                    setPage(productManagerPanel);
                    break;
                case "notification settings":
                    if(parent.isDevMode()) System.out.println("page changed -> notification settings");
                    setPage(notificationsSettingsPanel);
                    break;
                case "automatic report generator":
                    if(parent.isDevMode()) System.out.println("page changed -> auto report generator");
                    setPage(dataHandlerPanel);
                    break;
                case "data auto saver":
                    if(parent.isDevMode()) System.out.println("page changed -> data autosave settings");
                    setPage(dataHandlerPanel);
                    break;
                case "notifications":
                    setPage(notificationsPanel);
                    if(parent.isDevMode()) System.out.println("page changed -> notifications");
                    break;
                case "help":
                    if(parent.isDevMode()) System.out.println("opened dialogue -> help");
                    new HelpDialogue();
                    break;
                case "settings":
                    if(parent.isDevMode()) System.out.println("page changed -> settings");
                    setPage(settingsPanel);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid page: " + page);
            }
        }

    }

    public static void main(String[] args) {
        new DevPanel();
        new MIMS();
    }

}