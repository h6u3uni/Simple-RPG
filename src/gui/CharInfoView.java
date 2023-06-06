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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import rpggame.Player;

public class CharInfoView extends JPanel {
    private JTextArea textArea;

    public CharInfoView(Player player) {
        textArea = new JTextArea();
        textArea.setEditable(false);

        // Create a scroll pane and add the text area to it
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Set the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(400, 300));

        // Add the scroll pane to the panel
        add(scrollPane);

        // Update the text area with the player information
        updateTextArea(player);
    }

    private void updateTextArea(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append("Player Information:\n");
        sb.append("Name: ").append(player.name).append("\n");
        sb.append("Level: ").append(player.lvl).append("\n");
        sb.append("XP: ").append(player.xpNow).append("/").append(player.xpNeeded).append("\n");
        sb.append("Health: ").append(player.hp).append("/").append(player.maxHP).append("\n");
        sb.append("Attack: ").append(player.atk).append(" (").append(player.weapon.getAtk()).append(")").append("\n");
        sb.append("Defense: ").append(player.def).append(" (").append(player.weapon.getDef()).append(")").append("\n");
        sb.append("Speed: ").append(player.spd).append(" (").append(player.weapon.getSpd()).append(")").append("\n");
        sb.append("Money: ").append(player.money).append("\n");
        sb.append("Weapon: ").append(player.weapon.name).append("\n");

        // Set the text of the text area
        textArea.setText(sb.toString());
    }
}
