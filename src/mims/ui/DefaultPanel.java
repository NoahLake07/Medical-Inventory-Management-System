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
        header.setFont(H1);

        super.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(header);
        add(content);

    }

    public void setHeader(String s){
        header.setText(s);

    }

   public void setContent(JPanel content){

        this.content.removeAll();

        this.content.add(content);
   }


}
