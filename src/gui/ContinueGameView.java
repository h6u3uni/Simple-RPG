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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import rpggame.Logic;
import rpggame.Player;

public class ContinueGameView extends JPanel {
    private JList<Player> playerList; //list of players
    private DefaultListModel<Player> listModel; //list model
    public Player selectedPlayer; //selected player

    /*
     * Constructor
     * @param players - list of players
     * @return none
     */
    public ContinueGameView(ArrayList<Player> players) {
        setLayout(new BorderLayout());

        // Create the header label
        JLabel headerLabel = new JLabel("Select Game Save");
        headerLabel.setFont(new Font(headerLabel.getFont().getName(), Font.BOLD, 20));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        // Create the list model and populate it with the players
        listModel = new DefaultListModel<>();
        for (Player player : players) {
            listModel.addElement(player);
        }

        // Create the player list and wrap it with a scroll pane
        playerList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(playerList);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        add(scrollPane, BorderLayout.CENTER);

        // Create the select button
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent e) -> {
            selectedPlayer = playerList.getSelectedValue();
            if (selectedPlayer != null) {
                Logic.playerSelected(selectedPlayer);
            } else {
                System.out.println("No player selected.");
            }
        });
        add(selectButton, BorderLayout.SOUTH);
    }

}
