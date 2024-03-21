package edu.baylor.GroupFive.ui.utils;

import javax.swing.*;

import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

import java.awt.*;

public class Page extends JFrame implements InputDelegate {

    private Dashboard dashboard;
    
    public Page(String privilige) {
        super();
        createFrame();
        dashboard = new Dashboard(this, privilige);
        addComponent(dashboard, 0, 0);
        
    }

    public void createFrame() {
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0xe6f7ff));
        setLayout(new GridBagLayout());
        setVisible(true);
    }

    public void addComponent(Component component, int x, int y) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }

    public void onPageSwitch() {
        setVisible(true);
    }


}
