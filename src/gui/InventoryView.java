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

import items.DmgItem;
import items.HealItem;
import items.Weapon;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import rpggame.Logic;
import rpggame.Player;

public class InventoryView extends JPanel {

    private Player player;
    private JPanel buttonsPanel;
    private JLabel headerLabel;
    private JLabel subheadingLabel;
    private JButton goBackButton;
    private JPanel newButtonsPanel;
    private ActionListener goBack;
    private ActionListener goBack2;

    public InventoryView(Player player) {
        setLayout(new BorderLayout());

        this.player = player;

        // Create the header label
        headerLabel = new JLabel("Inventory");
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create the subheading label
        subheadingLabel = new JLabel("What would you like to do");
        subheadingLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        subheadingLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create the buttons panel
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        goBack = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.frame.gView.openInventory();
            }
        };
        
        // Create the buttons
        JButton changeWeaponButton = new JButton("Change Weapon");
        changeWeaponButton.addActionListener(e -> changeWeapon());

        JButton useItemButton = new JButton("Use Item");
        useItemButton.addActionListener(e -> useItem());

        JButton checkInventoryButton = new JButton("Check Inventory");
        checkInventoryButton.addActionListener(e -> checkInventory());

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
        inventoryOptions();
        
    }
    
    private void inventoryOptions(){
        removeAll();
        add(buttonsPanel, BorderLayout.SOUTH);
        add(subheadingLabel, BorderLayout.CENTER);
        add(headerLabel, BorderLayout.NORTH);
    }
    
    private void changeWeapon() {
        ArrayList<Weapon> weapons = player.inventory.getWeapons();
        WeaponChooseView wCView = new WeaponChooseView(weapons, player, false);
        Logic.frame.gView.goNext(wCView);
    }

    private void useItem() {
        UseItemView uIView = new UseItemView(player);
        Logic.frame.gView.goNext(uIView);
    }
    

    private void checkInventory() {
        remove(buttonsPanel);
        remove(subheadingLabel);

        String inventory = "";
        if (!player.inventory.getInventory().isEmpty()) {
            inventory = player.inventory.inventoryToString();
        } else {
            inventory = "Inventory contents will be displayed here.";
        }

        JTextArea textArea = new JTextArea(inventory);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 250));

        goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(goBack);

        newButtonsPanel = new JPanel();
        newButtonsPanel.setLayout(new BoxLayout(newButtonsPanel, BoxLayout.Y_AXIS));
        newButtonsPanel.add(Box.createVerticalStrut(10));
        newButtonsPanel.add(goBackButton);
        newButtonsPanel.add(Box.createVerticalStrut(100));
        newButtonsPanel.add(Box.createVerticalGlue());

        goBackButton.setAlignmentX(CENTER_ALIGNMENT);

        add(scrollPane, BorderLayout.CENTER);
        add(newButtonsPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

}
