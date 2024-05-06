package mims;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import mims.ui.HelpDialogue;

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
        sideBar.setSize(new Dimension(400, this.getHeight()));

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

        // CONFIGURE SPLIT PANE
        splitPane.setLeftComponent(sideBar);
        splitPane.setRightComponent(currentPage);
        this.add(splitPane);

        // INITIALIZE SETTINGS PANE

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

        public UIController(MIMS parent){
            this.parent = parent;
        }

        public MIMS getParent(){
            return this.parent;
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
                    //todo implement dashboard
                    break;
                case "operations":
                    if(parent.isDevMode()) System.out.println("page changed -> operations");
                    //todo implement operations
                    break;
                case "dpl configuration":
                    if(parent.isDevMode()) System.out.println("page changed -> dpl config");
                    //todo implement dpl config
                    break;
                case "user interface":
                    if(parent.isDevMode()) System.out.println("page changed -> ui settings");
                    //todo implement ui settings
                    break;
                case "product management":
                    if(parent.isDevMode()) System.out.println("page changed -> product management");
                    //todo implement product management settings
                    break;
                case "notification settings":
                    if(parent.isDevMode()) System.out.println("page changed -> notification settings");
                    //todo implement notification settings
                    break;
                case "automatic report generator":
                    if(parent.isDevMode()) System.out.println("page changed -> auto report generator");
                    //todo implement ARP settings
                    break;
                case "data auto saver":
                    if(parent.isDevMode()) System.out.println("page changed -> data autosave settings");
                    //todo implement data auto save settings
                    break;
                case "notifications":
                    //todo implement notifications
                    if(parent.isDevMode()) System.out.println("page changed -> notifications");
                    break;
                case "help":
                    if(parent.isDevMode()) System.out.println("opened dialogue -> help");
                    new HelpDialogue();
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