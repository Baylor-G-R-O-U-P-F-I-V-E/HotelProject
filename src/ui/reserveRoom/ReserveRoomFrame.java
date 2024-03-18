// Purpose: This file contains the ReserveRoomFrame class which is a JFrame that will be used to reserve a room.
package reserveRoom;

import javax.swing.*;
import java.awt.*;

public class ReserveRoomFrame extends JFrame{
    public ReserveRoomFrame() {
        setTitle("Reserve Room");
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0xe6f7ff));
        setLayout(new GridBagLayout());
    }
}
