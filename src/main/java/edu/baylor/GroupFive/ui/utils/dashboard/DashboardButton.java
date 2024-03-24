package edu.baylor.GroupFive.ui.utils.dashboard;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DashboardButton extends JButton {

    // Creates a default size
    Dimension buttonSize = new Dimension(150, 150);
    
    public DashboardButton(String path) {
        try {
            //Get the image from the path
            BufferedImage image = ImageIO.read(new File(path));
            ImageIcon icon = new ImageIcon(image);

            //Set the icon and size of the button
            setIcon(icon);
            setPreferredSize(buttonSize);
            setMaximumSize(buttonSize);
        
        } catch (Exception e) {
            System.out.println("Error: " + path + " not found");
        }
        
    }
}
