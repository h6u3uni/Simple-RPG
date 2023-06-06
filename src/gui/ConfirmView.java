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
import dialogue.Story;
import items.Weapon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import rpggame.Logic;
import rpggame.Player;
import rpggame.SaveManager;

public class ConfirmView extends JPanel {

    private JLabel textLabel;
    private JButton yesButton;
    private JButton noButton;

    public ConfirmView(String query) {
        setLayout(new BorderLayout());

        // Add padding on the top with an EmptyBorder
        int topPadding = 20; // Adjust the value as needed
        setBorder(new EmptyBorder(topPadding, 0, 0, 0));

        // Create the JLabel for the dynamic text
        textLabel = new JLabel(query, SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(textLabel, BorderLayout.NORTH);

        // Create the buttons
        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public ConfirmView(Player player, Weapon selectedWeapon, ArrayList<Weapon> weapons) {
        setLayout(new BorderLayout());

        // Add padding on the top with an EmptyBorder
        int topPadding = 20; // Adjust the value as needed
        setBorder(new EmptyBorder(topPadding, 0, 0, 0));
        
        String separator = Logic.createSeparator(selectedWeapon.name.length()*2);
        
        // Create the JLabel for the dynamic text
        textLabel = new JLabel("<html>Confirm weapon? <br/><br/>" + separator + "<br/>" + selectedWeapon.name + "<br/>" + separator + "<br/>Attack Boost: " + selectedWeapon.getAtk() + "<br/>Defense Boost: " + selectedWeapon.getDef() + "<br/>Speed Boost: " + selectedWeapon.getSpd() + "</html>", SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(textLabel, BorderLayout.NORTH);

        // Create the buttons
        yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the desired action when "Yes" is clicked
                if(Logic.newPlay){
                    player.weapon = selectedWeapon;
                    Logic.frame.showGameView(Story.getStoryIntro());
                }
                else{
                    player.inventory.addItem(player.weapon);
                    player.weapon = selectedWeapon;
                    player.inventory.removeItem(selectedWeapon);
                    Logic.frame.gView.goBack();
                }
            }
        });
        
        noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the desired action when "No" is clicked
                if(Logic.newPlay){
                    Logic.frame.showWeaponChooseView(weapons, player);
                }
                else {
                    Logic.frame.gView.goBack();
                }
                
            }
        });
        
        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
    
    public JButton getYesButton(){
        return yesButton;
    }
    
    public JButton getNoButton(){
        return noButton;
    }
    
//    public static void main(String[] args) {
//        ConfirmView confirmView = new ConfirmView(new Player("test", "test"), new Weapon("testweapon", 10, 0, 2, 100));
//
//        // Create a JFrame to test the ConfirmView
//        javax.swing.JFrame frame = new javax.swing.JFrame("Confirm View Test");
//        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(confirmView);
//        frame.setPreferredSize(new Dimension(600, 320));
//        frame.pack();
//        frame.setVisible(true);
//    }
}
