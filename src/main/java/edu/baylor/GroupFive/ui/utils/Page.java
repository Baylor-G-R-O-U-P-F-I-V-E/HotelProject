package edu.baylor.GroupFive.ui.utils;

import javax.swing.*;

import edu.baylor.GroupFive.ui.reserveRoom.ReserveRoomPanel;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

import java.awt.*;

public class Page extends JFrame implements InputDelegate {

    private Dashboard dashboard;
    private GridBagConstraints constraints = new GridBagConstraints();
    
    public Page(String privilige) {
        super();
        //Init new frame
        createFrame();

        //Create constraints to add dashboard to the frame
        dashboard = new Dashboard(this, privilige);
        addDashboard();

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(new ReserveRoomPanel(), constraints);
    }

    public void createFrame() {
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0xe6f7ff));
        setLayout(new GridBagLayout());
        setVisible(true);
    }

    public void addDashboard() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        add(dashboard, constraints);
    }

    public void onPageSwitch() {
        //todo: add logic for switching pages on dashboard button press
        setVisible(true);
    }


}
