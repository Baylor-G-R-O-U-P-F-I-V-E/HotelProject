package edu.baylor.GroupFive.ui.shop.dialogs;


import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import edu.baylor.GroupFive.database.services.StockServices;
import edu.baylor.GroupFive.models.Stock;
import edu.baylor.GroupFive.ui.shop.ShopPanel;

public class AddToCartDialog extends JDialog {

    private ShopPanel owner;
    private JTable shopTable;
    private JTable cartTable;

    public AddToCartDialog(ShopPanel owner, JTable shopTable, JTable cartTable) {
        super();
        this.owner = owner;
        this.shopTable = shopTable;
        this.cartTable = cartTable;
        createGUI();
    }


    private void createGUI() {
        // Sets up dialog panel
        setPreferredSize(new Dimension(300, 150));
        setTitle("Add to Cart");

        // Sets up list
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

        if (shopTable.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(this, "You can only add one item-type to cart at a time. Select exactly 1 item.");
            return;
        }

        List<JLabel> labels = new ArrayList<>();

        // Create labels for each field
        JLabel quantityLabel = new JLabel("Number to add:");
        // Add labels to list
        labels.add(quantityLabel);

        // Create textfields for each field
        JTextField quantityField = new JTextField();
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
        JButton addButton = new JButton("Add item(s) to cart");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener((e) -> {
//                // Check that all fields are filled
            int row = shopTable.getSelectedRow();
            int productID = Integer.parseInt((String) shopTable.getValueAt(row, 0));
            String itemDescription = (String) shopTable.getValueAt(row, 1);
            Stock stock = StockServices.getStockByProductID(productID);
            int numInStock = stock.getStock();
            
            // Check item is in stock
            if(numInStock <= 0){
                JOptionPane.showMessageDialog(cartTable, "Item '"+itemDescription+"' has no stock left, please select another item.");
                return;
            }

            // Check that the quantity is not empty
            if (quantityField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(cartTable, "Please enter a number of items to purchase.");
                return;
            }

            // Check that the quantity is a number
            if (!quantityField.getText().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(cartTable, "Please enter a valid number.");
                return;
            }

            // Check that the quantity is greater than 0
            if (Integer.parseInt(quantityField.getText()) <= 0) {
                JOptionPane.showMessageDialog(cartTable, "Please enter a number greater than 0.");
                return;
            }

            // Get stock remaining
            int quantityToAdd = Integer.parseInt(quantityField.getText());
            int numAlreadyInCart = 0;
            int existingItemRow = -1;

            // Check if we already have this item in cart
            boolean alreadyInCart = false;
            for(int cartRow = 0; cartRow < cartTable.getRowCount(); cartRow++){
                int curProductID = (Integer) cartTable.getValueAt(cartRow, 0);
                if(curProductID == productID) {
                    numAlreadyInCart = (Integer) cartTable.getValueAt(cartRow, 3);
                    alreadyInCart = true;
                    existingItemRow = cartRow;
                    break;
                }
            }
            if(quantityToAdd+numAlreadyInCart > numInStock){
                JOptionPane.showMessageDialog(cartTable, "You've tried to add more '"+itemDescription+"' to your cart than are in stock. Please add less than "+(numInStock - numAlreadyInCart)+" more of this item.");
                return;
            }

            Boolean valid = true; //RoomController.addRoom(room);

            if (!valid) {
                JOptionPane.showMessageDialog(cartTable, "Failed to add room to database.");
                return;
            }

            if(alreadyInCart){
                cartTable.getModel().setValueAt(quantityToAdd + numAlreadyInCart, existingItemRow, 3);
            }
            else {
                Object[] tableRow = new Object[] { productID, itemDescription, shopTable.getValueAt(row, 2), (quantityToAdd + numAlreadyInCart)  };
                ((javax.swing.table.DefaultTableModel) cartTable.getModel()).addRow(tableRow);
            }

            this.owner.updateSubTotal();

            dispose();
            JOptionPane.showMessageDialog(cartTable, "Items added to your cart.");
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
