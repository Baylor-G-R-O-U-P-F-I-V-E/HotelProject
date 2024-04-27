package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

/**
 * A button for use in panels.
 *
 * @author Brendon
 */
public class PanelButton extends JButton {

    /**
     * Constructs a PanelButton with the specified text.
     *
     * @param text The text to display on the button.
     */
    public PanelButton(String text) {
        this(text, 350, 50);
    }

    /**
     * Constructs a PanelButton with the specified text, width, and height.
     *
     * @param text The text to display on the button.
     * @param width The width of the button.
     * @param height The height of the button.
     */
    public PanelButton(String text, int width, int height) {
        super(text);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(width, height));
        setFont(new Font("Arial", Font.PLAIN, 22));
        setOpaque(true);
        setBorderPainted(false);
        setBackground(new Color(0, 0, 153));
        setForeground(new Color(255, 255, 255));
    }

}
