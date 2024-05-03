package edu.baylor.GroupFive.ui.reserveRoom;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.DatePanel;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

/**
 * A panel for reserving rooms.
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Brendon
 */
public class ReserveRoomPanel extends JPanel implements PagePanel {

    private JTable table;
    private Page delegate;

    private String[] columnNames = {
            "Room",
            "Quality",
            "Theme",
            "Smoking",
            "Number of Beds",
            "Bed Type",
            "Nightly Rate"
    };

    // Define data types for the columns
    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class, String.class, String.class, String.class,
            String.class
    };

    /**
     * Constructs a ReserveRoomPanel with the specified page delegate.
     *
     * @param page The page delegate.
     */
    public ReserveRoomPanel(Page page) {
        super();
        this.delegate = page;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create a model of the data.
        DefaultTableModel model = new AddReservationModel(columnNames, columnClass);

        // Create a table with a sorter.
        table = new HotelTable(model);

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        // Create a title
        JLabel title = new JLabel("Available Rooms");
        title.setFont(new java.awt.Font("Arial", Font.BOLD, 36));
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the title
        add(title);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the scroll pane to this panel.
        add(scrollPane);

        // Add the form pane
        add(new FormPane(table, ((HotelTable) table).getSorter(), columnNames));

        // Add the button panel
        addButtonPanel();

        // Set the panel properties
        setVisible(true);

    }

    /**
     * Adds buttons to the button panel.
     */
    private void addButtonPanel() {
        // Create button panel
        JPanel buttonPanel = new JPanel();

        // Create buttons
        PanelButton reserveRoom = new PanelButton("Reserve Room");
        PanelButton adjustDates = new PanelButton("Adjust Dates");

        // Add buttons to panel
        addButtonListeners(reserveRoom, adjustDates);
        buttonPanel.add(reserveRoom);
        buttonPanel.add(adjustDates);

        add(buttonPanel);
    }

    /**
     * Adds action listeners to buttons.
     *
     * @param reserveRoom The reserve room button.
     * @param viewRoom The view room button.
     * @param adjustDates The adjust dates button.
     */
    private void addButtonListeners(JButton reserveRoom, JButton adjustDates) {
        reserveRoom.addActionListener(e -> {
            int row = table.getSelectedRow();
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
     * Shows a dialog to input start and end dates.
     *
     * @return A List containing start and end dates.
     */
    public List<Date> showDates() {
        DatePanel startDatePanel = new DatePanel("");
        int result = JOptionPane.showConfirmDialog(null, startDatePanel, "Select a start date",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Date startDate = startDatePanel.getDate();

            DatePanel endDatePanel = new DatePanel("", 1);

            result = JOptionPane.showConfirmDialog(null, endDatePanel, "Select an end date",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                Date endDate = endDatePanel.getDate();
                System.out.println(startDate);
                System.out.println(endDate);
                if (startDate.after(endDate)) {
                    JOptionPane.showMessageDialog(null, "End date must be after start date.");
                    return null;
                }
                List<Date> dates = new ArrayList<>();
                dates.add(startDate);
                dates.add(endDate);
                return dates;
            } else {
                JOptionPane.showMessageDialog(null, "Please enter both start and end dates.");
            }
        }
        return null;
    }

    /**
     * Prompts reservation for a room with given start and end dates.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     */
    public void promptReservation(Date startDate, Date endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MM/dd/yyyy");

        // Create an array of options to be displayed in the dialog
        Object[] options = { "Reserve" };
        String room = (String) table.getValueAt(table.getSelectedRow(), 0);

        // Create the JOptionPane
        int choice = JOptionPane.showOptionDialog(null,
                "Room: " + room + "\nStart Date: " + formatter.format(startDate) + "\nEnd Date: "
                        + formatter.format(endDate),
                "Create Reservation?",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        // Check the user's choice
        if (choice == 0) {

            // The user clicked the "Reserve" button
            User user = delegate.getUser();
            Room roomObj = RoomController.getRoomInfo(Integer.parseInt(room));

            // Check if the room is already reserved
            if (ReservationController.isRoomBookedOn(roomObj.getRoomNumber(), startDate, endDate)) {
                JOptionPane.showMessageDialog(null, "Room is already reserved for the selected dates.\nPlease select different dates.");
                return;
            }

            Reservation prev = null;
            Boolean result = null;

            // Check if there is an inactive reservation for the room, if so update it
            if (((prev = ReservationController.getReservation(Integer.parseInt(room), startDate)) != null) && !prev.getActiveStatus()) {

                // Update the reservation
                prev.setStartDate(startDate);
                prev.setEndDate(endDate);
                prev.setGuestID(user.getUsername());
                prev.setRoomID(room);
                prev.setPrice(roomObj.getDailyPrice());
                prev.setCheckedInStatus(false);
                prev.setActiveStatus(true);

                result = ReservationController.modifyReservation(prev);

            // Otherwise, create a new reservation
            } else {
                result = ReservationController.createReservation(new Reservation(startDate, endDate, user.getUsername(), String.valueOf(roomObj.getRoomNumber()), roomObj.getDailyPrice(), true, false));
            }

            // Check if the reservation was successful
            if (!result) {
                JOptionPane.showMessageDialog(null, "Room could not be reserved for the selected dates.");
            } else {
                JOptionPane.showMessageDialog(null, "Room " + room + " reserved from " + formatter.format(startDate)
                        + " to " + formatter.format(endDate));
            }
        }
    }

    /**
     * Filters the rooms displayed in the table by the specified dates.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     */
    public void filterRoomsByDate(Date startDate, Date endDate) {
        
        if (startDate == null || endDate == null) {
            return;
        }
        
        // Filter the rooms by date
        ((AddReservationModel) table.getModel()).filterRoomsByDate(startDate, endDate);
    }

    /**
     * Get a date formatter.
     *
     * @return A date format of the form "EEEE MM/dd/yyyy"
     */
    public SimpleDateFormat getFormatter() {
        // Make a formatter for a date that looks like "Day of week MM/dd/yyyy"

        return new SimpleDateFormat("EEEE MM/dd/yyyy");
    }

    /**
     * Does nothing
     */
    @Override
    public void clear() {
        // Do nothing
    }
}
