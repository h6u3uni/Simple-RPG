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

    public StatChooseView(int initialStatPt, int initialHP, int initialAtk, int initialDef, int initialSpd) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.initialStatPt = initialStatPt;
        this.initialHP = initialHP;
        this.initialAtk = initialAtk;
        this.initialDef = initialDef;
        this.initialSpd = initialSpd;

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
        JButton confirmButton = new JButton("Confirm");
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
        tempStatPt = initialStatPt;
        tempHP = initialHP;
        tempAtk = initialAtk;
        tempDef = initialDef;
        tempSpd = initialSpd;
        updateLabels();
    }

    private void confirmSelection() {
        // TODO: Implement logic to confirm selection
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Stat Choose View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        StatChooseView statChooseView = new StatChooseView(10, 10, 5, 5, 5);
        frame.getContentPane().add(statChooseView);
        frame.pack();
        frame.setVisible(true);
    }
}
