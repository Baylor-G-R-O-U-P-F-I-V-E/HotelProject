package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

/**
 *
 */
public class PanelButton extends JButton {

    /**
     *
     * @param text
     */
    public PanelButton(String text) {
        this(text, 350, 50);
    }

    /**
     *
     * @param text
     * @param width
     * @param height
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
