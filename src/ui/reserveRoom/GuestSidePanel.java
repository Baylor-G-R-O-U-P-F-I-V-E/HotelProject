package reserveRoom;

import javax.swing.*;

public class GuestSidePanel extends JPanel{
    public GuestSidePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Guest Side Panel"));
        addButtons();

    }

    public void addButtons() {
        JButton reserveRoomButton = new JButton("Reserve Room");
        JButton viewReservationsButton = new JButton("View Reservations");
        JButton viewRoomButton = new JButton("View Room");

        add(reserveRoomButton);
        add(viewReservationsButton);
        add(viewRoomButton);

    }
}
