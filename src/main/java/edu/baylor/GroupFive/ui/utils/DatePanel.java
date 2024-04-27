package edu.baylor.GroupFive.ui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.awt.*;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

/**
 * A panel for selecting dates, extending JPanel.
 *
 * @author Brendon
 */
public class DatePanel extends JPanel implements PagePanel {
    //Calendar to store the displayed date
    private Calendar cal;
    private UtilDateModel model;

    /**
     * Constructs a DatePanel with the specified title.
     *
     * @param title The title for the date panel.
     */
    public DatePanel(String title) {
        this(title, 0);
    }

    /**
     * Constructs a DatePanel with the specified title and number of days ahead.
     *
     * @param title The title for the date panel.
     * @param daysAhead The number of days ahead.
     */
    public DatePanel(String title, int daysAhead) {

        // Create the label for the start date
        if (title == null) {
            title = "Start Date:";
        }

        // Style label
        JLabel dateLabel = new JLabel(title);
        dateLabel.setBounds(200, 250, 200, 50);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Add the label to the panel    
        add(dateLabel);

        addComponents(daysAhead);
    }

    /**
     * Returns the formatter for date values.
     *
     * @return The formatter for date values.
     */
    public AbstractFormatter getFormatter() {
        return new AbstractFormatter() {
            private String datePattern = "MM/dd/yyyy";
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
    }

    /**
     * Creates the date model for the date picker.
     *
     * @param daysAhead The number of days ahead.
     * @return The UtilDateModel for the date picker.
     */
    public UtilDateModel createModel(int daysAhead) {
        //Create Model for the date picker
        model = new UtilDateModel();

        //Set the date to the current date
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, daysAhead);

        //Set the date to the model
        model.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        model.setSelected(true);
        return model;
    }

    /**
     * Returns the internationalization strings for the date picker.
     *
     * @return The internationalization strings.
     */
    public Properties getI18nStrings() {
        Properties i18nStrings = new Properties();
        i18nStrings.put("text.today", "Today");
        i18nStrings.put("text.month", "Month");
        i18nStrings.put("text.year", "Year");
        return i18nStrings;
    }

    /**
     * Adds the componenets to the date panel.
     *
     * @param daysAhead The number of days ahead.
     */
    private void addComponents(int daysAhead) {

        UtilDateModel model = createModel(daysAhead);
        Properties i18nStrings = getI18nStrings();

        JDatePanelImpl datePanel = new JDatePanelImpl(model, i18nStrings);
        
        AbstractFormatter formatter = getFormatter();

        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, formatter);

        add(datePicker);

        
    }

    /**
     * Returns the selected date.
     *
     * @return The selected date.
     */
    public Date getDate() {
        return (Date) model.getValue();
    }

    /**
     * Sets the date forr the date panel.
     *
     * @param date The date to set.
     */
    public void setDate(Date date) {
        cal.setTime(date);
        model.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        model.setSelected(true);
    }

    /**
     * Clears the components in the date panle.
     */
    @Override
    public void clear() {
        //Clear all components
        for (Component component : getComponents()) {
            if (component instanceof JDatePickerImpl) {
                ((JDatePickerImpl) component).getModel().setValue(null);
            }
        }
    }
}
