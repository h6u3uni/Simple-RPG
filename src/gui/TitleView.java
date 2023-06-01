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
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

public class TitleView extends JPanel {

    public TitleView() {
        setLayout(new BorderLayout());

        // Create the title label
        JLabel titleLabel = new JLabel("Simple RPG");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add top padding above the title header
        int topPadding = 20; // Adjust the value as needed
        int bottomPadding = 20; // Adjust the value as needed
        titleLabel.setBorder(new EmptyBorder(topPadding, 0, bottomPadding, 0));

        add(titleLabel, BorderLayout.NORTH);

        // Create the buttons
        JButton newGameButton = new JButton("New Game");
        JButton continueGameButton = new JButton("Continue Game");
        JButton quitButton = new JButton("Quit");

        // Create a vertical box to hold the buttons
        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonBox.add(newGameButton);
        buttonBox.add(Box.createVerticalStrut(10)); // Add spacing between buttons
        continueGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonBox.add(continueGameButton);
        buttonBox.add(Box.createVerticalStrut(10)); // Add spacing between buttons
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonBox.add(quitButton);

        // Create a JPanel to hold the button box
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonBox);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        TitleView titleView = new TitleView();

        // Create a JFrame to test the TitleView
        javax.swing.JFrame frame = new javax.swing.JFrame("Title View Test");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(titleView);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
