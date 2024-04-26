package mims.ui;

import mims.MIMS;

import javax.swing.*;
import java.awt.*;

public class DefaultPanel extends JPanel {

    public static final Font H1=new Font("Arial",Font.BOLD,48);
    public static final Font H2 = new Font("Arial",Font.BOLD,36);
    public static final Font Paragraph = new Font("Arial",Font.PLAIN,20);

    private MIMS.UIController controller;
   private JLabel header;
   private JPanel headerPanel = new JPanel();
   private JPanel content = new JPanel();
    public DefaultPanel(){
        super();

        header = new JLabel("Default Panel");
        header.setFont(H1);
        headerPanel.add(header);

        super.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        header.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 68));
        content.setBackground(Color.BLACK);

        add(headerPanel);
        add(content);

    }

    public void setController(MIMS.UIController controller){

    }

    public void setHeader(String s){
        header.setText(s);

    }

   public void setContent(JPanel content){

        this.content.removeAll();

        this.content.add(content);
   }


}
