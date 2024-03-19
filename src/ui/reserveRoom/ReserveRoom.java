import javax.swing.SwingUtilities;
import java.awt.*;

class ReserveRoom {
    public static void ReserveRoomGUI() {
        //Init new frame
        ReserveRoomFrame frame = new ReserveRoomFrame();
        frame.setVisible(true);

        //Create constraints to add elements to the frame
        GridBagConstraints gbc = new GridBagConstraints();

        //Set the constraints for the GuestSidePanel
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        //Add the GuestSidePanel to the frame
        frame.add(new GuestSidePanel(), gbc);

        //Set the constraints for the ReserveRoomPanel
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Add the ReserveRoomPanel to the frame
        frame.add(new ReserveRoomPanel(), gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ReserveRoomGUI();
            }
        });
    }
}