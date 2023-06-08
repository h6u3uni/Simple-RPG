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
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import rpggame.Player;
import rpggame.SaveManager;

public class HallOfFameView extends JPanel {
    
    public HallOfFameView() {
        setLayout(new BorderLayout());
        
        // Header Label
        JLabel headerLabel = new JLabel("Hall of Fame");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        // Text Area
        ArrayList<String> hof = SaveManager.getHallOfFame();
        
        String out = "";
        if(hof.isEmpty()){
            out = "No Hall Of Fame Entries Yet!";
        }
        else{
            for(String entry : hof){
                out += " - " + entry + "\n";
            }
        }
        JTextArea textArea = new JTextArea(out);
        textArea.setEditable(false);
        
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 320));
        
        add(scrollPane, BorderLayout.CENTER);
    }

}
