package edu.baylor.GroupFive.ui.utils.buttons.landingButtons;

import edu.baylor.GroupFive.ui.utils.buttons.LandingButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

/**
 * Button for logging in.
 *
 * Extends {@link edu.baylor.GroupFive.ui.utils.buttons.LandingButton}.
 *
 * @author Brendon
 */
public class LoginButton extends LandingButton {
    /**
     * Constructs a LoginButton with the specified input delegate and path.
     *
     * @param delegate The input delegate.
     * @param path The path.
     * @author Brendon
     */
    public LoginButton(InputDelegate delegate, String path) {
        super(path);

        addActionListener(actionListener -> {
            delegate.onPageSwitch("login");
        });
    }

}
