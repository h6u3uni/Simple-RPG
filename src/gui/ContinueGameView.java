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
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import rpggame.Logic;
import rpggame.Player;

public class ContinueGameView extends JPanel {
    private JList<Player> playerList;
    private DefaultListModel<Player> listModel;

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
            Player selectedPlayer = playerList.getSelectedValue();
            if (selectedPlayer != null) {
                Logic.playerSelected(selectedPlayer);
            } else {
                System.out.println("No player selected.");
            }
        });
        add(selectButton, BorderLayout.SOUTH);
    }

//    public static void main(String[] args) {
//        ArrayList<Player> players = new ArrayList<>();
//        players.add(new Player("Player 1", "Male"));
//        players.add(new Player("Player 2", "Female"));
//        players.add(new Player("Player 3", "Male"));
//
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI(players);
//            }
//        });
//    }
//
//    private static void createAndShowGUI(ArrayList<Player> players) {
//        JFrame frame = new JFrame("Continue Game View");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 300);
//
//        ContinueGameView continueGameView = new ContinueGameView(players);
//        frame.getContentPane().add(continueGameView);
//
//        frame.setVisible(true);
//    }
}
