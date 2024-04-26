package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Image;

/**
 *
 */
public class PageButton extends JButton {
    /**
     *
     * @param path
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
     *
     * @param g the <code>Graphics</code> object to protect
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
