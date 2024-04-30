package edu.baylor.GroupFive.ui.inventory;

import javax.swing.JPanel;

import edu.baylor.GroupFive.ui.shop.AddShopModel;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

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

    private Page page;
    private HotelTable table;
    private AddInventoryModel model;

    // ProductID, Name, Cost, Stock;


    /**
     * Clears the panel (but does nothing atm).
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
