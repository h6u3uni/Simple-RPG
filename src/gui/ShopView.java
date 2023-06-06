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
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import rpggame.Logic;
import rpggame.Player;
import rpggame.SaveManager;
import rpggame.Shop;

public class ShopView extends JPanel {

    private JButton buyButton;
    private JButton sellButton;
    private JButton leaveButton;
    private JButton buyWeaponButton;
    private JButton buyHealingItemsButton;
    private JButton buyDamagingItemsButton;
    private JButton sellWeaponButton;
    private JButton sellHealingItemsButton;
    private JButton sellDamagingItemsButton;
    private JButton goBackButton;
    private JButton selectButton;
    private Weapon[] shopWeapons;
    private JPanel buttonsPanel;
    private JPanel buyOptionsPanel;
    private JPanel sellOptionsPanel;
    private JLabel headerLabel;
    private JLabel subheadingLabel;
    private Player player;

    public ShopView(Player player) {
        setLayout(new BorderLayout());
        
        this.player = player;
        
        shopWeapons = SaveManager.getShopWeapons();
        
        // Create the header label
        headerLabel = new JLabel("Welcome to the Shop");
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create the subheading label
        subheadingLabel = new JLabel("What would you like to do?");
        subheadingLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        subheadingLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create the buttons
        buyButton = new JButton("Buy");
        sellButton = new JButton("Sell");
        leaveButton = new JButton("Leave");
        buyWeaponButton = new JButton("Buy Weapon");
        buyHealingItemsButton = new JButton("Buy Healing Items");
        buyDamagingItemsButton = new JButton("Buy Damaging Items");
        sellWeaponButton = new JButton("Sell Weapon");
        sellHealingItemsButton = new JButton("Sell Healing Items");
        sellDamagingItemsButton = new JButton("Sell Damaging Items");
        goBackButton = new JButton("Go Back");
        selectButton = new JButton("Select");

        // Add action listeners to the buttons
        buyButton.addActionListener(e -> buy());
        sellButton.addActionListener(e -> sell());
        //leaveButton.addActionListener(new LeaveButtonListener());
        buyWeaponButton.addActionListener(e -> buyWeapon());
//        buyHealingItemsButton.addActionListener(e -> buyHeal());
//        buyDamagingItemsButton.addActionListener(e -> buyDmg());
//        sellWeaponButton.addActionListener(e -> sellWeapon());
//        sellHealingItemsButton.addActionListener(e -> sellHeal());
//        sellDamagingItemsButton.addActionListener(e -> sellDmg());
        //goBackButton.addActionListener(new GoBackButtonListener());

        openShop();
    }
    
    private void removeAllContent() {
        Component[] components = getComponents();

        for (Component component : components) {
            remove(component);
        }

        revalidate();
        repaint();
    }
    
    private void openShop(){
        removeAllContent();
        
        // Create the buttons panel
        buttonsPanel = new JPanel();
        
        // Add the buttons to the panel
        buttonsPanel.add(buyButton);
        buttonsPanel.add(sellButton);
        buttonsPanel.add(leaveButton);
        buttonsPanel.add(Box.createVerticalStrut(100));

        // Add the buttons panel to the south position
        add(buttonsPanel, BorderLayout.SOUTH);
        add(subheadingLabel, BorderLayout.CENTER);
        add(headerLabel, BorderLayout.NORTH);
        
        // Repaint the panel
        revalidate();
        repaint();
    }
    
    // Action listener for the Buy button
    private void buy() {
        remove(buttonsPanel); // Remove the current buttons panel

        // Create a new panel for buy options
        buyOptionsPanel = new JPanel();

        // Create the buy options buttons
        buyOptionsPanel.add(buyWeaponButton);
        buyOptionsPanel.add(buyHealingItemsButton);
        buyOptionsPanel.add(buyDamagingItemsButton);
        
        goBackButton.addActionListener((ActionEvent e) -> {
            openShop();
        });
        buyOptionsPanel.add(goBackButton);
        buyOptionsPanel.add(Box.createVerticalStrut(100));

        // Add the buy options panel to the center position
        add(buyOptionsPanel, BorderLayout.SOUTH);

        // Repaint the panel
        revalidate();
        repaint();
    }
    
    // Action listener for the Buy button
    private void sell() {
        remove(buttonsPanel); // Remove the current buttons panel

        // Create a new panel for buy options
        sellOptionsPanel = new JPanel();

        // Create the buy options buttons
        sellOptionsPanel.add(sellWeaponButton);
        sellOptionsPanel.add(sellHealingItemsButton);
        sellOptionsPanel.add(sellDamagingItemsButton);
        
        goBackButton.addActionListener((ActionEvent e) -> {
            openShop();
        });
        sellOptionsPanel.add(goBackButton);
        sellOptionsPanel.add(Box.createVerticalStrut(100));

        // Add the buy options panel to the center position
        add(sellOptionsPanel, BorderLayout.SOUTH);

        // Repaint the panel
        revalidate();
        repaint();
    }

    // Action listener for the Buy Weapon button
    private void buyWeapon() {
        remove(buyOptionsPanel); // Remove the current buttons panel
        remove(subheadingLabel);

        // Create the weapon list model
        DefaultListModel<Weapon> listModel = new DefaultListModel<>();
        for (Weapon weapon : shopWeapons) {
            listModel.addElement(weapon);
        }

        // Create the weapon list
        JList<Weapon> weaponList = new JList<>(listModel);
        weaponList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the scroll pane and add the weapon list to it
        JScrollPane scrollPane = new JScrollPane(weaponList);

        // Create the Go Back button
        JPanel goBackPanel = new JPanel();
        
        selectButton.addActionListener((ActionEvent e) -> {
            TODO
        });
        
        goBackPanel.add(selectButton);
        goBackPanel.add(goBackButton);

        // Add the scroll pane and the Go Back button to the center and south positions, respectively
        add(scrollPane);
        add(goBackPanel, BorderLayout.SOUTH);

        // Repaint the panel
        revalidate();
        repaint();
    }


//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Shop View");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 320);
//
//        ShopView shopView = new ShopView();
//        frame.add(shopView);
//
//        frame.setVisible(true);
//    }
}
