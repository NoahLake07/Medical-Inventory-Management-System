package mims;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import mims.ui.HelpDialogue;
import mims.ui.panel.*;

import javax.swing.*;
import java.awt.*;

public class MIMS extends JFrame {

    JSplitPane splitPane;
    JPanel sideBar, currentPage;
    public static UIController uiController;

    JPanel settingsMenu; // for the settings sub-menu
    boolean subMenuVisible = false;

    /**
     * Default constructor for MIMS
     */
    public MIMS(){

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        this.setTitle("MIMS v1.0");
        this.setName("MIMS 2024");
        this.setSize(800, 600);
        this.setUndecorated(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        uiController = new UIController(this);
        setupUI();

        this.setVisible(true);
    }

    /**
     * Constructor for MIMS that takes command line arguments
     * @param args
     */
    public MIMS(String[] args){
        //todo finish implementation (starting the app from reading the app's root directory
    }

    private void setupUI(){
        currentPage = new JPanel();
        splitPane = new JSplitPane();

        // SETUP MENU BAR
        sideBar = new JPanel();
        sideBar.setBackground(new Color(187, 202, 211));
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.setMaximumSize(new Dimension(450, this.getHeight()));

        // SETUP MENU HEADER
        JLabel header = new JLabel("MIMS");
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setHorizontalAlignment(JLabel.CENTER); // Center the header
        sideBar.add(header);
        header.setMaximumSize(new Dimension(Short.MAX_VALUE,50));

        // SETUP MENU ITEMS
        JPanel mainFunctions = new JPanel();
        mainFunctions.setLayout(new BoxLayout(mainFunctions, BoxLayout.Y_AXIS));

        // Create an array of button names
        String[] buttonNames = {"Dashboard", "Operations", "Settings", "Notifications"};

        // Iterate over the button names, creating a button for each one
        for (String buttonName : buttonNames) {
            JButton button = new JButton(buttonName);
            button.setFont(new Font("Arial", Font.PLAIN, 18)); // Set a more professional-looking font
            button.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // Make the button span the entire width of the sidebar
            button.addActionListener(e -> uiController.setPage(buttonName.toLowerCase()));
            sideBar.add(button);
        }

        initializeUIPanels();

        // CONFIGURE SPLIT PANE
        splitPane.setLeftComponent(sideBar);
        splitPane.setRightComponent(currentPage);
        this.add(splitPane);
    }

    private void initializeUIPanels(){
        uiController.initUIPanels();
    }

    public boolean isDevMode(){
        return true; //todo connect this to the static IOManager, which will actually fetch the env variable
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

        /**
         * A method to only be called from within the UIController class to set the page.
         * @param page
         */
        private void setPage(JPanel page){
            this.currentPage = page;
            parent.setRightComponent(currentPage);
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
        if(args.length > 0){
            new MIMS(args);
        } else {
            new MIMS();
        }
    }

}