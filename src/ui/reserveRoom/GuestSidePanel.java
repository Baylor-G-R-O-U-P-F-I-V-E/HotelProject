import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GuestSidePanel extends JPanel{
    public GuestSidePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addButtons();

    }

    public void addButtons() {
        //Creates a default size
        Dimension buttonSize = new Dimension(250, 250);

        //Init home button
        try {
            Image homeImage = ImageIO.read(new File("resources/button-icons/home-icon.png"));
            ImageIcon homeIcon = new ImageIcon(homeImage);
            JButton homeButton = new JButton(homeIcon);
            homeButton.setPreferredSize(buttonSize);
            homeButton.setMaximumSize(buttonSize);
            add(homeButton);
        } catch (Exception e) {
            System.out.println("Error: home-icon.png not found");
        }

        //Init view reservations button
        try {
            Image viewReservationsImage = ImageIO.read(new File("resources/button-icons/reservations-icon.png"));
            ImageIcon viewReservationsIcon = new ImageIcon(viewReservationsImage);
            JButton viewReservationsButton = new JButton(viewReservationsIcon);
            viewReservationsButton.setPreferredSize(buttonSize);
            viewReservationsButton.setMaximumSize(buttonSize);
            add(viewReservationsButton);
        } catch (Exception e) {
            System.out.println("Error: reservations-icon.png not found");
        }
        
        //Init view reservations button
        try {
            Image viewReservationsImage = ImageIO.read(new File("resources/button-icons/reservations-icon.png"));
            ImageIcon checkoutIcon = new ImageIcon(viewReservationsImage);
            JButton checkoutButton = new JButton(checkoutIcon);
            checkoutButton.setPreferredSize(buttonSize);
            checkoutButton.setMaximumSize(buttonSize);
            add(checkoutButton);
        } catch (Exception e) {
            System.out.println("Error: reservations-icon.png not found");
        }

    }
}
