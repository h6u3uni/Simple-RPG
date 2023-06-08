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
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import rpggame.Logic;
import rpggame.Player;
import rpggame.SaveManager;

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
    private Weapon[] shopWeapons;
    private HealItem[] shopHeals;
    private DmgItem[] shopDmgs;
    private JPanel buttonsPanel;
    private JPanel buyOptionsPanel;
    private JPanel sellOptionsPanel;
    private JLabel headerLabel;
    private JLabel subheadingLabel;
    private Player player;
    private ActionListener goBack;
    private ActionListener goBack2;
    
    /*
     * Constructor
     * @param player The player
     * @return none
     */
    public ShopView(Player player) {
        setLayout(new BorderLayout());
        
        this.player = player;
        
        shopWeapons = SaveManager.getShopWeapons();
        shopHeals = SaveManager.getShopHeals();
        shopDmgs = SaveManager.getShopDmgs();
        
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

        // Add action listeners to the buttons
        buyButton.addActionListener(e -> buy());
        sellButton.addActionListener(e -> sell());
        leaveButton.addActionListener(e -> Logic.frame.gView.resetDynamicPanel());
        buyWeaponButton.addActionListener(e -> buyWeapon());
        buyHealingItemsButton.addActionListener(e -> buyHeal());
        buyDamagingItemsButton.addActionListener(e -> buyDmg());
        sellWeaponButton.addActionListener(e -> sellWeapon());
        sellHealingItemsButton.addActionListener(e -> sellHeal());
        sellDamagingItemsButton.addActionListener(e -> sellDmg());
        //goBackButton.addActionListener(new GoBackButtonListener());

        openShop();
    }
    
    // remove all content from the panel
    private void removeAllContent() {
        Component[] components = getComponents();

        for (Component component : components) {
            remove(component);
        }

        revalidate();
        repaint();
    }
    
    // open the shop
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
        removeAllContent(); // Remove the current buttons panel

        // Create a new panel for buy options
        buyOptionsPanel = new JPanel();

        // Create the buy options buttons
        buyOptionsPanel.add(buyWeaponButton);
        buyOptionsPanel.add(buyHealingItemsButton);
        buyOptionsPanel.add(buyDamagingItemsButton);
        
        goBack = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.frame.gView.openShop();
            }
        };
        
        goBackButton.addActionListener(goBack);
        buyOptionsPanel.add(goBackButton);
        buyOptionsPanel.add(Box.createVerticalStrut(100));

        // Add the buy options panel to the center position
        add(buyOptionsPanel, BorderLayout.SOUTH);
        add(subheadingLabel, BorderLayout.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        // Repaint the panel
        revalidate();
        repaint();
    }
    
    // Action listener for the Buy button
    private void sell() {
        removeAllContent(); // Remove the current buttons panel

        // Create a new panel for buy options
        sellOptionsPanel = new JPanel();

        // Create the buy options buttons
        sellOptionsPanel.add(sellWeaponButton);
        sellOptionsPanel.add(sellHealingItemsButton);
        sellOptionsPanel.add(sellDamagingItemsButton);
        
        goBack = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.frame.gView.openShop();
            }
        };
        
        goBackButton.addActionListener(goBack);
        sellOptionsPanel.add(goBackButton);
        sellOptionsPanel.add(Box.createVerticalStrut(100));

        // Add the buy options panel to the center position
        add(sellOptionsPanel, BorderLayout.SOUTH);
        add(subheadingLabel, BorderLayout.CENTER);
        add(headerLabel, BorderLayout.NORTH);

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
        
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent e) -> {
            int selectedIndex = weaponList.getSelectedIndex();
            if (selectedIndex != -1) {
                Weapon selectedWeapon = (Weapon) weaponList.getModel().getElementAt(selectedIndex);
                ConfirmView confirmBuy = new ConfirmView(selectedWeapon, player, false);
                Logic.frame.gView.goNext(confirmBuy);
            }
        });
        
        goBackPanel.add(selectButton);
        goBack2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackButton.removeActionListener(goBack2);
                buy();
            }
        };
        goBackButton.removeActionListener(goBack);
        goBackButton.addActionListener(goBack2);
        
        goBackPanel.add(goBackButton);

        // Add the scroll pane and the Go Back button to the center and south positions, respectively
        add(scrollPane);
        add(goBackPanel, BorderLayout.SOUTH);

        // Repaint the panel
        revalidate();
        repaint();
    }

    // Action listener for the Buy Heal button
    private void buyHeal() {
        remove(buyOptionsPanel); // Remove the current buttons panel
        remove(subheadingLabel);

        // Create the weapon list model
        DefaultListModel<HealItem> listModel = new DefaultListModel<>();
        for (HealItem heal : shopHeals) {
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
                ConfirmView confirmBuy = new ConfirmView(selectedHeal, player, false);
                Logic.frame.gView.goNext(confirmBuy);
            }
        });
        
        goBackPanel.add(selectButton);
        
        goBack2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackButton.removeActionListener(goBack2);
                buy();
            }
        };
        goBackButton.removeActionListener(goBack);
        goBackButton.addActionListener(goBack2);
        
        goBackPanel.add(goBackButton);

        // Add the scroll pane and the Go Back button to the center and south positions, respectively
        add(scrollPane);
        add(goBackPanel, BorderLayout.SOUTH);

        // Repaint the panel
        revalidate();
        repaint();
    }
    
    // Action listener for the Buy Dmg button
    private void buyDmg() {
        remove(buyOptionsPanel); // Remove the current buttons panel
        remove(subheadingLabel);

        // Create the weapon list model
        DefaultListModel<DmgItem> listModel = new DefaultListModel<>();
        for (DmgItem dmg : shopDmgs) {
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
                ConfirmView confirmBuy = new ConfirmView(selectedDmg, player, false);
                Logic.frame.gView.goNext(confirmBuy);
            }
        });
        
        goBackPanel.add(selectButton);
        
        goBack2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackButton.removeActionListener(goBack2);
                buy();
            }
        };
        goBackButton.removeActionListener(goBack);
        goBackButton.addActionListener(goBack2);
        
        goBackPanel.add(goBackButton);

        // Add the scroll pane and the Go Back button to the center and south positions, respectively
        add(scrollPane);
        add(goBackPanel, BorderLayout.SOUTH);

        // Repaint the panel
        revalidate();
        repaint();
    }

    // Action listener for the Sell Weapon button
    private void sellWeapon() {
        removeAll();

        // Create the weapon list model
        DefaultListModel<Weapon> listModel = new DefaultListModel<>();
        ArrayList<Weapon> playerWeapons = Logic.player.inventory.getWeapons();
        for (Weapon weapon : playerWeapons) {
            listModel.addElement(weapon);
        }

        // Create the weapon list
        JList<Weapon> weaponList = new JList<>(listModel);
        weaponList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the scroll pane and add the weapon list to it
        JScrollPane scrollPane = new JScrollPane(weaponList);

        // Create the Go Back button
        JPanel goBackPanel = new JPanel();
        
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent e) -> {
            int selectedIndex = weaponList.getSelectedIndex();
            if (selectedIndex != -1) {
                Weapon selectedWeapon = (Weapon) weaponList.getModel().getElementAt(selectedIndex);
                ConfirmView confirmBuy = new ConfirmView(selectedWeapon, player, true);
                player.inventory.removeItem(selectedWeapon);
                sellWeapon();
                Logic.frame.gView.goNext(confirmBuy);
            }
        });
        
        goBackPanel.add(selectButton);
        
        goBack2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackButton.removeActionListener(goBack2);
                sell();
            }
        };
        goBackButton.removeActionListener(goBack);
        goBackButton.addActionListener(goBack2);
        
        goBackPanel.add(goBackButton);

        // Add the scroll pane and the Go Back button to the center and south positions, respectively
        add(scrollPane, BorderLayout.CENTER);
        add(goBackPanel, BorderLayout.SOUTH);
        add(headerLabel, BorderLayout.NORTH);

        // Repaint the panel
        revalidate();
        repaint();
    }

    // Action listener for the Sell Heal button
    private void sellHeal() {
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
                ConfirmView confirmBuy = new ConfirmView(selectedHeal, player, true);
                player.inventory.removeItem(selectedHeal);
                sellHeal();
                Logic.frame.gView.goNext(confirmBuy);
            }
        });
        
        goBackPanel.add(selectButton);
        
        goBack2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackButton.removeActionListener(goBack2);
                sell();
            }
        };
        goBackButton.removeActionListener(goBack);
        goBackButton.addActionListener(goBack2);
        
        goBackPanel.add(goBackButton);

        // Add the scroll pane and the Go Back button to the center and south positions, respectively
        add(scrollPane, BorderLayout.CENTER);
        add(goBackPanel, BorderLayout.SOUTH);
        add(headerLabel, BorderLayout.NORTH);

        // Repaint the panel
        revalidate();
        repaint();
    }
    
    // Action listener for the Sell Dmg button
    private void sellDmg() {
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
                ConfirmView confirmBuy = new ConfirmView(selectedDmg, player, true);
                player.inventory.removeItem(selectedDmg);
                sellDmg();
                Logic.frame.gView.goNext(confirmBuy);
            }
        });
        
        goBackPanel.add(selectButton);

        goBack2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackButton.removeActionListener(goBack2);
                sell();
            }
        };
        goBackButton.removeActionListener(goBack);
        goBackButton.addActionListener(goBack2);
        
        goBackPanel.add(goBackButton);

        // Add the scroll pane and the Go Back button to the center and south positions, respectively
        add(scrollPane, BorderLayout.CENTER);
        add(goBackPanel, BorderLayout.SOUTH);
        add(headerLabel, BorderLayout.NORTH);

        // Repaint the panel
        revalidate();
        repaint();
    }


}
