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
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import rpggame.Enemy;
import rpggame.Logic;
import rpggame.Player;

public class BattleView extends JPanel {
    private Player player;
    private Enemy enemy;
    
    public BattleView(Player player) {
        setLayout(new BorderLayout());

        this.player = player;
        this.enemy = player.currEnemy;
        
        // Header Label
        JLabel headerLabel = new JLabel("What do you want to do?");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Fight Button
        JButton fightButton = new JButton("Fight");
        fightButton.setAlignmentX(CENTER_ALIGNMENT);
        fightButton.addActionListener(e -> fight());

        // Use Item Button
        JButton useItemButton = new JButton("Use Item");
        useItemButton.setAlignmentX(CENTER_ALIGNMENT);
        useItemButton.addActionListener(e -> useItem());

        // Change Weapon Button
        JButton changeWeaponButton = new JButton("Change Weapon");
        changeWeaponButton.setAlignmentX(CENTER_ALIGNMENT);
        changeWeaponButton.addActionListener(e -> changeWeapon());

        // Run Button
        JButton runButton = new JButton("Run");
        runButton.setAlignmentX(CENTER_ALIGNMENT);
        runButton.addActionListener(e -> run());

        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(fightButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(useItemButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(changeWeaponButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(runButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            // Create a Player object
//            Player player = new Player("test","test");
//
//            // Create a JFrame
//            JFrame frame = new JFrame("Battle View");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            // Create a BattleView panel with the Player object
//            BattleView battleView = new BattleView(player);
//            frame.getContentPane().add(battleView);
//
//            // Pack and display the JFrame
//            frame.pack();
//            frame.setVisible(true);
//        });
//    }

    private void changeWeapon() {
        ArrayList<Weapon> weapons = player.inventory.getWeapons();
        WeaponChooseView wCView = new WeaponChooseView(weapons, player, false);
        Logic.frame.gView.goNext(wCView);
    }

    private void useItem() {
        UseItemView uIView = new UseItemView(player);
        Logic.frame.gView.goNext(uIView);
    }

    private void fight() {
        Logic.fight();
    }

    private void run() {
        Logic.run();
    }
}
