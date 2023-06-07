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
        subheadingLabel.setText("What Item would you like to use?");
        removeAll();
        
        newButtonsPanel = new JPanel();
        newButtonsPanel.setLayout(new BoxLayout(newButtonsPanel, BoxLayout.Y_AXIS));
        
        JButton useHealItemButton = new JButton("Use Heal Item");
        useHealItemButton.addActionListener(e -> useHealItem());
        
        JButton useDamageItemButton = new JButton("Use Damage Item");
        useDamageItemButton.addActionListener(e -> useDamageItem());
        
        goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(goBack);
        
        newButtonsPanel.add(Box.createVerticalStrut(10));
        newButtonsPanel.add(useHealItemButton);
        newButtonsPanel.add(Box.createVerticalStrut(10));
        newButtonsPanel.add(useDamageItemButton);
        newButtonsPanel.add(Box.createVerticalStrut(10));
        newButtonsPanel.add(goBackButton);
        newButtonsPanel.add(Box.createVerticalStrut(100));
        newButtonsPanel.add(Box.createVerticalGlue());
        
        useHealItemButton.setAlignmentX(CENTER_ALIGNMENT);
        useDamageItemButton.setAlignmentX(CENTER_ALIGNMENT);
        goBackButton.setAlignmentX(CENTER_ALIGNMENT);
        
        add(newButtonsPanel, BorderLayout.SOUTH);
        add(subheadingLabel, BorderLayout.CENTER);
        add(headerLabel, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
    
    private void useHealItem() {
        subheadingLabel.setText("Which Healing Item would you like to use?");
        removeAll();

        // Create the weapon list model
        ArrayList<HealItem> playerHeals = Logic.player.inventory.getHealItems();
        DefaultListModel<HealItem> listModel = new DefaultListModel<>();
        for (HealItem heal : playerHeals) {
            listModel.addElement(heal);
        }

        // Create the weapon list
        JList<HealItem> healsList = new JList<>(listModel);
        healsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the scroll pane and add the weapon list to it
        JScrollPane scrollPane = new JScrollPane(healsList);

        // Create the Go Back button
        JPanel goBackPanel = new JPanel();
        
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent e) -> {
            int selectedIndex = healsList.getSelectedIndex();
            if (selectedIndex != -1) {
                HealItem selectedHeal = (HealItem) healsList.getModel().getElementAt(selectedIndex);
                ConfirmView confirmUse = new ConfirmView(player, selectedHeal, false);
                Logic.frame.gView.goNext(confirmUse);
            }
        });
        
        goBackPanel.add(selectButton);
        
        goBack2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackButton.removeActionListener(goBack2);
                useItem();
            }
        };
        goBackButton.removeActionListener(goBack);
        goBackButton.addActionListener(goBack2);
        
        goBackPanel.add(goBackButton);

        // Add the scroll pane and the Go Back button to the center and south positions, respectively
        add(scrollPane, BorderLayout.CENTER);
        add(goBackPanel, BorderLayout.SOUTH);
        add(subheadingLabel, BorderLayout.NORTH);
        //add(headerLabel, BorderLayout.NORTH);

        // Repaint the panel
        revalidate();
        repaint();
    }
    
    private void useDamageItem() {
        subheadingLabel.setText("Which Healing Item would you like to use?");
        removeAll();

        // Create the weapon list model
        ArrayList<DmgItem> playerDmgs = Logic.player.inventory.getDmgItems();
        DefaultListModel<DmgItem> listModel = new DefaultListModel<>();
        for (DmgItem dmg : playerDmgs) {
            listModel.addElement(dmg);
        }

        // Create the weapon list
        JList<DmgItem> dmgsList = new JList<>(listModel);
        dmgsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the scroll pane and add the weapon list to it
        JScrollPane scrollPane = new JScrollPane(dmgsList);

        // Create the Go Back button
        JPanel goBackPanel = new JPanel();
        
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent e) -> {
            int selectedIndex = dmgsList.getSelectedIndex();
            if (selectedIndex != -1) {
                DmgItem selectedDmg = (DmgItem) dmgsList.getModel().getElementAt(selectedIndex);
                ConfirmView confirmUse = new ConfirmView(player, selectedDmg, false);
                Logic.frame.gView.goNext(confirmUse);
            }
        });
        
        goBackPanel.add(selectButton);
        
        goBack2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackButton.removeActionListener(goBack2);
                useItem();
            }
        };
        goBackButton.removeActionListener(goBack);
        goBackButton.addActionListener(goBack2);
        
        goBackPanel.add(goBackButton);

        // Add the scroll pane and the Go Back button to the center and south positions, respectively
        add(scrollPane, BorderLayout.CENTER);
        add(goBackPanel, BorderLayout.SOUTH);
        add(subheadingLabel, BorderLayout.NORTH);
        //add(headerLabel, BorderLayout.NORTH);

        // Repaint the panel
        revalidate();
        repaint();
    }

    private void checkInventory() {
        remove(buttonsPanel);
        remove(subheadingLabel);
        
        String inventory = "";
        if(!player.inventory.getInventory().isEmpty()){
            inventory = player.inventory.inventoryToString();
        }
        else {
            inventory = "Inventory contents will be displayed here.";
        }
        JTextArea textArea = new JTextArea(inventory);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(goBack);

        newButtonsPanel = new JPanel();
        newButtonsPanel.setLayout(new BoxLayout(newButtonsPanel, BoxLayout.Y_AXIS));
        newButtonsPanel.add(textArea);
        newButtonsPanel.add(Box.createVerticalStrut(10));
        newButtonsPanel.add(goBackButton);
        newButtonsPanel.add(Box.createVerticalStrut(100));
        newButtonsPanel.add(Box.createVerticalGlue());

        goBackButton.setAlignmentX(CENTER_ALIGNMENT);

        add(newButtonsPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }
}
