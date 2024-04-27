package edu.baylor.GroupFive.ui.utils.interfaces;

/**
 * A data model interface for retrieving data.
 *
 * @author Brendon
 */
public interface DataModel {
    /**
     * Retrieves data.
     *
     * @throws RuntimeException If an error occurs while retrieving data.
     */
    public void getData() throws RuntimeException;
}
