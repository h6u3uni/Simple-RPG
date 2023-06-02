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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InventoryView extends JPanel {

    public InventoryView() {
        setLayout(new BorderLayout());

        // Create the header label
        JLabel headerLabel = new JLabel("Inventory");
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        // Create the subheading label
        JLabel subheadingLabel = new JLabel("What would you like to do");
        subheadingLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        subheadingLabel.setHorizontalAlignment(JLabel.CENTER);
        add(subheadingLabel, BorderLayout.CENTER);

        // Create the buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        // Create the buttons
        JButton changeWeaponButton = new JButton("Change Weapon");
        JButton useItemButton = new JButton("Use Item");
        JButton checkInventoryButton = new JButton("Check Inventory");

        // Add vertical spacing
        buttonsPanel.add(Box.createVerticalStrut(10));

        // Add the buttons to the panel
        buttonsPanel.add(changeWeaponButton);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(useItemButton);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(checkInventoryButton);
        buttonsPanel.add(Box.createVerticalStrut(100));
        // Add vertical spacing
        buttonsPanel.add(Box.createVerticalGlue());

        // Center align the buttons
        changeWeaponButton.setAlignmentX(CENTER_ALIGNMENT);
        useItemButton.setAlignmentX(CENTER_ALIGNMENT);
        checkInventoryButton.setAlignmentX(CENTER_ALIGNMENT);

        // Add the buttons panel to the south position
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 320);

        InventoryView inventoryView = new InventoryView();
        frame.getContentPane().add(inventoryView);

        frame.setVisible(true);
    }
}
