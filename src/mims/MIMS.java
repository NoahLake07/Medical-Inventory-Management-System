package mims;

import mims.ui.HelpDialogue;

import javax.swing.*;
import java.awt.*;

public class MIMS extends JFrame {

    JSplitPane splitPane;
    JPanel sideBar, currentPage;
    public static UIController uiController;

    JPanel settingsMenu; // for the settings sub-menu

    /**
     * Default constructor for MIMS
     */
    public MIMS(){
        this.setTitle("MIMS v1.0");
        this.setName("MIMS 2024");
        this.setSize(800, 600);
        this.setUndecorated(true);
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
        sideBar.setMaximumSize(new Dimension((int) this.getWidth()/3,Short.MAX_VALUE));
        sideBar.setBackground(Color.LIGHT_GRAY);
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

        // SETUP MENU ITEMS
        settingsMenu = new JPanel();
        JPanel mainFunctions = new JPanel();
        mainFunctions.setLayout(new BoxLayout(mainFunctions, BoxLayout.Y_AXIS));
            JButton dashboard = new JButton("Dashboard");
            dashboard.addActionListener(e->uiController.setPage("dashboard"));
            mainFunctions.add(dashboard);

            JButton operations = new JButton("Operations");
            operations.addActionListener(e->uiController.setPage("operations"));
            mainFunctions.add(operations);

            JButton settings = new JButton("Settings");
                Runnable r = () -> {
                    // toggle visibility of settings sub-menu
                    if(settingsMenu.isVisible())
                        settingsMenu.setVisible(false);
                    else if(!settingsMenu.isVisible()){
                        settingsMenu.setVisible(true);
                    }

                    // set the main panel to the first settings sub-menu item
                    uiController.setPage("dpl configuration"); };
            settings.addActionListener(l->r.run());
            mainFunctions.add(settings);

            JButton notifications = new JButton("Notifications");
            notifications.addActionListener(e->uiController.setPage("notifications"));
            mainFunctions.add(notifications);
        sideBar.add(mainFunctions);


        // CONFIGURE SPLIT PANE
        splitPane.setLeftComponent(sideBar);
        splitPane.setRightComponent(currentPage);
        this.add(splitPane);
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