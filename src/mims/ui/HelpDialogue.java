package mims.ui;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class HelpDialogue {

    private JFrame frame;
    private JPanel headerPanel, inputPanel, sendPanel;
    private JButton sendBtn;
    private JTextField name, email, inquiry;
    private Font lblFont = new Font("Arial", Font.PLAIN, 16);

    public HelpDialogue() {
        frame = new JFrame("Help");
        frame.setLayout(new BorderLayout());

        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 78));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        JLabel headerLbl = new JLabel("Contact");
        headerLbl.setFont(DefaultPanel.H1);
        headerPanel.add(headerLbl);
        frame.add(headerPanel, BorderLayout.NORTH);

        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        namePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));
        namePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        JLabel nameLbl = new JLabel("Name");
        nameLbl.setFont(DefaultPanel.Paragraph);
        name = new JTextField(11);
        namePanel.add(nameLbl);
        namePanel.add(name);
        inputPanel.add(namePanel);

        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        emailPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 25));
        emailPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        JLabel emailLbl = new JLabel("Email");
        emailLbl.setFont(DefaultPanel.Paragraph);
        email = new JTextField(11);
        emailPanel.add(emailLbl);
        emailPanel.add(email);
        inputPanel.add(emailPanel);

        JPanel inquiryPanel = new JPanel();
        inquiryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        inquiryPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
        inquiryPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        JLabel inquiryLbl = new JLabel("Inquiry");
        inquiryLbl.setFont(DefaultPanel.Paragraph);
        inquiry = new JTextField(11);
        inquiryPanel.add(inquiryLbl);
        inquiryPanel.add(inquiry);
        inputPanel.add(inquiryPanel);

        frame.add(inputPanel, BorderLayout.CENTER);

        sendPanel = new JPanel();
        sendPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        sendBtn = new JButton("Send");
        sendPanel.add(sendBtn);
        sendPanel.setBorder(BorderFactory.createEmptyBorder(0,0,10,10));
        frame.add(sendPanel, BorderLayout.SOUTH);

        sendBtn.addActionListener(e->{
            JOptionPane.showMessageDialog(frame,"Message Sent Successfully.");
        });

        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}