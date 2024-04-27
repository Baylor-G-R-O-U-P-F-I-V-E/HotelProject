package edu.baylor.GroupFive.ui.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * The best error dialog you will ever see.
 *
 * He is the bad man.
 *
 * @author TheBadMan has no author. He has persisted since time immemorial.
 */
public class BadInputDialog extends JOptionPane {
    /**
     * The Bad Man Appears.
     *
     * @param message The Bad Man Speaks.
     * @param title Name By Which The Bad Man Is Known.
     * @throws IOException If The Bad Man's Summon Is Negated.
     */
    public BadInputDialog(String message, String title) throws IOException {
        BufferedImage badImage = ImageIO.read(new File("src/main/resources/dialog-icons/mad-guy.png"));
        ImageIcon badIcon = new ImageIcon(badImage);

        showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, badIcon);
    }
}
