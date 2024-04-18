package edu.baylor.GroupFive.ui.homePanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

public class HomePanel extends JPanel implements PagePanel {

    /*
     * What it needs:
     * 
     * - A title label
     * - Cart display
     * - A list of products
     * - A list of reservations
     */

    private Page page;
    private String username;

    public HomePanel(Page page, String username) {
        super();

        this.page = page;
        this.username = username;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setVisible(true);
    
        setOpaque(true);
    
        setBackground(page.getContentPane().getBackground());
    
        JLabel titleLabel = new JLabel("Home");
        titleLabel.setAlignmentX(Box.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
    
        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createVerticalGlue());
    
        JPanel textPanel = new JPanel();
        // Add components to textPanel as needed
    
        add(textPanel);
        add(Box.createVerticalGlue());
    }

    public void addCartDisplay() {

    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
