package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.Dimension;

import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

/**
 * Button on our dashboard.
 *
 * Extends {@link edu.baylor.GroupFive.ui.utils.buttons.PageButton}.
 *
 * @author Brendon
 */
public class DashboardButton extends PageButton {

    // Creates a default size
    Dimension buttonSize = new Dimension(150, 150);

    /**
     * Constructs a DashboardButton with the specified input delegate, path, and message.
     *
     * @param delegate The input delegate.
     * @param path The path.
     * @param message The message.
     * @author Brendon
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
