package mims.ui;

import mims.MIMS;

import javax.swing.*;
import java.awt.*;

public class DefaultPanel extends JPanel {

    public static final Font H1=new Font("Arial",Font.BOLD,42);
    public static final Font H2 = new Font("Arial",Font.BOLD,32);
    public static final Font H3 = new Font("Arial",Font.BOLD,26);
    public static final Font Paragraph = new Font("Arial",Font.PLAIN,18);

    private MIMS.UIController controller;
   private JLabel header;
   protected JPanel headerPanel = new JPanel();
   protected JPanel content = new JPanel();


   public DefaultPanel(){
        super();

        header = new JLabel("");
        header.setFont(H1);
        headerPanel.add(header);

        super.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20,10,10,10));
        header.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 68));

        add(headerPanel);
        add(content);

    }

    public void setController(MIMS.UIController controller){
        this.controller = controller;
    }

    public void setHeader(String s){
        header.setText(s);
    }

   public void setContent(JPanel content){

        this.content.removeAll();

        this.content.add(content);
   }


}
