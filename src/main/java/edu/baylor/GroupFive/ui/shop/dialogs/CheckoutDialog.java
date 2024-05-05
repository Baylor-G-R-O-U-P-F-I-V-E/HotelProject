package edu.baylor.GroupFive.ui.shop.dialogs;


import edu.baylor.GroupFive.database.controllers.BillingController;
import edu.baylor.GroupFive.database.controllers.StockController;
import edu.baylor.GroupFive.models.Stock;
import edu.baylor.GroupFive.ui.shop.AddShopModel;
import edu.baylor.GroupFive.ui.shop.ShopPanel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Dialog for checking out items in the cart.
 * 
 * Extends {@link javax.swing.JDialog}.
 * 
 * @author Chase
 * @author Siri
 */
public class CheckoutDialog extends JDialog {

    private ShopPanel owner;
    private JTable cartTable;
    private JLabel subtotalLabel;
    private AddShopModel model;

    /**
     * Constructs a new CheckoutDialog with the specified owner, cart table, and subtotal label.
     * @param owner
     * @param cartTable
     * @param subtotalLabel
     */
    public CheckoutDialog(ShopPanel owner, JTable cartTable, AddShopModel model, JLabel subtotalLabel) {
        super(SwingUtilities.windowForComponent(cartTable));
        this.owner = owner;
        this.cartTable = cartTable;
        this.subtotalLabel = subtotalLabel;
        this.model = model;
        createGUI();
    }

    /**
     * Creates the GUI for the dialog.
     */
    private void createGUI() {
        // Sets up dialog panel
        setPreferredSize(new Dimension(600, 400));
        setTitle("Checkout Items");

        // Sets up list
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
        
        // Create a duplicate cart table
        JTable duplicateCart = duplicateCart(cartTable);
        JScrollPane cartItems = new JScrollPane(duplicateCart);
        cartItems.setPreferredSize(new Dimension(600, 200));
        listPane.add(cartItems);

        // Create checkout button
        JFrame frame = new JFrame();
        JButton checkoutButton = new JButton("Checkout Items");
        checkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkoutButton.addActionListener((e -> {
            for (int i = cartTable.getRowCount() - 1 ; i >= 0; --i) {

                // Get transaction details
                String description = String.valueOf(cartTable.getValueAt(i, 1));
                float amount = Float.parseFloat(String.valueOf(cartTable.getValueAt(i,2)))
                        * Float.parseFloat(String.valueOf(cartTable.getValueAt(i,3)));

                // Take stock from database
                Stock stock = StockController.getStockByProductID(Integer.parseInt(String.valueOf(cartTable.getValueAt(i, 0))));
                Boolean stockResult = StockController.takeStock(stock.getStockID(), Integer.parseInt(String.valueOf(cartTable.getValueAt(i, 3))));

                // Check if stock was successfully taken
                if (!stockResult) {
                    JOptionPane.showMessageDialog(frame, "Failed to checkout items. Please try again.");
                    return;
                }

                // Add transaction to billing
                BillingController.addTransaction(owner.getUsername(), description, amount);

                ((DefaultTableModel) cartTable.getModel()).removeRow(i);

                model.refreshData();
                
            }
            JOptionPane.showMessageDialog(frame, "Items added to your Stay Bill!");
        }));

        listPane.add(checkoutButton);
        add(listPane);
        pack();
        setLocationRelativeTo(getParent());

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private JTable duplicateCart(JTable cartTable) {
        String[][] cart = new String[cartTable.getRowCount()][cartTable.getColumnCount()];
        for(int i = 0; i < cartTable.getRowCount(); ++i) {
            for(int j = 0; j < cartTable.getColumnCount(); ++j) {
                cart[i][j] = String.valueOf(cartTable.getValueAt(i,j));
            }
        }
        String[] vals = new String[cartTable.getColumnCount()];
        for(int i = 0; i < cartTable.getColumnCount(); ++i) {
            vals[i] = cartTable.getColumnName(i);
        }
        return new JTable(cart, vals);
    }


}
