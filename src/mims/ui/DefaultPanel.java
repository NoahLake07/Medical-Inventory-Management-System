package mims.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DefaultPanel extends JPanel {

    public static final Font H1=new Font("Arial",Font.BOLD,36);
    public static final Font H2 = new Font("Arial",Font.BOLD,28);
    public static final Font Paragraph = new Font("Arial",Font.PLAIN,16);
    JLabel header;
    JPanel content = new JPanel();
    public DefaultPanel(){
        super();

        header = new JLabel("Default Panel");

        super.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(header);
        add(content);

    }

    public void setHeader(String s){
        header.setText(s);
        header.setFont(H1);
    }

    @Override
    public Component add(Component j){
        content.add(j);
        return j;
    }

    public void setVisible (boolean b){
        content.setVisible(true);
    }

    public void setLayout(LayoutManager layout) {
        if(content!=null) content.setLayout(layout);
        else super.setLayout(layout);
    }

    // Method to set the border
    public void setBorder(Border border) {
        content.setBorder(border);
    }

    // Method to set the background color
    public void setBackground(Color color) {
        content.setBackground(color);
    }

    // Method to set the foreground color
    public void setForeground(Color color) {
        content.setForeground(color);
    }


}
