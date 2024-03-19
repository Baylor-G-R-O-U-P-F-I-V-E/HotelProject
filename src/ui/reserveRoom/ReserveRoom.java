import javax.swing.SwingUtilities;
import java.awt.*;

class ReserveRoom {
    public static void ReserveRoomGUI() {
        //Init new frame
        ReserveRoomFrame frame = new ReserveRoomFrame();
        frame.setVisible(true);

        //Create constraints to add elements to the frame
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        
        //Add the GuestSidePanel to the frame
        frame.add(new GuestSidePanel(), gbc);
        gbc.gridx = 1;

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