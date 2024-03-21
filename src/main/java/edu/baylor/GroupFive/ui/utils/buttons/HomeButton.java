package edu.baylor.GroupFive.ui.utils.buttons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import edu.baylor.GroupFive.ui.utils.interfaces.DashboardButton;
import javafx.scene.image.Image;

public class HomeButton extends JButton implements DashboardButton {
    HomeButton() {
        try {
            ImageIcon homeIcon = getIcon();
            setIcon(homeIcon);
            setPreferredSize(buttonSize);
            setMaximumSize(buttonSize);
        } catch (Exception e) {
            System.out.println("Error: home-icon.png not found");
        }
        
    }

    public ImageIcon getIcon() {
        try {
            BufferedImage homeImage = ImageIO.read(new File("src/main/resources/button-icons/home-icon.png"));
            ImageIcon homeIcon = new ImageIcon(homeImage);
            JButton homeButton = new JButton(homeIcon);
            return homeIcon;
        } catch (IOException e) {
            System.out.println("Error: home-icon.png not found");
            return null;
        }
    }
}
