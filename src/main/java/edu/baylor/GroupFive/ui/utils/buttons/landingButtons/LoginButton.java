package edu.baylor.GroupFive.ui.utils.buttons.landingButtons;

import edu.baylor.GroupFive.ui.utils.buttons.LandingButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

/**
 *
 */
public class LoginButton extends LandingButton {
    /**
     *
     * @param delegate
     * @param path
     */
    public LoginButton(InputDelegate delegate, String path) {
        super(path);

        addActionListener(actionListener -> {
            delegate.onPageSwitch("login");
        });
    }

}
