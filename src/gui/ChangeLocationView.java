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
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import rpggame.Logic;

public class ChangeLocationView extends JPanel {
    
    public ChangeLocationView(int act) {
        setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Where would you like to go?");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        // List of Locations
        String[] locations = getLocationList(act);
        
        JList<String> locationList = new JList<>(locations);
        locationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(locationList);
        add(scrollPane, BorderLayout.CENTER);

        // Select Button
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent e) -> {
            int placeId = locationList.getSelectedIndex();
            Logic.changeLocation(placeId);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(selectButton);
        buttonPanel.add(Box.createHorizontalGlue());
        add(buttonPanel, BorderLayout.SOUTH);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Change Location");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            ChangeLocationView changeLocationView = new ChangeLocationView(2);
//            frame.getContentPane().add(changeLocationView);
//
//            frame.pack();
//            frame.setVisible(true);
//        });
//    }

    private String[] getLocationList(int act) {
        if(act == 1){
            String[] locations = {"(1) Elven City","(2) Goblin Forest"};
            return locations;
        }
        else if(act == 2){
            String[] locations = {"(1) Elven City","(2) Goblin Forest", "(3) Ancient Tomb"};
            return locations;
        }
        else if(act == 3){
            String[] locations = {"(1) Elven City","(2) Goblin Forest", "(3) Ancient Tomb", "(4) Minotaur Labyrinth"};
            return locations;
        }
        else if(act == 4){
            String[] locations = {"(1) Elven City","(2) Goblin Forest", "(3) Ancient Tomb", "(4) Minotaur Labyrinth", "(5) Death Mountain"};
            return locations;
        }
        else if(act == 5){
            String[] locations = {"(1) Elven City","(2) Goblin Forest", "(3) Ancient Tomb", "(4) Minotaur Labyrinth", "(5) Death Mountain", "(6) Demon Castle"};
            return locations;
        }
        else if(act == 6){
            String[] locations = {"(1) Elven City","(2) Goblin Forest", "(3) Ancient Tomb", "(4) Minotaur Labyrinth", "(5) Death Mountain", "(6) Demon Castle", "(7) ???"};
            return locations;
        }
        else{
            String[] locations = {"(1) Elven City"};
            return locations;
        }
    }

}

