package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.Dimension;

/**
 * Button for our landing page.
 *
 * Extends {@link edu.baylor.GroupFive.ui.utils.buttons.PageButton}.
 *
 * @author Brendon
 */
public class LandingButton extends PageButton {
    Dimension buttonSize = new Dimension(400, 125);

    /**
     * Constructs a LandingButton with the specified path.
     *
     * @param path The path.
     * @author Brendon
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
