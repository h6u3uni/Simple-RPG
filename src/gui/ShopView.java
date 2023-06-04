/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author haruk
 */

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShopView extends JPanel {

    public ShopView() {
        setLayout(new BorderLayout());

        // Create the header label
        JLabel headerLabel = new JLabel("Welcome to the Shop");
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        // Create the subheading label
        JLabel subheadingLabel = new JLabel("What would you like to do?");
        subheadingLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        subheadingLabel.setHorizontalAlignment(JLabel.CENTER);
        add(subheadingLabel, BorderLayout.CENTER);

        // Create the buttons panel
        JPanel buttonsPanel = new JPanel();

        // Create the buttons
        JButton buyButton = new JButton("Buy");
        JButton sellButton = new JButton("Sell");
        JButton leaveButton = new JButton("Leave");

        // Add the buttons to the panel
        buttonsPanel.add(buyButton);
        buttonsPanel.add(sellButton);
        buttonsPanel.add(leaveButton);
        buttonsPanel.add(Box.createVerticalStrut(100));

        // Add the buttons panel to the south position
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Shop View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 320);

        ShopView shopView = new ShopView();
        frame.add(shopView);

        frame.setVisible(true);
    }
}

