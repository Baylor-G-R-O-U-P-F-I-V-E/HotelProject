package edu.baylor.GroupFive.ui.generateBill;

import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

public class GuestBillModel extends HotelModel implements DataModel {

    private Double totalAmount;

    public GuestBillModel(String[] columnNames, Class<?>[] columnClass) {
        super(columnNames, columnClass);

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    public void getData() throws RuntimeException {
        // Get list of purchases from the guests

    }

}
