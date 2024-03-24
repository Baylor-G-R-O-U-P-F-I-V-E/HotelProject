package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {
    public Button(String path) {
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
}
