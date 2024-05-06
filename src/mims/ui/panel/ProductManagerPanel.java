package mims.ui.panel;

import mims.ui.DefaultPanel;

import javax.swing.*;

public class ProductManagerPanel extends DefaultPanel {
    private JLabel productManagerLabel;
    private JPanel productManagerContent;

    public ProductManagerPanel() {
        super();
        productManagerLabel = new JLabel("Product Manager");
        productManagerLabel.setFont(H1);
        headerPanel.add(productManagerLabel);
        productManagerContent = new JPanel();
        content.add(productManagerContent);
    }


}
