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
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import rpggame.Logic;
import static rpggame.Logic.addGUIText;
import static rpggame.Logic.createHeading;
import static rpggame.Logic.frame;
import static rpggame.Logic.place;
import static rpggame.Logic.places;
import rpggame.Player;

public class StatChooseView extends JPanel {
    private int initialStatPt;
    private int initialHP;
    private int initialAtk;
    private int initialDef;
    private int initialSpd;
    
    private int tempStatPt;
    private int tempHP;
    private int tempAtk;
    private int tempDef;
    private int tempSpd;

    private JLabel headerLabel; // Declare the header label
    private JLabel remainingPointsLabel;
    private JLabel hpLabel;
    private JLabel atkLabel;
    private JLabel defLabel;
    private JLabel spdLabel;
    private JButton confirmButton;
            
    private Player player;
    private boolean initial;

    public StatChooseView(Player player, boolean initial) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.player = player;
        this.initial = initial;
        
        this.initialStatPt = player.statPoint;
        this.initialHP = player.hpStat;
        this.initialAtk = player.atk;
        this.initialDef = player.def;
        this.initialSpd = player.spd;

        // Initialize stat variables
        tempStatPt = initialStatPt;
        tempHP = initialHP;
        tempAtk = initialAtk;
        tempDef = initialDef;
        tempSpd = initialSpd;
        
        // Create the header label
        headerLabel = new JLabel("Distribute Stat Points", SwingConstants.CENTER);
        headerLabel.setFont(new Font(headerLabel.getFont().getName(), Font.BOLD, 20));
        headerLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(headerLabel);

        // Create the remaining points label
        remainingPointsLabel = new JLabel("Remaining Points: " + tempStatPt);
        remainingPointsLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(remainingPointsLabel);
        
        // Create the labels for each stat
        hpLabel = new JLabel("" + tempHP);
        atkLabel = new JLabel("" + tempAtk);
        defLabel = new JLabel("" + tempDef);
        spdLabel = new JLabel("" + tempSpd);

        // Create the buttons for increasing stats
        JButton increaseHpButton = new JButton("+");
        JButton increaseAtkButton = new JButton("+");
        JButton increaseDefButton = new JButton("+");
        JButton increaseSpdButton = new JButton("+");

        JPanel hpPanel = createStatPanel("HP Stat: ", increaseHpButton, hpLabel);
        JPanel atkPanel = createStatPanel("Atk Stat: ", increaseAtkButton, atkLabel);
        JPanel defPanel = createStatPanel("Def Stat: ", increaseDefButton, defLabel);
        JPanel spdPanel = createStatPanel("Spd Stat: ", increaseSpdButton, spdLabel);

        // Create the reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setAlignmentX(CENTER_ALIGNMENT);

        // Create the confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.setEnabled(false);
        confirmButton.setAlignmentX(CENTER_ALIGNMENT);

        // Add components to the main panel
        add(headerLabel);
        add(remainingPointsLabel);
        add(Box.createVerticalStrut(5)); // Add some vertical spacing
        add(hpPanel);
        add(atkPanel);
        add(defPanel);
        add(spdPanel);
        add(Box.createVerticalStrut(5)); // Add some vertical spacing
        add(resetButton);
        add(Box.createVerticalStrut(5)); // Add some vertical spacing
        add(confirmButton);
        add(Box.createVerticalStrut(5)); // Add some vertical spacing

        // Add action listeners to the buttons
        increaseHpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                increaseStatPoint("HP");
            }
        });

        increaseAtkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                increaseStatPoint("Atk");
            }
        });

        increaseDefButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                increaseStatPoint("Def");
            }
        });

        increaseSpdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                increaseStatPoint("Spd");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetSelection();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmSelection();
            }
        });
    }

    private void increaseStatPoint(String stat) {
        if (tempStatPt > 0) {
            switch (stat) {
                case "HP":
                    tempHP++;
                    break;
                case "Atk":
                    tempAtk++;
                    break;
                case "Def":
                    tempDef++;
                    break;
                case "Spd":
                    tempSpd++;
                    break;
            }
            tempStatPt--;
            updateLabels();
        }
    }
    
    private void updateLabels() {
        remainingPointsLabel.setText("Remaining Points: " + tempStatPt);
        hpLabel.setText("" + tempHP);
        atkLabel.setText("" + tempAtk);
        defLabel.setText("" + tempDef);
        spdLabel.setText("" + tempSpd);
        if(tempStatPt == 0){
            confirmButton.setEnabled(true);
        }
    }
    
    private JPanel createStatPanel(String labelPrefix, JButton button, JLabel label) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JLabel statLabel = new JLabel(labelPrefix);
        panel.add(statLabel);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(label);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(button);

        // Set borders to center the content
        int borderPadding = 5;
        EmptyBorder emptyBorder = new EmptyBorder(borderPadding, borderPadding, borderPadding, borderPadding);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, panel.getBorder());
        panel.setBorder(compoundBorder);

        return panel;
    }

    
    private void resetSelection() {
        // Reset the stat values to their initial values
        confirmButton.setEnabled(false);
        tempStatPt = initialStatPt;
        tempHP = initialHP;
        tempAtk = initialAtk;
        tempDef = initialDef;
        tempSpd = initialSpd;
        updateLabels();
    }

    private void confirmSelection() {
        this.player.statPoint = tempStatPt;
        this.player.hpStat = tempHP;
        this.player.hp = tempHP*5;
        this.player.maxHP = tempHP*5;
        this.player.atk = tempAtk;
        this.player.def = tempDef;
        this.player.spd = tempSpd;
        if(initial){
           Logic.chooseStartWeapon();
        }
        else{
            addGUIText("\n\n\n" + createHeading(places[place]));
            frame.gView.goBack();
            frame.gView.enableButtons();
        }
    }

}
