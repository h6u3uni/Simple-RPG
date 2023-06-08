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

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import rpggame.Logic;
import rpggame.SaveManager;

public class DeadView extends JPanel {

    /*
     * Constructor
     * Creates the DeadView panel
     */
    public DeadView() {
        setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("You have died");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the text
        add(headerLabel, BorderLayout.NORTH);

        // Subheading Label
        JLabel subheadingLabel;
        // If the player is dead and there is a save file, ask if they want to go back to the previous save
        if (!Logic.newPlay && SaveManager.saveExist()) {
            subheadingLabel = new JLabel("Do you want to go back to the previous save?");
        } else {
            subheadingLabel = new JLabel("Thanks for playing.");
        }
        subheadingLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the text
        add(subheadingLabel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        JButton okButton = new JButton("Ok");

        // Add the buttons to the panel
        if (!Logic.newPlay && SaveManager.saveExist()) {
            buttonPanel.add(yesButton);
            buttonPanel.add(noButton);
        } else {
            buttonPanel.add(okButton);
        }

        add(buttonPanel, BorderLayout.SOUTH);

        // If the player chooses to go back to the previous save, load the save
        yesButton.addActionListener(e -> {
            Logic.playerSelected(Logic.originPlayer);
        });

        // If the player chooses not to go back to the previous save, exit the game
        noButton.addActionListener(e -> {
            buttonPanel.removeAll();
            subheadingLabel.setText("Thanks for playing.");
            buttonPanel.add(okButton);
            revalidate();
            repaint();
        });

        okButton.addActionListener(e -> {
            Logic.exit(false);
        });
    }

}

