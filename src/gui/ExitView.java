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
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rpggame.Logic;

public class ExitView extends JPanel {

    private JLabel headerLabel;

    /*
     * Constructor
     * @param none
     * @return none
     */
    public ExitView() {
        setLayout(new BorderLayout());

        // Create the header label
        headerLabel = new JLabel("Do you want to save before quitting?");
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        // Create the buttons panel
        JPanel buttonsPanel = new JPanel();

        // Create the buttons
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        // Add action listeners to the buttons
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform save operation
                saveAndQuit();
            }
        });

        // Add action listeners to the buttons
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quit without saving
                quitWithoutSaving();
            }
        });

        // Add the buttons to the panel
        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);

        // Add the buttons panel to the center position
        add(buttonsPanel, BorderLayout.CENTER);
    }

    // Save and quit
    private void saveAndQuit() {
        Logic.exit(true);
    }

    // Quit without saving
    private void quitWithoutSaving() {
        Logic.exit(false);
    }

}
