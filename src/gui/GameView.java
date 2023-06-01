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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameView extends JPanel {

    private JTextArea textArea;

    public GameView() {
        setLayout(new BorderLayout());

        // Create the text area
        textArea = new JTextArea("txt");
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.NORTH);

        // Create the dynamic content panel
        JPanel dynamicContentPanel = new JPanel();
        JTextArea test = new JTextArea("test");
        test.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane test1 = new JScrollPane(test);
        test1.setPreferredSize(new Dimension(600,320));
        dynamicContentPanel.add(test1);
        dynamicContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(dynamicContentPanel, BorderLayout.CENTER);

        // Create the main menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the menu buttons
        JButton exploreButton = new JButton("Explore");
        JButton changeLocationButton = new JButton("Change Location");
        JButton characterInfoButton = new JButton("Character Info");
        JButton inventoryOptionsButton = new JButton("Inventory Options");
        JButton goToShopButton = new JButton("Go to Shop");
        JButton saveGameButton = new JButton("Save Game");
        JButton exitGameButton = new JButton("Exit Game");

        // Add the buttons to the menu panel
        menuPanel.add(exploreButton);
        menuPanel.add(changeLocationButton);
        menuPanel.add(characterInfoButton);
        menuPanel.add(inventoryOptionsButton);
        menuPanel.add(goToShopButton);
        menuPanel.add(saveGameButton);
        menuPanel.add(exitGameButton);

        // Add the menu panel to the right side of the main panel
        add(menuPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        GameView gameView = new GameView();
        frame.getContentPane().add(gameView);

        frame.setVisible(true);
    }
}
