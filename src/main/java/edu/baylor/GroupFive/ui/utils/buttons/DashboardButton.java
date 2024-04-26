package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.Dimension;

import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

/**
 *
 */
public class DashboardButton extends PageButton {

    // Creates a default size
    Dimension buttonSize = new Dimension(150, 150);

    /**
     *
     * @param delegate
     * @param path
     * @param message
     */
    public DashboardButton(InputDelegate delegate, String path, String message) {
        super(path);

        setPreferredSize(buttonSize);
        setMaximumSize(buttonSize);
        setMinimumSize(buttonSize);

        revalidate();
        repaint();

        addActionListener(actionListener -> {
            delegate.onPageSwitch(message);
        });
    }
}
