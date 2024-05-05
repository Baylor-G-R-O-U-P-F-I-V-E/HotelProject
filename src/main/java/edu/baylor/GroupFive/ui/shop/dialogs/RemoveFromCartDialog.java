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

    public RemoveFromCartDialog(ShopPanel owner, JTable cartTable) {
        super();
        this.owner = owner;
        this.cartTable = cartTable;

        // Check that exactly one item is selected
        if (cartTable.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this, "You must select exactly one item type to remove from your cart.");
            return;
        }

        createGUI();

        pack();
        setLocationRelativeTo(getParent());
    }


    private void createGUI() {
        // Sets up dialog panel
        setPreferredSize(new Dimension(300, 150));
        setTitle("Remove from Cart");

        // Sets up list
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

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

            // Check that all fields are filled
            if (quantityField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "You must enter a quantity to remove.");
                dispose();
                return;
            }

            // Check that the quantity is a number
            if (!quantityField.getText().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
                dispose();
                return;
            }

            // Get the number of items to remove
            int numToRemove = Integer.parseInt(quantityField.getText());

            if (numToRemove <= 0) {
                JOptionPane.showMessageDialog(this, "You must remove at least one item from your cart.");
                dispose();
                return;
            }

            // Get the number of items already in the cart
            if (numToRemove > numAlreadyInCart) {
                JOptionPane.showMessageDialog(this, "Cannot remove more items than are present in cart. Please remove "+numAlreadyInCart+" or less items.");
                dispose();
                return;
            }

            // Update the cart
            if (numAlreadyInCart - numToRemove == 0)
                ((DefaultTableModel) cartTable.getModel()).removeRow(row);
            else
                cartTable.getModel().setValueAt(numAlreadyInCart - numToRemove, row, 3);

            this.owner.updateSubTotal();

            // Close the dialog
            JOptionPane.showMessageDialog(cartTable, "Items removed from your cart.");
        });
        listPane.add(addButton);
        add(listPane);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
