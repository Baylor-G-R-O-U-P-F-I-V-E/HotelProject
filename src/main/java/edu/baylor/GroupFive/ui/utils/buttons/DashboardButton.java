package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.Dimension;

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
