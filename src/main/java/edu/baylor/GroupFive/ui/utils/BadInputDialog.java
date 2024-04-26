package edu.baylor.GroupFive.ui.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * The best error dialog you will ever see.
 */
public class BadInputDialog extends JOptionPane {
    /**
     *
     * @param message
     * @param title
     * @throws IOException
     */
    public BadInputDialog(String message, String title) throws IOException {
        BufferedImage badImage = ImageIO.read(new File("src/main/resources/dialog-icons/mad-guy.png"));
        ImageIcon badIcon = new ImageIcon(badImage);

        showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, badIcon);
    }
}
