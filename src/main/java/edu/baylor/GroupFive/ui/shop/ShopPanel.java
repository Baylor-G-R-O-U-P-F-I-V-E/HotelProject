package edu.baylor.GroupFive.ui.shop;

import javax.swing.*;

import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.shop.dialogs.AddToCartDialog;
import edu.baylor.GroupFive.ui.shop.dialogs.CheckoutDialog;
import edu.baylor.GroupFive.ui.shop.dialogs.RemoveFromCartDialog;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

import java.awt.*;

/**
 * Panel for the shop.
 * 
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Brendon
 * @author Siri
 */
public class ShopPanel extends JPanel implements PagePanel {

    private Page page;
    private User user;
    private HotelTable table;
    private HotelTable cartTable;
    private AddShopModel model;
    private JLabel subtotalLabel;
    //private ProductTable table;

    private String[] columnNames = {
            "Product ID",
            "Description",
            "Cost",
            "# In Stock"
    };

    private String[] cartColumnNames = {
        "Product ID",
        "Description",
        "Cost",
        "# in Cart"
    };

    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class
    };

    /**
     * Constructs a new ShopPanel with the specified delegate
     *
     * // @param delegate Page
     */
    public ShopPanel(Page page, User user) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.page = page;
        this.user = user;

        // Create a model of the data.
        model = new AddShopModel(columnNames, columnClass);

        // Create a table with a sorter.
        table = new HotelTable(model);

        // Create a table for the cart
        cartTable = new HotelTable(new HotelModel(cartColumnNames, columnClass));

        // Add the table to a scroll pane.
        JScrollPane scrollPane = new JScrollPane(table);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add a title
        JLabel title = new JLabel("Products");
        title.setFont(new java.awt.Font("Arial", Font.BOLD, 36));
        title.setAlignmentX(Panel.CENTER_ALIGNMENT);
        add(title);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the scroll pane to this panel.
        add(scrollPane);
        add(cartScrollPane);

        // Add the form pane
        add(new FormPane(table, table.getSorter(), columnNames));

        // Add the button panel.
        addButtonPanel();

        // Add some glue
        add(Box.createVerticalGlue());

    }

    /**
     * Updates the subtotal label.
     */
    public void updateSubTotal(){
        double subTotal = 0;

        // Calculate the subtotal
        for (int row = 0; row < cartTable.getRowCount(); row++) {
            int numItem = (Integer) cartTable.getValueAt(row, 3);
            double unitPrice = Double.parseDouble((String) cartTable.getValueAt(row, 2));
            subTotal += numItem*unitPrice;
        }

        // Update the subtotal label
        double finalSubTotal = subTotal;
        SwingUtilities.invokeLater(() -> {
            this.subtotalLabel.setText("Subtotal: "+ finalSubTotal);
        });

    }

    /**
     * Adds the button panel to the panel.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();

        // Add the subtotal label
        this.subtotalLabel = new JLabel("Subtotal: 0.00");
        buttonPanel.add(this.subtotalLabel);

        // Add to cart button
        PanelButton addToCartButton = new PanelButton("Add To Cart", 200, 50);
        addToCartButton.addActionListener(e -> {
            // Show the dialog to add to cart
            AddToCartDialog dialog = new AddToCartDialog(this, table, cartTable, subtotalLabel);
            dialog.setVisible(true);
        });
        buttonPanel.add(addToCartButton);

        // Remove from cart button
        PanelButton removeFromCartButton = new PanelButton("Remove From Cart", 300, 50);
        removeFromCartButton.addActionListener(e -> {
            // Show the dialog to remove from cart
            RemoveFromCartDialog dialog = new RemoveFromCartDialog(this, cartTable);
            dialog.setVisible(true);
        });
        buttonPanel.add(removeFromCartButton);

        // Checkout button
        PanelButton checkoutButton = new PanelButton("Checkout", 200, 50);
        checkoutButton.addActionListener(e -> {

            // Check if cart is empty
            if (cartTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "You must have at least one item in your cart to checkout.");
                return;
            }

            CheckoutDialog dialog = new CheckoutDialog(this, cartTable, model);
            dialog.setVisible(true);

        });
        buttonPanel.add(checkoutButton);

        // Add the button panel to the panel.
        add(buttonPanel);
    }

    /**
     * Returns the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Clears the panel (but does nothing atm).
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
