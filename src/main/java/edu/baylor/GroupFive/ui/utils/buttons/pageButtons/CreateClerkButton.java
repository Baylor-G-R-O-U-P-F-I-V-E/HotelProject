package edu.baylor.GroupFive.ui.utils.buttons.pageButtons;

import edu.baylor.GroupFive.ui.utils.buttons.DashboardButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

public class CreateClerkButton extends DashboardButton {
    public CreateClerkButton(InputDelegate page, String path) {
        super(path);
        addActionListener(e -> {
            page.onPageSwitch("create-clerk");
        });
    }

}
