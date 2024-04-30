package edu.baylor.GroupFive.ui.inventory;

import javax.swing.JPanel;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

/**
 * Panel for the shop.
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Siri
 */
public class InventoryPanel extends JPanel implements PagePanel {

    /**
     * Constructs a new ShopPanel with the specified delegate
     *
     * // @param delegate Page
     */
    public InventoryPanel() {
        super();
    }

    /**
     * Clears the panel (but does nothing atm).
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
