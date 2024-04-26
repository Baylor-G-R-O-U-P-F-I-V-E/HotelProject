package edu.baylor.GroupFive.ui.utils.buttons.landingButtons;

import edu.baylor.GroupFive.ui.utils.buttons.LandingButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

/**
 *
 */
public class CreateAccountButton extends LandingButton {

    /**
     *
     * @param delegate
     * @param path
     */
    public CreateAccountButton(InputDelegate delegate, String path) {
        super(path);

        addActionListener(actionListener -> {
            delegate.onPageSwitch("createAccount");
        });
    }
}
