package edu.baylor.GroupFive.ui.generateBill.dialogs;

import edu.baylor.GroupFive.database.controllers.BillingController;
import edu.baylor.GroupFive.database.services.TransactionService;
import edu.baylor.GroupFive.models.Transaction;
import edu.baylor.GroupFive.ui.generateBill.GuestBillPanel;
import edu.baylor.GroupFive.ui.shop.ShopPanel;

import javax.swing.*;
import java.awt.*;

public class PayBillDialog extends JDialog {

    private GuestBillPanel owner;
    private JTable billTable;
    public PayBillDialog(GuestBillPanel owner, JTable billTable) {
        super(SwingUtilities.windowForComponent(owner));
        this.owner = owner;
        this.billTable = billTable;
        createGUI();
    }


    private void createGUI() {
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

        JPanel cardPanel = new JPanel();
        JTextField cardNumber = new JTextField();
        cardNumber.setPreferredSize(new Dimension(200, 30));
        cardPanel.add(new JLabel("Card number:"));
        cardPanel.add(cardNumber);
        listPane.add(cardPanel);

        JPanel cvvPanel = new JPanel();
        JTextField cvv = new JTextField();
        cvv.setPreferredSize(new Dimension(200, 30));
        cvvPanel.add(new JLabel("Cvv:"));
        cvvPanel.add(cvv);
        listPane.add(cvvPanel);

        JPanel expPanel = new JPanel();
        JTextField expirationDate = new JTextField();
        expirationDate.setPreferredSize(new Dimension(200, 30));
        expPanel.add(new JLabel("Expiry date:"));
        expPanel.add(expirationDate);
        listPane.add(expPanel);

        JPanel namePanel = new JPanel();
        JTextField cardName = new JTextField();
        cardName.setPreferredSize(new Dimension(200, 30));
        namePanel.add(new JLabel("Name on card:"));
        namePanel.add(cardName);
        listPane.add(namePanel);

        JButton payButton = new JButton("Pay bill of $"+owner.getSubtotal());
        listPane.add(payButton);

        payButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Confirm payment?");
            if(response == JOptionPane.OK_OPTION){
                for(int row = 0; row < this.billTable.getRowCount(); row++){
                    Long id = (Long) this.billTable.getValueAt(row, 0);
                    System.out.println("deleting transaction id "+id);
                    Transaction txn  = TransactionService.getTransaction(id);
                    BillingController.deleteTransaction(txn);
                }
            }
        });



        add(listPane);
        pack();
        setLocationRelativeTo(getParent());
    }

}
