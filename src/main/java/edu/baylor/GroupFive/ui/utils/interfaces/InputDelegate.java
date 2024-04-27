package edu.baylor.GroupFive.ui.utils.interfaces;

/**
 * An interface for delegating input events.
 *
 * @author Brendon
 */
public interface InputDelegate {
    /**
     * Switches the page based on the provided option.
     *
     * @param option The option representing the page to switch to.
     * @author Brendon
     */
    public void onPageSwitch(String option);
}
