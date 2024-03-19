import javax.swing.*;
import java.awt.*;

public class GuestSidePanel extends JPanel{
    public GuestSidePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addButtons();

    }

    public void addButtons() {
        //Creates a default size
        Dimension buttonSize = new Dimension(272, 251);

        //Init home buttons
        ImageIcon homeIcon = new ImageIcon("../../../resources/button-icons/home-icon.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.setPreferredSize(buttonSize);
        homeButton.setMaximumSize(buttonSize);

        //Init view reservations button
        ImageIcon viewReservationsIcon = new ImageIcon("../../../resources/button-icons/reservations-icon.png");
        JButton viewReservationsButton = new JButton(viewReservationsIcon);
        viewReservationsButton.setPreferredSize(buttonSize);
        viewReservationsButton.setMaximumSize(buttonSize);
        
        //Init view reservations button
        ImageIcon checkoutIcon = new ImageIcon("../../../resources/button-icons/checkout-icon.png");
        JButton checkoutButton = new JButton(checkoutIcon);
        checkoutButton.setPreferredSize(buttonSize);
        checkoutButton.setMaximumSize(buttonSize);

        add(homeButton);
        add(viewReservationsButton);
        add(checkoutButton);

    }
}
