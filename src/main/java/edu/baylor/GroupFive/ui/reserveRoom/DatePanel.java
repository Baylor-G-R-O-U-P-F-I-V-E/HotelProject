package edu.baylor.GroupFive.ui.reserveRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.awt.*;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DatePanel extends JPanel {
    DatePanel(String title) {

        // Create the label for the start date
        JLabel dateLabel = new JLabel(title);
        dateLabel.setBounds(200, 250, 200, 50);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Init the date picker for the start date
        UtilDateModel model = new UtilDateModel();
        Calendar cal = Calendar.getInstance();
        model.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        model.setSelected(true);
        Properties i18nStrings = new Properties();
        i18nStrings.put("text.today", "Today");
        i18nStrings.put("text.month", "Month");                    
        i18nStrings.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, i18nStrings);
        
        AbstractFormatter formatter = new AbstractFormatter() {
            private String datePattern = "yyyy-MM-dd";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }
                return "";
            }
        };

        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, formatter);

        add(dateLabel);
        add(datePicker);
    }
}
