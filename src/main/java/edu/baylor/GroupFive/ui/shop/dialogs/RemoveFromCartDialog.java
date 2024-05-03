package edu.baylor.GroupFive.ui.shop.dialogs;


import edu.baylor.GroupFive.database.services.StockServices;
import edu.baylor.GroupFive.models.Stock;
import edu.baylor.GroupFive.ui.shop.ShopPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveFromCartDialog extends JDialog {

    private ShopPanel owner;
    private JTable cartTable;
    private JLabel subtotalLabel;

    public RemoveFromCartDialog(ShopPanel owner, JTable cartTable, JLabel subtotalLabel) {
        super(SwingUtilities.windowForComponent(cartTable));
        this.owner = owner;
        this.cartTable = cartTable;
        this.subtotalLabel = subtotalLabel;
        createGUI();
    }


    private void createGUI() {
        // Sets up dialog panel
        setPreferredSize(new Dimension(600, 400));
        setTitle("Add to cart");

        // Sets up list
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

        if (cartTable.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this, "You must select exactly one item type to remove from your cart.");
            return;
        }

        List<JLabel> labels = new ArrayList<>();

        // Create labels for each field
        JLabel quantityLabel = new JLabel("Number to remove:");
        // Add labels to list
        labels.add(quantityLabel);

        // Create textfields for each field
        JTextField quantityField = new JTextField();
        int row = cartTable.getSelectedRow();
        Integer numAlreadyInCart = (Integer) cartTable.getValueAt(row, 3);
        quantityField.setText(numAlreadyInCart.toString()); // default to all items removed
        // Set default textfield sizes
        quantityField.setPreferredSize(new Dimension(200, 30));

        // Add textfields to list
        List<Component> textFields = new ArrayList<>();
        textFields.add(quantityField);

        // Make a new panel for each text field and label and add them
//        System.out.println("ccount: "+ cartTable.getColumnCount());
        for (int i = 0; i < textFields.size(); i++) {
            JPanel panel = new JPanel();
            panel.add(labels.get(i));
            panel.add(textFields.get(i));
            listPane.add(panel);
        }

        // Makes add row button
        JButton addButton = new JButton("Remove items from cart");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener((e) -> {
//                // Check that all fields are filled
            int numToRemove = Integer.parseInt(quantityField.getText());
            if(numToRemove > numAlreadyInCart){
                JOptionPane.showMessageDialog(this.owner, "Cannot remove more items than are present in cart. Please remove "+numAlreadyInCart+" or less items.");
                return;
            }

            Boolean valid = true; //RoomController.addRoom(room);

            if (!valid) {
                JOptionPane.showMessageDialog(cartTable, "Failed to add room to database.");
                return;
            }
            if(numAlreadyInCart - numToRemove == 0)
                ((DefaultTableModel) cartTable.getModel()).removeRow(row);
            else
                cartTable.getModel().setValueAt(numAlreadyInCart - numToRemove, row, 3);

            this.owner.updateSubTotal();

            dispose();
            JOptionPane.showMessageDialog(cartTable, "Items removed from your cart.");
        });
        listPane.add(addButton);
        add(listPane);
        pack();
        setLocationRelativeTo(getParent());
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
