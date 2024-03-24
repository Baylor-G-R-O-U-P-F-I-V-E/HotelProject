package edu.baylor.GroupFive.ui.login;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

public class LoginPanel extends JPanel implements PagePanel {
    public LoginPanel() {
        super();
        // Set the layout of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
    }

    @Override
    public void clear() {

    }
}
