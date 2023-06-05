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

import items.Weapon;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import rpggame.Logic;
import rpggame.Shop;

public class ShopView extends JPanel {

    private JButton buyButton;
    private JButton sellButton;
    private JButton leaveButton;
    private JButton buyWeaponButton;
    private JButton buyHealingItemsButton;
    private JButton buyDamagingItemsButton;
    private JButton goBackButton;
    private JTextArea textArea;
    private Weapon[] shopWeapons;
    private int userMoney;
    private JPanel buttonsPanel;

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
        buttonsPanel = new JPanel();

        // Create the buttons
        buyButton = new JButton("Buy");
        sellButton = new JButton("Sell");
        leaveButton = new JButton("Leave");
        buyWeaponButton = new JButton("Buy Weapon");
        buyHealingItemsButton = new JButton("Buy Healing Items");
        buyDamagingItemsButton = new JButton("Buy Damaging Items");
        goBackButton = new JButton("Go Back");

        // Add action listeners to the buttons
        buyButton.addActionListener(new BuyButtonListener());
        sellButton.addActionListener(new SellButtonListener());
        leaveButton.addActionListener(new LeaveButtonListener());
        buyWeaponButton.addActionListener(new BuyWeaponButtonListener());
        buyHealingItemsButton.addActionListener(new BuyHealingItemsButtonListener());
        buyDamagingItemsButton.addActionListener(new BuyDamagingItemsButtonListener());
        goBackButton.addActionListener(new GoBackButtonListener());

        // Add the buttons to the panel
        buttonsPanel.add(buyButton);
        buttonsPanel.add(sellButton);
        buttonsPanel.add(leaveButton);
        buttonsPanel.add(Box.createVerticalStrut(100));

        // Add the buttons panel to the south position
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    // Action listener for the Buy button
    private class BuyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            remove(buttonsPanel); // Remove the current buttons panel

            // Create a new panel for buy options
            JPanel buyOptionsPanel = new JPanel();

            // Create the buy options buttons
            buyOptionsPanel.add(buyWeaponButton);
            buyOptionsPanel.add(buyHealingItemsButton);
            buyOptionsPanel.add(buyDamagingItemsButton);
            buyOptionsPanel.add(Box.createVerticalStrut(100));

            // Add the buy options panel to the center position
            add(buyOptionsPanel, BorderLayout.SOUTH);

            // Repaint the panel
            revalidate();
            repaint();
        }
    }

    // Action listener for the Buy Weapon button
    private class BuyWeaponButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            remove(buttonsPanel); // Remove the current buttons panel

            // Create a scroll pane for the weapon list
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            // Create a text area to display the weapon list
            textArea = new JTextArea(10, 20);
            textArea.setEditable(false);
            scrollPane.setViewportView(textArea);

            // Populate the text area with the weapon list
            for (Weapon weapon : shopWeapons) {
                textArea.append(weapon.getName() + " - Price: $" + weapon.getPrice() + "\n");
            }

            // Create the Go Back button
            JPanel goBackPanel = new JPanel();
            goBackPanel.add(goBackButton);

            // Add the scroll pane and the Go Back button to the center and south positions, respectively
            add(scrollPane, BorderLayout.CENTER);
            add(goBackPanel, BorderLayout.SOUTH);

            // Repaint the panel
            revalidate();
            repaint();
        }
    }

    // Action listener for the Go Back button
    private class GoBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            remove(textArea); // Remove the weapon list text area
            remove(goBackButton); // Remove the Go Back button

            // Restore the buttons panel
            add(buttonsPanel, BorderLayout.SOUTH);

            // Repaint the panel
            revalidate();
            repaint();
        }
    }

    // Action listener for the Sell button
    private class SellButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add your sell functionality here
        }
    }

    // Action listener for the Leave button
    private class LeaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add your leave functionality here
        }
    }

    // Action listener for the Buy Healing Items button
    private class BuyHealingItemsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add your buy healing items functionality here
        }
    }

    // Action listener for the Buy Damaging Items button
    private class BuyDamagingItemsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Add your buy damaging items functionality here
        }
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
