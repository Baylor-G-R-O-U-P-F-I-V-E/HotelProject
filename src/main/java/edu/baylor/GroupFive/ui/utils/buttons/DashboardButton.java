package edu.baylor.GroupFive.ui.utils.dashboard;

import java.awt.Dimension;

import edu.baylor.GroupFive.ui.utils.buttons.PageButton;

public class DashboardButton extends PageButton {

    // Creates a default size
    Dimension buttonSize = new Dimension(150, 150);
    
    public DashboardButton(String path) {
        super(path);

        setPreferredSize(buttonSize);
        setMaximumSize(buttonSize);
        setMinimumSize(buttonSize);
    }
}
