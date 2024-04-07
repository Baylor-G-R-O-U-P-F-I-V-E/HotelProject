package edu.baylor.GroupFive.ui.createClerk;

import javax.swing.JPanel;

import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

public class CreateClerkAccountPanel extends JPanel implements PagePanel {
    private InputDelegate delegate;

    CreateClerkAccountPanel(InputDelegate delegate) {
        super();
        this.delegate = delegate;

        setVisible(true);
        setOpaque(true);

    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
