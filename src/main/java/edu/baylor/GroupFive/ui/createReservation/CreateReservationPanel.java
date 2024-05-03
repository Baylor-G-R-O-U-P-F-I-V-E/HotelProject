package edu.baylor.GroupFive.ui.createReservation;

import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.baylor.GroupFive.database.controllers.AccountController;
import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.ui.reserveRoom.ReserveRoomPanel;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;

/**
 * Panel for creating a reservation for a guest.
 * This panel extends {@link edu.baylor.GroupFive.ui.reserveRoom.ReserveRoomPanel}
 * and provides functionality for creating a reservation for a guest.
 * 
 * What does this need to have?
 * - A table of available rooms
 * - A button to reserve a room
 * - A button to adjust the dates
 * 
 * @see edu.baylor.GroupFive.ui.reserveRoom.ReserveRoomPanel
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @see edu.baylor.GroupFive.ui.utils.Page
 * 
 * @author Brendon
 */
public class CreateReservationPanel extends ReserveRoomPanel {

    @SuppressWarnings("unused")
    private Page page;

    public CreateReservationPanel(Page page) {
        super(page);
        this.page = page;

        title.setText("Create Reservation For Guest");

        remove(this.buttonPanel);

        addButtonPanel();

    }

    @Override
    public void addButtonPanel() {
        // Create button panel
        buttonPanel = new JPanel();

        // Create buttons
        PanelButton reserveRoom = new PanelButton("Reserve Room");
        PanelButton adjustDates = new PanelButton("Adjust Dates");

        // Add buttons to panel
        addButtonListeners(reserveRoom, adjustDates);
        buttonPanel.add(reserveRoom);
        buttonPanel.add(adjustDates);

        add(buttonPanel);

    }

    @Override
    /**
     * Adds action listeners to buttons.
     *
     * @param reserveRoom The reserve room button.
     * @param viewRoom The view room button.
     * @param adjustDates The adjust dates button.
     */
    public void addButtonListeners(JButton reserveRoom, JButton adjustDates) {
        reserveRoom.addActionListener(e -> {
            int row = super.getTable().getSelectedRow();
            if (row != -1) {
                List<Date> dates = showDates();
                if (dates != null) {
                    promptReservation(dates.get(0), dates.get(1));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a reservation to view.");
            }
        });

        adjustDates.addActionListener(e -> {
            
            // Get the dates from the user
            List<Date> dates = showDates();

            // Filter the rooms by the selected dates
            if (dates != null) {
                filterRoomsByDate(dates.get(0), dates.get(1));
            }

        });
    }

    /**
     * Prompts the user to confirm the reservation.
     *
     * @param startDate The start date of the reservation.
     * @param endDate The end date of the reservation.
     */
    public void promptReservation(Date startDate, Date endDate) {

        // Ensure dates are valid
        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(null, "Please enter valid dates.");
            return;
        }

        // If start date is before current date
        if (startDate.before(new Date())) {
            JOptionPane.showMessageDialog(null, "Start date cannot be before today.");
            return;
        }

        // If end date is before start date
        if (endDate.before(startDate)) {
            JOptionPane.showMessageDialog(null, "End date cannot be before start date.");
            return;
        }

        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to reserve this room?", "Confirm Reservation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Ask for the guest username
            String guestUsername = JOptionPane.showInputDialog("Enter the guest username:");

            // Check if the guest username is valid
            if (guestUsername == null || guestUsername.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid guest username.");
            }

            // Check if guest has an account
            if (AccountController.getUser(guestUsername) == null) {
                JOptionPane.showMessageDialog(null, "Guest does not have an account.");
            }

            // Create the reservation
            else {
                // Get the room number
                int roomNumber = Integer.parseInt((String) super.getTable().getValueAt(super.getTable().getSelectedRow(), 0));

                Room room = RoomController.getRoomInfo(roomNumber);

                if (room == null) {
                    JOptionPane.showMessageDialog(null, "Error getting room information.");
                    return;
                }

                // Get the price
                double price = room.getDailyPrice();

                // Check if the room is available
                if (!RoomController.isRoomAvailable(roomNumber, startDate, endDate)) {
                    JOptionPane.showMessageDialog(null, "Room is not available for the selected dates.");
                    return;
                }

                Reservation prev = ReservationController.getReservation(roomNumber, startDate);

                if (prev != null) {
                    if (prev.getActiveStatus()) {
                        JOptionPane.showMessageDialog(null, "Room is already reserved for the selected dates.");
                        return;
                    }
                    // Update the prev reservation values
                    prev.setEndDate(endDate);
                    prev.setActiveStatus(true);
                    prev.setCheckedInStatus(false);
                    prev.setPrice(price);
                    prev.setGuestID(guestUsername);
                    prev.setRoomID(String.valueOf(roomNumber));
                    prev.setStartDate(startDate);

                    Boolean updateResult = ReservationController.modifyReservation(prev);

                    if (!updateResult) {
                        JOptionPane.showMessageDialog(null, "Error updating reservation.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Reservation made successfully.");
                    }

                    return;

                }

                // Create the reservation
                Boolean createResult = ReservationController.createReservation(new Reservation(startDate, endDate, guestUsername, String.valueOf(roomNumber), price, true, false));

                // Check if the reservation was created successfully
                if (!createResult) {
                    JOptionPane.showMessageDialog(null, "Error creating reservation.");
                } else {
                    JOptionPane.showMessageDialog(null, "Reservation created successfully.");
                }
            }
            
        }
    }

    @Override
    public void clear() {
        super.clear();
        buttonPanel.removeAll();
    }

}
