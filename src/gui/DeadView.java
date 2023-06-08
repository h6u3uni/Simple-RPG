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
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import rpggame.Logic;
import rpggame.SaveManager;

public class DeadView extends JPanel {

    public DeadView() {
        setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("You have died");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the text
        add(headerLabel, BorderLayout.NORTH);

        // Subheading Label
        JLabel subheadingLabel;
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

        if (!Logic.newPlay && SaveManager.saveExist()) {
            buttonPanel.add(yesButton);
            buttonPanel.add(noButton);
        } else {
            buttonPanel.add(okButton);
        }

        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        yesButton.addActionListener(e -> {
            Logic.playerSelected(Logic.player);
        });

        noButton.addActionListener(e -> {
            buttonPanel.removeAll();
            subheadingLabel.setText("Thanks for playing.");
            buttonPanel.add(new JButton("Ok"));
            revalidate();
            repaint();
        });

        okButton.addActionListener(e -> {
            Logic.exit(false);
        });
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            Logic.newPlay = false; // Set newPlay flag accordingly
//            //Logic.saveExists = true; // Set saveExists flag accordingly
//            SaveManager sMan = new SaveManager();
//            JFrame frame = new JFrame("Game Over");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            DeadView deadView = new DeadView();
//            frame.getContentPane().add(deadView);
//
//            frame.pack();
//            frame.setVisible(true);
//        });
//    }
}

