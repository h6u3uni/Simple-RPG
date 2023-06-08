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
import java.awt.BorderLayout;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import rpggame.Logic;
import rpggame.Player;

public class UseItemView extends JPanel{
    
    private Player player;
    private JLabel headerLabel;
    private JLabel subheadingLabel;
    private JPanel newButtonsPanel;
    private ActionListener goBack;
    private ActionListener goBack2;
    
    public UseItemView(Player player){
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

        goBack = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.frame.gView.goBack();
            }
        };

        subheadingLabel.setText("What Item would you like to use?");

        newButtonsPanel = new JPanel();
        newButtonsPanel.setLayout(new BoxLayout(newButtonsPanel, BoxLayout.Y_AXIS));

        JButton useHealItemButton = new JButton("Use Heal Item");
        useHealItemButton.addActionListener(e -> useHealItem());

        JButton useDamageItemButton = new JButton("Use Damage Item");
        useDamageItemButton.addActionListener(e -> useDamageItem());

        JButton goBackButton = new JButton("Go Back");
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

        useItem();
    }
    
    private void useItem(){
        removeAll();
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
                ConfirmView confirmUse = new ConfirmView(player, selectedHeal, player.getInBattle());
                Logic.frame.gView.goNext(confirmUse);
            }
        });
        
        goBackPanel.add(selectButton);
        
        JButton goBackButton = new JButton("Go Back");
        
        goBack2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackButton.removeActionListener(goBack2);
                useItem();
            }
        };
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
                ConfirmView confirmUse = new ConfirmView(player, selectedDmg, player.getInBattle());
                Logic.frame.gView.goNext(confirmUse);
            }
        });
        
        goBackPanel.add(selectButton);
        
        JButton goBackButton = new JButton("Go Back");
        
        goBack2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackButton.removeActionListener(goBack2);
                useItem();
            }
        };
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
}
