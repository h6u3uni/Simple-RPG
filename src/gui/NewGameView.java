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

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import rpggame.Logic;

public class NewGameView extends JPanel {
    JTextField nameField;
    JRadioButton maleRadioButton;
    JRadioButton femaleRadioButton;
    
    public NewGameView() {
        setLayout(new GridBagLayout());

        // Create the header label
        JLabel headerLabel = new JLabel("New Game");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints gbcHeaderLabel = new GridBagConstraints();
        gbcHeaderLabel.gridx = 0;
        gbcHeaderLabel.gridy = 0;
        gbcHeaderLabel.gridwidth = 3;
        gbcHeaderLabel.anchor = GridBagConstraints.CENTER;
        gbcHeaderLabel.insets = new Insets(10, 10, 0, 10); // Add some padding
        add(headerLabel, gbcHeaderLabel);

        // Create the label for name input
        JLabel nameLabel = new JLabel("Enter your Name:");
        GridBagConstraints gbcNameLabel = new GridBagConstraints();
        gbcNameLabel.gridx = 0;
        gbcNameLabel.gridy = 1;
        gbcNameLabel.anchor = GridBagConstraints.WEST;
        gbcNameLabel.insets = new Insets(10, 10, 0, 10); // Add some padding
        add(nameLabel, gbcNameLabel);

        // Create the text field for name input
        nameField = new JTextField(20);
        GridBagConstraints gbcNameField = new GridBagConstraints();
        gbcNameField.gridx = 1;
        gbcNameField.gridy = 1;
        gbcNameField.gridwidth = 2;
        gbcNameField.fill = GridBagConstraints.HORIZONTAL;
        gbcNameField.insets = new Insets(10, 0, 0, 10); // Add some padding
        add(nameField, gbcNameField);

        // Create the label for gender selection
        JLabel genderLabel = new JLabel("Select your Gender:");
        GridBagConstraints gbcGenderLabel = new GridBagConstraints();
        gbcGenderLabel.gridx = 0;
        gbcGenderLabel.gridy = 2;
        gbcGenderLabel.anchor = GridBagConstraints.WEST;
        gbcGenderLabel.insets = new Insets(10, 10, 0, 10); // Add some padding
        add(genderLabel, gbcGenderLabel);

        // Create the radio buttons for gender selection
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setSelected(true);
        femaleRadioButton = new JRadioButton("Female");

        // Create a button group to ensure only one radio button can be selected
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        GridBagConstraints gbcMaleRadioButton = new GridBagConstraints();
        gbcMaleRadioButton.gridx = 1;
        gbcMaleRadioButton.gridy = 2;
        gbcMaleRadioButton.anchor = GridBagConstraints.WEST;
        gbcMaleRadioButton.insets = new Insets(10, 0, 0, 10); // Add some padding
        add(maleRadioButton, gbcMaleRadioButton);

        GridBagConstraints gbcFemaleRadioButton = new GridBagConstraints();
        gbcFemaleRadioButton.gridx = 2;
        gbcFemaleRadioButton.gridy = 2;
        gbcFemaleRadioButton.anchor = GridBagConstraints.WEST;
        gbcFemaleRadioButton.insets = new Insets(10, 0, 0, 10); // Add some padding
        add(femaleRadioButton, gbcFemaleRadioButton);

        // Create the confirmation button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> confirm());
        
        GridBagConstraints gbcConfirmButton = new GridBagConstraints();
        gbcConfirmButton.gridx = 0;
        gbcConfirmButton.gridy = 3;
        gbcConfirmButton.gridwidth = 3;
        gbcConfirmButton.insets = new Insets(10, 10, 0, 10); // Add some padding
        add(confirmButton, gbcConfirmButton);
    }
    
//    public static void main(String[] args) {
//        // Create an instance of the NewGameView
//        NewGameView newGameView = new NewGameView();
//
//        // Create a JFrame to display the NewGameView
//        JFrame frame = new JFrame("New Game");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(newGameView);
//        frame.setPreferredSize(new Dimension(400, 300)); // Adjust the size as needed
//        frame.pack();
//        frame.setVisible(true);
//    }

    private void confirm() {
        if(!(nameField.getText().equals(""))){
            if (maleRadioButton.isSelected()) {
                Logic.makeNewPlayer(nameField.getText(), "Male");
            } else if (femaleRadioButton.isSelected()) {
                Logic.makeNewPlayer(nameField.getText(), "Female");
            }
//            System.out.println(Logic.player.name);
        }
        else{
            System.out.println("empty");
        }
        
    }
}
