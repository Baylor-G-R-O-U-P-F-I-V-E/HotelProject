package reserveRoom;

import javax.swing.*;

public class GuestSidePanel extends JPanel{
    public GuestSidePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Guest Side Panel"));
    }
}
