package mims.ui.panel.subsettings;

import mims.DPL;
import mims.MIMS;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DPLConfigPanel extends SimplePanel {
    private JTree tree;
    private DefaultTreeModel treeModel;
    private DPL dpl;

    public DPLConfigPanel(DPL dpl) {
        super("DPL Configuration");
        this.dpl = dpl;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        createTree();
        add(new JScrollPane(tree), BorderLayout.CENTER);
    }

    private void createTree() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Storage Room");
        treeModel = new DefaultTreeModel(rootNode);
        tree = new JTree(treeModel);
        tree.setRootVisible(true);
        populateTree(rootNode);

        // Set custom renderer
        tree.setCellRenderer(new CustomTreeCellRenderer());

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showContextMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showContextMenu(e);
                }
            }
        });
    }

    private void populateTree(DefaultMutableTreeNode rootNode) {
        DPL.Container rootContainer = dpl.getRoot();
        addNodes(rootNode, rootContainer);
    }

    private void addNodes(DefaultMutableTreeNode parentNode, DPL.Container container) {
        if (container == null) return;
        for (DPL.Node child : container.getChildren()) {
            if (child instanceof DPL.Container) {
                DPL.Container childContainer = (DPL.Container) child;
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(childContainer.getObjectID());
                parentNode.add(childNode);
                addNodes(childNode, childContainer);
            }
        }
    }

    private void showContextMenu(MouseEvent e) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem addItem = new JMenuItem("Add Item");
        JMenuItem addContainer = new JMenuItem("Add Container");
        JMenuItem remove = new JMenuItem("Remove");

        addItem.addActionListener(ae -> onAddItem());
        addContainer.addActionListener(ae -> onAddContainer());
        remove.addActionListener(ae -> onRemove());

        menu.add(addItem);
        menu.add(addContainer);
        menu.add(remove);

        menu.show(tree, e.getX(), e.getY());
    }

    private void onAddContainer() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode == null) return;

        String nodeName = JOptionPane.showInputDialog("Enter name for new container:");
        if (nodeName != null && !nodeName.isEmpty()) {
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeName);
            selectedNode.add(newNode);
            treeModel.reload();
        }
    }

    private void onAddItem() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode == null) return;

        String itemName = JOptionPane.showInputDialog("Enter name for new item:");
        if (itemName != null && !itemName.isEmpty()) {
            DefaultMutableTreeNode newItemNode = new DefaultMutableTreeNode(itemName);
            selectedNode.add(newItemNode);
            treeModel.reload();
        }
    }

    private void onRemove() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null && selectedNode.getParent() != null) {
            treeModel.removeNodeFromParent(selectedNode);
            treeModel.reload();
        }
    }

    public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
        private Icon roomIcon;
        private Icon shelfIcon;
        private Icon containerIcon;
        private Icon itemIcon;
        public CustomTreeCellRenderer() {
            // Load icons
            roomIcon = resizeIcon(new ImageIcon("res/roomIcon.png"));
            shelfIcon = resizeIcon(new ImageIcon("res/shelfIcon.png"));
            containerIcon = resizeIcon(new ImageIcon("res/containerIcon.png"));
            itemIcon = resizeIcon(new ImageIcon("res/itemIcon.png"));
        }

        private Icon resizeIcon(ImageIcon icon) {
            Image img = icon.getImage();
            Image resizedImage = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            String nodeName = node.toString();

            // Assign icon based on the node level or type
            if (node.isRoot()) {
                setIcon(roomIcon);
            } else if (node.getChildCount() > 0 && !leaf) {
                setIcon(shelfIcon);
            } else if (!leaf) {
                setIcon(containerIcon);
            } else {
                setIcon(itemIcon);
            }

            return this;
        }
    }
}
