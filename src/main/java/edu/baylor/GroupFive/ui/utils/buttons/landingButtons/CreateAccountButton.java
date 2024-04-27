package edu.baylor.GroupFive.ui.utils.buttons.landingButtons;

import edu.baylor.GroupFive.ui.utils.buttons.LandingButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

/**
 * Button for creating an account.
 *
 * Extends {@link edu.baylor.GroupFive.ui.utils.buttons.LandingButton}.
 *
 * @author Brendon
 */
public class CreateAccountButton extends LandingButton {

    /**
     * Constructs a CreateAccountButton with the specified input delegate and path.
     *
     * @param delegate The input delegate.
     * @param path The path.
     * @author Brendon
     */
    public CreateAccountButton(InputDelegate delegate, String path) {
        super(path);

        addActionListener(actionListener -> {
            delegate.onPageSwitch("createAccount");
        });
    }
}
