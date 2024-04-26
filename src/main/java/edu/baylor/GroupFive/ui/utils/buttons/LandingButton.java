package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.Dimension;

/**
 *
 */
public class LandingButton extends PageButton {
    Dimension buttonSize = new Dimension(400, 125);

    /**
     *
     * @param path
     */
    public LandingButton(String path) {
        super(path);
        setPreferredSize(buttonSize);
        setMaximumSize(buttonSize);
        setMinimumSize(buttonSize);

        revalidate();
        repaint();
    }

    
}
