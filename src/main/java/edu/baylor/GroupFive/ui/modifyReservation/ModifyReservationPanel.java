package edu.baylor.GroupFive.ui.modifyReservation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.ui.utils.DatePanel;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

/**
 * Panel for modifying a reservation.
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Brendon
 */
public class ModifyReservationPanel extends JPanel implements PagePanel {

    private Page delegate;
    private Dimension fieldSize = new Dimension(200, 50);
    private JTextField roomID, price;
    private DatePanel startDate, endDate;
    private String originalRoom;
    private Date originalStartDate;
    private Reservation reservation;

    /**
     * Constructs a ModifyReservationPanel with the specified parameters.
     *
     * @param delegate      The page delegate for handling user interactions.
     * @param originalRoom  The original room number of the reservation.
     * @param originalStart The original start date of the reservation.
     * @author Brendon
     */
    public ModifyReservationPanel(Page delegate, String originalRoom, String originalStart) {
        super();
        this.delegate = delegate;
        this.originalRoom = originalRoom;
        SimpleDateFormat sdf = new SimpleDateFormat(CoreUtils.DATE_FORMAT);

        try {
            this.originalStartDate = sdf.parse(originalStart);
        } catch (Exception e) {
            e.printStackTrace();
            delegate.onPageSwitch("home");
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        reservation = ReservationController.getReservation(Integer.parseInt(originalRoom), originalStartDate);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        addRoomPanel(textPanel);
        addPricePanel(textPanel);

        startDate = new DatePanel("Start Date:");
        startDate.setDate(reservation.getStartDate());
        startDate.setBackground(new Color(0xF9F7FA));
        endDate = new DatePanel("End Date:", 1);
        endDate.setDate(reservation.getEndDate());
        endDate.setBackground(new Color(0xF9F7FA));

        textPanel.add(startDate);
        textPanel.add(endDate);

        textPanel.setOpaque(false);

        add(textPanel);

        JPanel buttonPanel = new JPanel();

        addModifyReservationButton(buttonPanel);
        addBackButton(buttonPanel);

        buttonPanel.setOpaque(false);

        add(buttonPanel);

        setVisible(true);

        setSize(400, 400);

    }

    /**
     * Adds a panel for entering the room number to the specified panel.
     *
     * @param textPanel The panel to which the room number panel will be added.
     * @author Brendon
     */
    public void addRoomPanel(JPanel textPanel) {
        JPanel roomPanel = new JPanel();
        roomPanel.setOpaque(true);
        roomPanel.setBackground(new Color(0xF9F7FA));

        JLabel roomLabel = new JLabel("Room Number:");
        roomLabel.setBounds(200, 50, 200, 50);
        roomLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        roomID = new JTextField();
        roomID.setText(String.valueOf(reservation.getRoomNumber()));
        roomID.setBounds(400, 50, 200, 50);
        roomID.setFont(new Font("Arial", Font.PLAIN, 20));
        roomID.setPreferredSize(fieldSize);

        roomPanel.add(roomLabel);
        roomPanel.add(roomID);

        textPanel.add(roomPanel);
    }

    /**
     * Adds a panel for entering the price to the specified panel.
     *
     * @param textPanel The panel to which the price panel will be added.
     * @author Brendon
     */
    public void addPricePanel(JPanel textPanel) {
        JPanel pricePanel = new JPanel();
        pricePanel.setOpaque(true);
        pricePanel.setBackground(new Color(0xF9F7FA));

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(200, 50, 200, 50);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        price = new JTextField();
        price.setText(String.valueOf(reservation.getPrice()));
        price.setBounds(400, 50, 200, 50);
        price.setFont(new Font("Arial", Font.PLAIN, 20));
        price.setPreferredSize(fieldSize);
        price.setMaximumSize(fieldSize);

        pricePanel.add(priceLabel);
        pricePanel.add(price);

        textPanel.add(pricePanel);
    }

    /**
     * Adds a button for modifying the reservation to the specified panel.
     *
     * @param buttonPanel The panel to which the modify reservation button will be added.
     * @author Brendon
     */
    public void addModifyReservationButton(JPanel buttonPanel) {
        PanelButton modifyButton = new PanelButton("Confirm");

        // Add an action listener to the button
        modifyButton.addActionListener(new ModifyReservationActionListener(delegate, originalRoom, originalStartDate, roomID, price, startDate, endDate));

        buttonPanel.add(modifyButton);
    }

    /**
     * Adds a back button to the specified panel.
     *
     * @param buttonPanel The panel to which the back button will be added.
     * @author Brendon
     */
    public void addBackButton(JPanel buttonPanel) {
        PanelButton backButton = new PanelButton("Back");

        // Add an action listener to the button
        backButton.addActionListener(e -> delegate.onPageSwitch("home"));

        buttonPanel.add(backButton);
    }

    /**
     * Clears the input fields.
     *
     * @author Brendon
     */
    @Override
    public void clear() {
        roomID.setText("");
        price.setText("");
        startDate.setDate(new Date());
        endDate.setDate(new Date());
    }
}
