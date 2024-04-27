package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Image;

/**
 * A button for navigating pages.
 *
 * @author Brendon
 */
public class PageButton extends JButton {
    /**
     * Constructs a PageButton with the specified path.
     *
     * @param path The path to the image for the button.
     */
    public PageButton(String path) {
        try {
            // Get the image from the path
            BufferedImage image = ImageIO.read(new File(path));
            ImageIcon icon = new ImageIcon(image);

            // Set the icon and size of the button
            setIcon(icon);

        } catch (Exception e) {
            System.out.println("Error: " + path + " not found");
        }
    }

    /**
     * Prints the component using the provided Graphics object.
     *
     * @param g The {@code Graphics} object to paint with.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getIcon() != null) {
            Image img = ((ImageIcon)getIcon()).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(img, 0, 0, this);
        }
    }
}
